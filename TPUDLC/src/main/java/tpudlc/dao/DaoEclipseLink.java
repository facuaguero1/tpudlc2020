package tpudlc.dao;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import tpudlc.commons.exceptions.TechnicalException;


public abstract class DaoEclipseLink<E extends DalEntity, K> implements Dao<E, K> {
    
    @Inject
    protected EntityManager entityManager;
    
    private final Class<E> entityClass;
    
    public DaoEclipseLink(Class<E> entityClass) {
        this.entityClass = entityClass;
    }
    
    protected Class<E> getEntityClass() {
        return entityClass;
    }
    
    @Override
    public E create(E pData) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(pData);
            entityManager.flush();
            entityManager.getTransaction().commit();
            //System.out.println("----------------------EXITO");
        }
        catch (Exception ex){
            //entityManager.getTransaction().rollback();
            //System.out.println("----------------------FRACASO");
            throw new TechnicalException(ex);
        }
        
        return pData;
    }
    
    /*
        Para poder usar el siguiente método, la PK ya debe haber sido generada
        de antemano.
    */
    public List<E> bulkCreate(List<E> list) {
        
        Integer listSize = list.size();
        try{
            entityManager.getTransaction().begin();
            for(int i = 0; i < listSize; i++) {
                entityManager.persist( list.get(i) );
                if(i % 1000 == 0 || i == listSize-1) {
                    entityManager.flush();
                    entityManager.clear();
                }
            }
            entityManager.getTransaction().commit();
        }catch(Exception ex) {
            throw new TechnicalException(ex);
        }
        
        return list;
    }
    
    @Override
    public void update(E pData){
        try{
            entityManager.getTransaction().begin();
            E managed = entityManager.merge(pData);
            entityManager.persist(managed);
            entityManager.flush();
            entityManager.getTransaction().commit();
        }
        catch (Exception ex){
            entityManager.getTransaction().rollback();
            throw new TechnicalException(ex);
        }
    }
    
    public List<E> bulkUpdate(List<E> list) {
        Integer listSize = list.size();
        try{
            entityManager.getTransaction().begin();
            for(int i = 0; i < listSize; i++) {
                E managed = entityManager.merge(list.get(i));
                entityManager.persist( managed );
                if(i % 1000 == 0 || i == listSize-1) {
                    entityManager.flush();
                    entityManager.clear();
                }
            }
            entityManager.getTransaction().commit();
        }catch(Exception ex) {
            throw new TechnicalException(ex);
        }
        
        return list;
    }
    
    @Override
    public void delete(K pKey) {
        try{
            entityManager.getTransaction().begin();
            entityManager.remove(retrieve(pKey));
            entityManager.flush();
            entityManager.getTransaction().commit();
        }
        catch (Exception ex){
            entityManager.getTransaction().rollback();
            throw new TechnicalException(ex);
        }
    }
    
    @Override
    public E retrieve(K pKey) {
        return entityManager.find(getEntityClass(), pKey);
    }
    
    @Override
    public List<E> findAll() {
        try{
            String className = getEntityClass().getSimpleName();
            Query query = entityManager.createQuery("SELECT e FROM " + className + " e");
            return query.getResultList();
        }
        catch (Exception ex){
            throw new TechnicalException(ex);
        }
    }
    
    public List<E> findByFilter(String filter) {
        try{
            String className = getEntityClass().getSimpleName();
            Query query = entityManager.createNamedQuery(className + ".findByFilter")
                    .setParameter(":filter", filter);
            
            return query.getResultList();
        }
        catch (Exception ex){
            throw new TechnicalException(ex);
        }
    }
}
