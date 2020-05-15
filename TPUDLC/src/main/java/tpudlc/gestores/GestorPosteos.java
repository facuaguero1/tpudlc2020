package tpudlc.gestores;

import java.util.List;
import tpudlc.classes.Posteo;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


@ApplicationScoped
public class GestorPosteos {
    
    @Inject
    private EntityManager em;
    
    public List<Posteo> listarTodos() {
        TypedQuery<Posteo> q = em.createQuery("select p from Posteo p", Posteo.class);
        List<Posteo> salida = q.getResultList();
        return salida;
    }
    
    public void agregarPosteo(Posteo nuevo) {
        em.getTransaction().begin();
        em.persist(nuevo);
        em.getTransaction().commit();
    }
    
    public Posteo buscar(int id) {
        return em.find(Posteo.class, id);
    }
     
}