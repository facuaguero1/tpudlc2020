package tpudlc.classes.daos;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import tpudlc.classes.Palabra;
import tpudlc.dao.DaoEclipseLink;


public class PalabraDao extends DaoEclipseLink<Palabra, Integer> {
    
    public PalabraDao() {
        super(Palabra.class);
    }
    
    public Palabra retrieve(Integer id){
        List<Palabra> resp = entityManager.createNamedQuery("Palabra.findById")
            .setParameter("idPalabra", id)
            .getResultList();
        if (resp.size() == 1) return resp.get(0);
        
        return null;
    }
    
    public Palabra retrieve(String palabra){
        List<Palabra> resp = entityManager.createNamedQuery("Palabra.findByPalabra")
            .setParameter("palabra", palabra)
            .getResultList();
        if (resp.size() == 1) return resp.get(0);
        
        return null;
    }
    
    public Integer maxId() {
        List<Palabra> resp = entityManager.createNamedQuery("Palabra.findMaxId")
                .setMaxResults(1)
                .getResultList();
        if (resp == null || resp.size() == 0) return 0;
        
        Integer maxId = resp.get(0).getIdPalabra();
        
        return maxId;
    }
    
}