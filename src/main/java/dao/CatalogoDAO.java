package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entities.ElementoCatalogo;

import java.util.List;

public class CatalogoDAO {
    private static EntityManagerFactory emf;
    private EntityManager em;

    public CatalogoDAO() {
        emf = Persistence.createEntityManagerFactory("U4D15Progetto");
        em = emf.createEntityManager();
    }

    public void aggiungiElemento(ElementoCatalogo elemento) {
        em.getTransaction().begin();
        em.persist(elemento);
        em.getTransaction().commit();
    }

    public void rimuoviElemento(String isbn) {
        em.getTransaction().begin();
        ElementoCatalogo elemento = em.find(ElementoCatalogo.class, isbn);
        if (elemento != null) {
            em.remove(elemento);
        }
        em.getTransaction().commit();
    }

    public ElementoCatalogo cercaPerISBN(String isbn) {
        return em.find(ElementoCatalogo.class, isbn);
    }

    public List<ElementoCatalogo> cercaPerAnnoPubblicazione(int anno) {
        TypedQuery<ElementoCatalogo> query = em.createQuery(
                "SELECT e FROM ElementoCatalogo e WHERE e.annoPubblicazione = :anno", ElementoCatalogo.class);
        query.setParameter("anno", anno);
        return query.getResultList();
    }

    public List<ElementoCatalogo> cercaPerAutore(String autore) {
        TypedQuery<ElementoCatalogo> query = em.createQuery(
                "SELECT e FROM ElementoCatalogo e WHERE TYPE(e) = Libro AND e.autore = :autore", ElementoCatalogo.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }

    public List<ElementoCatalogo> cercaPerTitolo(String titolo) {
        TypedQuery<ElementoCatalogo> query = em.createQuery(
                "SELECT e FROM ElementoCatalogo e WHERE e.titolo LIKE :titolo", ElementoCatalogo.class);
        query.setParameter("titolo", "%" + titolo + "%");
        return query.getResultList();
    }
}