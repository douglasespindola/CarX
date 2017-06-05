package service;

import entity.Anuncio;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class AnuncioService {

    @PersistenceContext(name = "pi2017")
    private EntityManager entityManager;

    public List<Anuncio> getAllAnuncios() {
        try {
            Query query = entityManager.createNamedQuery("Anuncio.getAllAnuncios");
            return (List<Anuncio>) query.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public Anuncio getAnuncio() {
        try {
            Query query = entityManager.createNamedQuery("Anuncio.getAnuncio");
            return (Anuncio) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public Anuncio update(Anuncio anuncio) {
        return entityManager.merge(anuncio);
    }

    @Transactional
    public Anuncio create(Anuncio anuncio) {
        entityManager.persist(anuncio);
        return anuncio;
    }

    @Transactional
    public void remove(Integer id) {
        Anuncio anuncio = entityManager.find(Anuncio.class, id);
        entityManager.remove(anuncio);

    }
}
