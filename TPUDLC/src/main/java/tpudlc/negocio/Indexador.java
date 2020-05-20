package tpudlc.negocio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import tpudlc.classes.Documento;
import tpudlc.classes.Palabra;
import tpudlc.classes.Posteo;
import tpudlc.classes.daos.DocumentoDao;
import tpudlc.classes.daos.PalabraDao;
import tpudlc.classes.daos.PosteoDao;


public class Indexador {
    
    @Inject private DocumentoDao documentoDao;
    @Inject private PalabraDao palabraDao;
    @Inject private PosteoDao posteoDao;
    
    public static final String directorioDocumentos = "/home/facundo/repositorios/DocumentosTP1/";
    public static final String directorioIndexados =  directorioDocumentos + "indexados/";
    
    
    
    public void indexar() throws IOException{
        
        File fileDirectorioDocs = new File(directorioDocumentos);
        
        File[] arrayDocumentos = fileDirectorioDocs.listFiles();
        
        ArrayList<File> listaDocs = new ArrayList();

        for(int i = 0; i < arrayDocumentos.length; i++){
            if( !arrayDocumentos[i].isDirectory() ){
                listaDocs.add(arrayDocumentos[i]);
            }
        }
        
        Pattern p = Pattern.compile("[a-zA-Z]+"); 
        Integer j = 0;
        Integer indexados = 0;
        
        Integer idPal = palabraDao.maxId() + 1;
        Integer idPost = posteoDao.maxId() + 1;
        if(listaDocs.size() > 0){
            HashMap<String, Palabra> vocabulario = iniciarVocabulario();
            System.out.println("Vocabulario inicializado con éxito.");
        
        
            for(File doc: listaDocs){ 
                System.gc();

                System.out.println("----------------------------------Doc: " + doc.getName());
                Documento documento = documentoDao.retrieve( doc.getName() );
                if ( documento  ==  null){
                    documento = new Documento( doc.getName() );
                    documento = documentoDao.create(documento);
                }

                HashMap<String, Posteo> posteos= new HashMap(100000, 0.5f);

                ArrayList<Palabra> palsPendientesCreacion = new ArrayList(50000);

                BufferedReader br = new BufferedReader(new FileReader(doc)); 
                String renglon;
                while ((renglon = br.readLine()) != null) {

                    Matcher m = p.matcher(renglon);

                    while (m.find()) { 

                        String pal = m.group().toLowerCase();


                        if(pal.length() > 3) {

                            Palabra palabra = vocabulario.get(pal);
                            if(palabra == null){
                                palabra = new Palabra(idPal, pal);
                                idPal++;
                                vocabulario.put(palabra.getPalabra(), palabra);
                                palsPendientesCreacion.add(palabra);
                            }

                            Posteo posteo = posteos.get(pal);
                            if(posteo == null){
                                posteo = new Posteo(idPost, palabra, documento);
                                idPost++;
                                posteos.put(pal, posteo);
                            }

                            posteo.increaseTf();

                            if (j % 10000 == 0){
                                System.out.println("Num aprox palabras indexadas: " + j.toString());
                                Integer tamanoVoc = (Integer) vocabulario.size();
                                System.out.println( "-----------------------palabras distintas:" + tamanoVoc.toString() );
                            }

                        }
                        j++;
                    }
                }

                palabraDao.bulkCreate(palsPendientesCreacion);

                updatePalabras(vocabulario, posteos);

                persistirPosteos(posteos);

                System.out.println("Se indexó el documento: " + doc.getName());
                moveToIndexados(doc);
                indexados++;
            }
        }
        System.out.println("----------------------------Proceso de indexación finalizado con éxito.");
    }
    
    private HashMap<String, Palabra> iniciarVocabulario() {
        HashMap<String, Palabra> vocabulario = new HashMap(800000, 0.5f);
        ArrayList<Palabra> queryPalabras= new ArrayList<Palabra>(palabraDao.findAll());
        Integer queryLength = queryPalabras.size();
        for(int i = 0; i < queryLength; i++){
            String stringPalabra = queryPalabras.get(i).getPalabra();
            Palabra objetoPalabra = queryPalabras.get(i);
            vocabulario.put(stringPalabra, objetoPalabra);
        }
        
        return vocabulario;
    }
    
    private void updatePalabras(HashMap<String, Palabra> vocabulario,
                                HashMap<String, Posteo> posteos) {
        Collection<Posteo> colPost= posteos.values();
        ArrayList<Posteo> arrayListPosteos = new ArrayList( colPost );
        ArrayList<Palabra> arrayListPalabras = new ArrayList(100000);
        for(Posteo posteo: arrayListPosteos) {
            Palabra palabra = posteo.getPalabra();
            palabra.increaseNr();
            if( posteo.getTf() > palabra.getMaxtf() ) {
                palabra.setMaxtf( posteo.getTf() );
            }
            arrayListPalabras.add( palabra );
        }
        palabraDao.bulkUpdate(arrayListPalabras);
    }
    
    private void persistirPosteos(HashMap<String, Posteo> posteos) {
        Collection<Posteo> values = posteos.values();
        ArrayList<Posteo> valuesList = new ArrayList<Posteo>(values);
        posteoDao.bulkCreate(valuesList);
    }
    
    private void moveToIndexados(File file) {
        try{
            Path temp = Files.move
                ( Paths.get( directorioDocumentos + file.getName() ),
                  Paths.get(directorioIndexados + file.getName() ) );
            System.out.println("        El documento fue reubicado.");
        
        }catch (IOException ex){
            System.out.println("------------------------------------ERROR AL MOVER EL ARCHIVO: " + file.getName());
        }
    }
                
}
