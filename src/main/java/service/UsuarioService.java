package service;

import java.util.ArrayList;
import java.util.List;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
}
