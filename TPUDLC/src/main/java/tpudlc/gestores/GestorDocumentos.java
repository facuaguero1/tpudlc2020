package tpudlc.gestores;

import java.util.List;
import tpudlc.classes.Documento;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


@ApplicationScoped
public class GestorDocumentos {
    
    @Inject
    private EntityManager em;
    
    public List<Documento> listarTodos() {
        TypedQuery<Documento> q = em.createQuery("select d from Documento d", Documento.class);
        List<Documento> salida = q.getResultList();
        return salida;
    }
    
    public void agregarDocumento(Documento nuevo) {
        em.getTransaction().begin();
        em.persist(nuevo);
        em.getTransaction().commit();
    }
    
    public Documento buscar(int id) {
        return em.find(Documento.class, id);
    }
     
}
