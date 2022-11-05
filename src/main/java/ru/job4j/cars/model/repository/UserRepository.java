package ru.job4j.cars.model.repository;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.cars.model.User;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
public class UserRepository {
    final SessionFactory sf;
    /**
     * Сохранить в базе.
     *
     * @param user пользователь.
     * @return пользователь с id.
     */
    public User create(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return user;
    }
    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     */
    public void update(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE User SET password = :fPassword WHERE login = :fLogin")
                    .setParameter("fLogin", user.getLogin())
                    .setParameter("fPassword", user.getPassword())
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
    /**
     * Удалить пользователя по id.
     * @param userId ID
     */
    public void delete(int userId) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "DELETE User WHERE id = :fId")
                    .setParameter("fId", userId)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
    /**
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        Session session = sf.openSession();
        try {
            Query query = session.createQuery("from ru.job4j.cars.model.User order by id", User.class);
            return query.list();
        } finally {
            session.close();
        }
    }
    /**
     * Найти пользователя по ID
     * @return пользователь.
     */
    public Optional<User> findById(int id) {
        Session session = sf.openSession();
        try {
            Query query = session.createQuery("from ru.job4j.cars.model.User where id = :paramId", User.class);
            query.setParameter("paramId", id);
            System.out.println(query.uniqueResultOptional());
            return query.uniqueResultOptional();
        } finally {
            session.close();
        }
    }
    /**
     * Список пользователей по login LIKE %key%
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key) {
        Session session = sf.openSession();
        try {
            Query query = session.createQuery("from ru.job4j.cars.model.User where login like :paramLogin", User.class);
            query.setParameter("paramLogin", "%" + key + "%");
            return query.list();
        } finally {
            session.close();
        }
    }
    /**
     * Найти пользователя по login.
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        Session session = sf.openSession();
        try {
            Query query = session.createQuery("from ru.job4j.cars.model.User where login = :paramLogin", User.class);
            query.setParameter("paramLogin", login);
            System.out.println(query.uniqueResultOptional());
            return query.uniqueResultOptional();
        } finally {
            session.close();
        }
    }
}