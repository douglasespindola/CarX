package service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.DatatypeConverter;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.*;
import javax.transaction.Transactional;


import dto.TokenDto;
import entity.User;
import org.joda.time.DateTime;

@Named
@RequestScoped
public class UserService{

    @PersistenceContext(name = "pi2017")
    private EntityManager entityManager;
    @Transactional
    public List<User> getAll() {
        try {
            Query query = entityManager.createNamedQuery("User.getAllUsers");
            return query.getResultList();
        } catch (Exception e){
            return new ArrayList<>();
        }
    }
    @Transactional
    public User get(Integer id) {
        try {
            Query query = entityManager.createNamedQuery("User.getUser");
            query.setParameter("id", id);
            return (User) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public synchronized User update(User user) {
        Query query = entityManager.createNamedQuery("User.checkUserNamedAvailable");
        query.setParameter("email",user.getEmail());
        List<User> users = query.getResultList();
        if(users == null) {
            return entityManager.merge(user);
        }
        throw new IllegalArgumentException("Esse email já está vinculado a uma conta favor usar outro");
    }

    @Transactional
    public synchronized User create(User user) {
        Query query = entityManager.createNamedQuery("User.checkUserNamedAvailable");
        query.setParameter("email",user.getEmail());
        List<User> users = query.getResultList();
        if(users.size() == 0){
            entityManager.persist(user);
            return user;
        }
        throw new IllegalArgumentException("Esse email já está vinculado a uma conta favor usar outro");
    }

    @Transactional
    public void remove(Integer id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Transactional
    public TokenDto getToken(User user) {
        TokenDto tokenDto = new TokenDto();
        try {
            Query query = entityManager.createNamedQuery("User.getLogin");
            query.setParameter("email", user.getEmail()).setParameter("password", user.getPassword());
            User userLogged = (User) query.getSingleResult();
            if (userLogged != null) {
                userLogged.setToken(DatatypeConverter.printBase64Binary(
                        (user.getEmail() + ":" + (new DateTime().getMillis() + 1800)).getBytes())
                );
                entityManager.persist(userLogged);
                
                tokenDto.setToken(userLogged.getToken());
                return tokenDto;
            }
            tokenDto.setToken("");
            return tokenDto;
        } catch (Exception e) {
            tokenDto.setToken("");
            return tokenDto;
        }
    }
    @Transactional
    public User checkToken(String token) {
        try {
            Query query = entityManager.createNamedQuery("User.getToken");
            query.setParameter("token", token);
            return (User) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
