package dao;

import entities.Alpinist;
import entities.Group;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class GroupDao implements Dao<Group, Integer> {
    private EntityManager manager;

    public GroupDao(EntityManager manager) {
        this.manager = manager;
    }


    @Override
    public void add(Group group) {
        manager.persist(group);
    }

    @Override
    public void update(Group group) {
        manager.merge(group);

    }

    @Override
    public Group getByPK(Integer integer) {
        return manager.find(Group.class, integer);
    }

    @Override
    public void remove(Group group) {
        manager.remove(group);

    }

    @Override
    public void removeByPK(Integer integer) {
        Group group = getByPK(integer);
        remove(group);
    }

    @Override
    public List<Group> getAll() {
        Query query = manager.createQuery("SELECT c FROM Group c");
        List<Group> groups = (List<Group>) query.getResultList();
        return groups;
    }

    public List<Group> groupByMountain(String nameOfMountain){
        TypedQuery<Group> query = manager.createNamedQuery("Group.byMountain",
                Group.class);
        query.setParameter("nameOfMountain", nameOfMountain);
        List<Group> groups = query.getResultList();
        return groups;
    }


    public List<Group> groupByProcess(Boolean inProcess){
        TypedQuery<Group> query = manager.createNamedQuery("Group.byProcess",
                Group.class);
        query.setParameter("inProcess", inProcess);
        List<Group> groups = query.getResultList();
        return groups;
    }
}
