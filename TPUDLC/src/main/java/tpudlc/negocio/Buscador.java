package tpudlc.negocio;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import tpudlc.classes.Documento;
import tpudlc.classes.Palabra;
import tpudlc.classes.Posteo;
import tpudlc.classes.daos.DocumentoDao;
import tpudlc.classes.daos.PalabraDao;
import tpudlc.classes.daos.PosteoDao;


public class Buscador {
    
    @Inject private DocumentoDao documentoDao;
    @Inject private PalabraDao palabraDao;
    @Inject private PosteoDao posteoDao;
    
    private final String directorioIndexados = Indexador.directorioIndexados;
    
    public List<Documento> buscar(String query) {
        
        /* Cálculo de la cantidad de documentos indexados para calcular
            el logaritmo más adelante.
        */
        File fileDirectorioDocs = new File(directorioIndexados);
        Integer N = fileDirectorioDocs.listFiles().length;
        
        // Inicializacion de la variable que contendrá la respuesta.
        List<Documento> resp = new ArrayList();
        
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(query);
        ArrayList<Palabra> listaPalabras = new ArrayList(10);
        while(m.find()){
            String termino = m.group().toLowerCase();
            Palabra pal = palabraDao.retrieve(termino);
            if( pal != null ) { listaPalabras.add(pal);} 
        }
        
        
        if( listaPalabras.size() > 0 ) {
            // Ordena las palabras de la query de acuerdo a su Nr, en orden ascendente.
            Collections.sort(listaPalabras, new ComparadorNrPalabras());
            
            // HashMap que contendrá a los documentos relevantes y sus puntajes.
            HashMap<Documento, Double> mapDocumentos = new HashMap(200);
            
            for(Palabra pal: listaPalabras) {
                
                // Para cada palabra, obtiene los 10 posteos con el tf más alto.
                ArrayList<Posteo> posteos = new ArrayList( posteoDao.retrieveOrdered(10, pal) );
                for(Posteo post: posteos){
                    
                    Double puntaje = post.getTf() * Math.log10( N / pal.getNr() );
                    

                    Documento doc = post.getDocumento();
                    if( !mapDocumentos.containsKey(doc) ) {
                        mapDocumentos.put(doc, 0.0);
                    }
                    // Suma el puntaje al documento correspondiente.
                    Double puntTemp = mapDocumentos.get(doc) + puntaje;
                    mapDocumentos.remove(doc);
                    mapDocumentos.put(doc, puntTemp);
                    
                           
                }
            }   
             /* Ahora convierte el HashMap en lista para poder
                        hacer el ordenamiento en base a los puntajes.
                    */
                    ArrayList<ContenedorDocumento> listaDocumentos = new ArrayList();
                    
                    Iterator it = mapDocumentos.entrySet().iterator();
                    while( it.hasNext() ) {
                        Map.Entry<Documento, Double> par = (Map.Entry) it.next();
                        Documento docu = par.getKey();
                        Double punt = par.getValue();
                        
                        ContenedorDocumento cont = new ContenedorDocumento(
                                                           docu, punt);
                        listaDocumentos.add(cont);
                        
                        it.remove();
                    }
                    
                    // Ahora los ordena de acuerdo a sus puntajes.
                    Collections.sort(listaDocumentos,
                                    new ComparadorPuntajeDocumentos() );

                    
                    for(int i = listaDocumentos.size()-1; i > -1; i--) {
                        Documento docResp = listaDocumentos.get(i).getDoc();
                        resp.add(docResp);
                    }      
        }
        
        return resp;
    }
    
    private class ComparadorNrPalabras implements Comparator<Palabra> {
        @Override
        public int compare(Palabra p1, Palabra p2) {
            return p1.getNr().compareTo( p2.getNr() );
        }
    }
    
    private class ComparadorPuntajeDocumentos implements Comparator<ContenedorDocumento> {
        @Override
        public int compare(ContenedorDocumento p1, ContenedorDocumento p2) {
            return p1.getPuntaje().compareTo( p2.getPuntaje() );
        }
    }
    
    private class ContenedorDocumento {
        
        private Documento doc;
        private Double puntaje;

        public ContenedorDocumento(Documento doc, Double puntaje) {
            this.doc = doc;
            this.puntaje = puntaje;
        }

        public Documento getDoc() {
            return doc;
        }

        public Double getPuntaje() {
            return puntaje;
        }
    
    }
    
}
