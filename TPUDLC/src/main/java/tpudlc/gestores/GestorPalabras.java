package tpudlc.gestores;

import java.util.List;
import tpudlc.classes.Palabra;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


@ApplicationScoped
public class GestorPalabras {
    
    @Inject
    private EntityManager em;
    
    public List<Palabra> listarTodas() {
        TypedQuery<Palabra> q = em.createQuery("select p from Palabra p", Palabra.class);
        List<Palabra> salida = q.getResultList();
        return salida;
    }
    
    public void agregarPalabra(Palabra nueva) {
        em.getTransaction().begin();
        em.persist(nueva);
        em.getTransaction().commit();
    }
    
    public Palabra buscar(int id) {
        return em.find(Palabra.class, id);
    }
     
}