package tpudlc.producers;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory; 


public class EntityManagerProducer {
    
    @Inject
    private EntityManagerFactory emf;
    
    @Produces
    @RequestScoped
    public EntityManager create() {
        System.out.println("create");
        return emf.createEntityManager();
    }
    
    public void destroy(@Disposes EntityManager em) {
        em.close();
    }
    
}
