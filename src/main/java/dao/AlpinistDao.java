package dao;

import entities.Alpinist;
import entities.Mountain;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class AlpinistDao implements Dao<Alpinist, Integer>{

    private EntityManager manager;

    public AlpinistDao(EntityManager manager) {
        this.manager = manager;
    }


    @Override
    public void add(Alpinist alpinist) {
        manager.persist(alpinist);


    }

    @Override
    public void update(Alpinist alpinist) {
        manager.merge(alpinist);


    }

    @Override
    public Alpinist getByPK(Integer integer) {
        return manager.find(Alpinist.class, integer);

    }

    @Override
    public void remove(Alpinist alpinist) {
        manager.remove(alpinist);


    }

    @Override
    public void removeByPK(Integer integer) {
        Alpinist alpinist = getByPK(integer);
        remove(alpinist);

    }

    @Override
    public List<Alpinist> getAll() {
        Query query = manager.createQuery("SELECT c FROM Alpinist c");
        List<Alpinist> alpinists = (List<Alpinist>) query.getResultList();
        return alpinists;
    }

    public List<Alpinist> alpinistByAge(int from, int to){
        TypedQuery<Alpinist> query = manager.createNamedQuery("Alpinist.byAge", Alpinist.class);
        query.setParameter("from", from);
        query.setParameter("to", to);
        List<Alpinist> alpinists = query.getResultList();
        return alpinists;
    }
}
