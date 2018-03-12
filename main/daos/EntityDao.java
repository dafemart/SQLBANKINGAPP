package daos;

import java.util.ArrayList;


public interface EntityDao<T> {
     public void createEntity(T info);
     public T retrieveEntityByUsername(String username, String EntityType);
     public ArrayList<T> retrieveAllEntities(String EntityType);
}
