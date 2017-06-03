package service;

import java.util.ArrayList;
import java.util.List;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.*;
import javax.transaction.Transactional;


import entity.Usuario;

@Named
@RequestScoped
public class UsuarioService {

    @PersistenceContext(name="pi2017")
    private EntityManager entityManager;

    public List<Usuario> getAllUsuarios() {
        try {
            Query query = entityManager.createNamedQuery("Usuario.getAllUsuarios");
            return query.getResultList();
        } catch (Exception e){
            return new ArrayList<>();
        }
    }

    public Usuario getUsuario(Integer id) {
        try {
            Query query = entityManager.createNamedQuery("Usuario.getUsuario");
            query.setParameter("id",id);
            return (Usuario) query.getSingleResult();
        } catch (Exception e){
            return null;
        }
    }

    @Transactional
    public Usuario update(Usuario usuario) { //throws TransactionRequiredException {
        return entityManager.merge(usuario);
    }

    @Transactional
    public Usuario create(Usuario usuario) {//throws PersistenceException {
        entityManager.persist(usuario);
        return usuario;
    }
    @Transactional
    public void remove(Integer id){
        Usuario usuario = entityManager.find(Usuario.class, id);
       entityManager.remove(usuario);
    }
}
