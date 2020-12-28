package utils;

import dao.AlpinistDao;
import dao.GroupDao;
import dao.MountainDao;
import entities.Alpinist;
import entities.Group;
import entities.Mountain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ManyToMany;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class BaseApp {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("entityManager");
        EntityManager manager = factory.createEntityManager();


        AlpinistDao alpinistDao = new AlpinistDao(manager);
        GroupDao groupsDao = new GroupDao(manager);
        MountainDao mountainDao = new MountainDao(manager);

        Alpinist alpinist1 = new Alpinist();
        Alpinist alpinist2 = new Alpinist();
        Alpinist alpinist3 = new Alpinist();
        Alpinist alpinist4 = new Alpinist();
        Group group1 = new Group();
        Group group2 = new Group();
        Mountain mountain1 = new Mountain();
        Mountain mountain2 = new Mountain();


        alpinist1.setAddress("Большая Никитская 53");
        alpinist1.setAge(22);
        alpinist1.setId_alpinist(1);
        alpinist1.setName("Анатолий");
        alpinist2.setAddress("Ленина 15");
        alpinist2.setAge(35);
        alpinist2.setId_alpinist(2);
        alpinist2.setName("Николай");
        alpinist3.setAddress("Кронверкская 62");
        alpinist3.setAge(26);
        alpinist3.setId_alpinist(3);
        alpinist3.setName("Евгений");
        alpinist4.setAddress("Кронверкская 62");
        alpinist4.setAge(24);
        alpinist4.setId_alpinist(4);
        alpinist4.setName("Елена");


        mountain1.setCountry("Россия");
        mountain1.setHeight(4509);
        mountain1.setId_mountain(1);
        mountain1.setNameOfMountain("Белуха");
        mountain2.setCountry("Грузия");
        mountain2.setHeight(5033);
        mountain2.setId_mountain(2);
        mountain2.setNameOfMountain("Казбек");

        group1.setDate(LocalDate.of(2020, 8, 12));
        group1.setDuration(7);
        group1.setId_group(1);
        group1.setInProcess(true);
        group1.setMountain(mountain1);
        group1.addAlpinist(alpinist1);
        group1.addAlpinist(alpinist2);
        group2.setDate(LocalDate.of(2020, 8, 17));
        group2.setDuration(9);
        group2.setId_group(2);
        group2.setInProcess(false);
        group2.setMountain(mountain2);
        group2.addAlpinist(alpinist3);
        group2.addAlpinist(alpinist4);

        manager.getTransaction().begin();
        alpinistDao.add(alpinist1);
        alpinistDao.add(alpinist2);
        alpinistDao.add(alpinist3);
        alpinistDao.add(alpinist4);
        manager.getTransaction().commit();

        manager.getTransaction().begin();
        mountainDao.add(mountain1);
        mountainDao.add(mountain2);
        manager.getTransaction().commit();

        manager.getTransaction().begin();
        groupsDao.add(group1);
        groupsDao.add(group2);
        manager.getTransaction().commit();

        manager.getTransaction().begin();
        List<Alpinist> alpinists = alpinistDao.alpinistByAge(20,25);
        manager.getTransaction().commit();
        for (Alpinist alpinist : alpinists) {
            System.out.println("-------------Альпинисты по возрасту-------------" + "\n" +
                    "Name: " + alpinist.getName() +"\n"+
                    "Address: " + alpinist.getAddress() + "\n" +
                    "Age: " + alpinist.getAge());
        }

        manager.getTransaction().begin();
        List<Group> groups1 = groupsDao.groupByMountain("Белуха");
        manager.getTransaction().commit();
        for (Group group : groups1) {
            System.out.println("-------------Горы по названию-------------" + "\n" +
                    "id: " + group.getId_group() +"\n"+
                    "mountain: " + group.getMountain().getNameOfMountain() + "\n" +
                    "date: " + group.getDate());
        }

        manager.getTransaction().begin();
        List<Mountain> mountains1 = mountainDao.mountainByCountry("Россия");
        manager.getTransaction().commit();
        for (Mountain mountain : mountains1) {
            System.out.println("-------------Горы по Стране-------------" + "\n" +
                    "id: " + mountain.getId_mountain() +"\n"+
                    "name: " + mountain.getNameOfMountain() + "\n" +
                    "height: " + mountain.getHeight());
        }

        manager.getTransaction().begin();
        List<Group> groups2 = groupsDao.groupByProcess(true);;
        manager.getTransaction().commit();
        for (Group group : groups2) {
            System.out.println("-------------Группы по статусу-------------" + "\n" +
                    "id: " + group.getId_group() +"\n"+
                    "mountain: " + group.getMountain().getNameOfMountain() + "\n" +
                    "date: " + group.getDate());

        }
        manager.close();



/*
        manager.getTransaction().begin();
        manager.persist(alpinist);
        manager.persist(mountain);
        manager.persist(group);
        manager.getTransaction().commit();


 */

    }
}
