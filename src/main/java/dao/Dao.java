package dao;

import java.util.List;

public interface Dao<T, PK> {

        void add(T t);

        void update(T t);

        T getByPK(PK pk);

        void remove(T t);

        void removeByPK(PK pk);

        List<T> getAll();


}
