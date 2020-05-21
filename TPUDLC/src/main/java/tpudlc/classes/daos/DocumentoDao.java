package tpudlc.classes.daos;
import java.util.List;
import tpudlc.classes.Documento;
import tpudlc.dao.DaoEclipseLink;


public class DocumentoDao extends DaoEclipseLink<Documento, Integer> {

    public DocumentoDao() {
        super(Documento.class);
    }
    
    public Documento retrieve(Integer id){
        List<Documento> resp = entityManager.createNamedQuery("Documento.findById")
            .setParameter("idDocumento", id)
            .getResultList();
        if (resp.size() == 1) return resp.get(0);
        
        return null;
    }
    
    public Documento retrieve(String nombre) {
        List<Documento> resp = entityManager.createNamedQuery("Documento.findByNombreArchivo")
                .setParameter("nombreArchivo", nombre)
                .getResultList();
        if (resp.size() == 1) return resp.get(0);
        
        return null;
    }
    
    public Integer maxId() {
        List<Documento> resp = entityManager.createNamedQuery("Documento.findMaxId")
                .getResultList();
        if (resp == null || resp.size() == 0) return 0;
        
        Integer maxId = resp.get(0).getIdDocumento();
        
        return maxId;
    }
   
}
