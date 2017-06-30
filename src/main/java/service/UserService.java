package service;

import dto.TokenDto;
import entity.User;
import org.joda.time.DateTime;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

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
    public synchronized User update(User user) throws NoSuchAlgorithmException {
        Query query = entityManager.createNamedQuery("User.checkUserNamedAvailable");
        query.setParameter("email",user.getEmail());
        String password = user.getPassword();
        user.setPassword(password);
        List<User> users = query.getResultList();
        if(users.size() == 0) {
            return entityManager.merge(user);
        }
        for(User us: users) {
            if(us.getId() == user.getId()) {
                return entityManager.merge(user);
            }
        }
        throw new IllegalArgumentException("Esse email já está vinculado a uma conta favor usar outro");
    }

    @Transactional
    public synchronized User create(User user) throws NoSuchAlgorithmException {
        Query query = entityManager.createNamedQuery("User.checkUserNamedAvailable");
        query.setParameter("email",user.getEmail());
        List<User> users = query.getResultList();
        if(users.size() == 0){
            String password = user.getPassword();
            user.setPassword(password);
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
            query.setParameter("email", user.getEmail()).setParameter("password", user.convertPasswordToMD5(user.getPassword()));
            User userLogged = (User) query.getSingleResult();
            if (userLogged != null) {
                userLogged.setToken(DatatypeConverter.printBase64Binary(
                        (user.getEmail() + ":" + (new DateTime().getMillis() + (60 * 60 * 60 * 60))).getBytes())
                );
                entityManager.persist(userLogged);

                tokenDto.setToken(userLogged.getToken());
                tokenDto.setName(userLogged.getName());
                tokenDto.setEmail(userLogged.getEmail());
                tokenDto.setCpf(userLogged.getCpf().toString());
                tokenDto.setUserId(userLogged.getId());
                return tokenDto;
            }
            tokenDto.setToken(null);
            return tokenDto;
        } catch (Exception e) {
            tokenDto.setToken(null);
            return tokenDto;
        }
    }

    @Transactional
    public TokenDto checkToken(String token) {
        try {
            Query query = entityManager.createNamedQuery("User.getToken");
            query.setParameter("token", token);
            User user = (User)query.getSingleResult();
            if(user==null) return null;
            TokenDto tokenDto = new TokenDto();
            tokenDto.setEmail(user.getEmail());
            tokenDto.setName(user.getName());
            tokenDto.setCpf(user.getCpf().toString());
            tokenDto.setToken(user.getToken());
            tokenDto.setUserId(user.getId());
            return tokenDto;
        } catch (Exception e) {
            return null;
        }
    }
}
