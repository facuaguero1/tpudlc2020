package tpudlc.dao;

import java.util.List;



public interface Dao<E extends DalEntity,K> {
    
    void update(E pData);
    
    void delete(K pKey);
    
    E create(E pData);
    
    E retrieve(K pKey);
    
    List<E> findAll();
    
}
