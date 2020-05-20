package tpudlc.classes.daos;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import tpudlc.classes.Documento;
import tpudlc.classes.Palabra;
import tpudlc.classes.Posteo;
import tpudlc.dao.DaoEclipseLink;

//@ApplicationScoped
public class PosteoDao extends DaoEclipseLink<Posteo, Integer> {
    
    public PosteoDao() {
        super(Posteo.class);
    }
    
    public Posteo retrieve(Integer id){
        List<Posteo> resp = entityManager.createNamedQuery("Posteo.findById")
            .setParameter("idPosteo", id)
            .getResultList();
        if (resp.size() == 1) return resp.get(0);
        
        return null;
    }
    
    public Posteo retrieve(Palabra pal, Documento doc){
        List<Posteo> resp = entityManager.createNamedQuery("Posteo.findByPalabraAndDocumento")
            .setParameter("palabra", pal).setParameter("documento", doc)
            .getResultList();
        if (resp.size() == 1) return resp.get(0);
        
        return null;
    }
    
    public List<Posteo> retrieve(Palabra pal) {
        List<Posteo> resp = entityManager.createNamedQuery("Posteo.findByPalabra")
                .setParameter("palabra", pal)
                .getResultList();
        
        return resp;
    }
    
    public List<Posteo> retrieveOrdered(int cantidad, Palabra pal) {
        List<Posteo> resp = entityManager.createNamedQuery("Posteo.findByPalabraOrderedByTf")
                .setParameter("palabra", pal).setMaxResults(cantidad)
                .getResultList();
        
        return resp;
    }
    
    public List<Posteo> retrieve(Documento doc) {
        List<Posteo> resp = entityManager.createNamedQuery("Posteo.findByDocumento")
                .setParameter("documento", doc)
                .getResultList();
        
        return resp;
    }
    
    public Integer maxId() {
        List<Posteo> resp = entityManager.createNamedQuery("Posteo.findMaxId")
                .getResultList();
        if (resp == null || resp.size() == 0) return 0;
        
        Integer maxId = resp.get(0).getIdPosteo();
        
        return maxId;
    }

}