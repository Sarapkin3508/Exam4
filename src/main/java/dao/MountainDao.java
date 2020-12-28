package dao;


import entities.Mountain;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.text.html.parser.Entity;
import java.util.List;

public class MountainDao implements Dao<Mountain, Integer> {
   private  EntityManager manager;

    public MountainDao(EntityManager manager) {
        this.manager = manager;
    }


    @Override
    public void add(Mountain mountain) {
        manager.persist(mountain);

    }

    @Override
    public void update(Mountain mountain) {
        manager.merge(mountain);

    }

    @Override
    public Mountain getByPK(Integer integer) {
        return manager.find(Mountain.class, integer);
    }

    @Override
    public void remove(Mountain mountain) {
        manager.remove(mountain);

    }

    @Override
    public void removeByPK(Integer integer) {
        Mountain mountain = getByPK(integer );
        remove(mountain);

    }


    @Override
    public List<Mountain> getAll() {
        Query query = manager.createQuery("SELECT c FROM Mountain c");
        List<Mountain> mountains = (List<Mountain>) query.getResultList();
        return mountains;
    }

    public List<Mountain> mountainByCountry(String country){
        TypedQuery<Mountain> query = manager.createNamedQuery("Mountain.byCountry",
                Mountain.class);
        query.setParameter("country", country);
        List<Mountain> mountains = query.getResultList();
        return mountains;
    }

}
