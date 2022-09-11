package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private final EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(User user){
        entityManager.persist(user);
    }
    public void deleteById(int id){
        entityManager.remove(entityManager.find(User.class,id));
    }

    public User getById (int id){
        return entityManager.find(User.class,id);
    }

   public User findByUsername(String username){
       return entityManager.createQuery("SELECT u FROM User u  WHERE u.username = :username", User.class)
               .setParameter("username", username).getSingleResult();
   }

    public List<User> findAll() {
        return entityManager.createQuery("SELECT u from User  u", User.class )
                .getResultList();

    }

    public void update(User user) {
        entityManager.merge(user);
    }

}
