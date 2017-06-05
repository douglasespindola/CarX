package service;

import java.util.ArrayList;
import java.util.List;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.*;
import javax.transaction.Transactional;


import entity.User;

@Named
@RequestScoped
public class UserService {

    @PersistenceContext(name="pi2017")
    private EntityManager entityManager;

    public List<User> getAllUsers() {
        try {
            Query query = entityManager.createNamedQuery("User.getAllUsers");
            return query.getResultList();
        } catch (Exception e){
            return new ArrayList<>();
        }
    }

    public User getUser(Integer id) {
        try {
            Query query = entityManager.createNamedQuery("User.getUser");
            query.setParameter("id",id);
            return (User) query.getSingleResult();
        } catch (Exception e){
            return null;
        }
    }

    @Transactional
    public User update(User user) {
        return entityManager.merge(user);
    }

    @Transactional
    public User create(User user) {
        entityManager.persist(user);
        return user;
    }
    @Transactional
    public void remove(Integer id){
        User user = entityManager.find(User.class, id);
       entityManager.remove(user);
    }
}
