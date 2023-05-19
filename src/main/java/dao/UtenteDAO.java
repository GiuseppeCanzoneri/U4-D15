package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entities.Utente;

import java.util.List;

public class UtenteDAO {
    private static EntityManagerFactory emf;
    private EntityManager em;

    public UtenteDAO() {
        emf = Persistence.createEntityManagerFactory("U4D15Progetto");
        em = emf.createEntityManager();
    }

    public void aggiungiUtente(Utente utente) {
        em.getTransaction().begin();
        em.persist(utente);
        em.getTransaction().commit();
    }

    public void rimuoviUtente(String numeroTessera) {
        em.getTransaction().begin();
        Utente utente = em.find(Utente.class, numeroTessera);
        if (utente != null) {
            em.remove(utente);
        }
        em.getTransaction().commit();
    }

    public Utente cercaUtentePerNumeroTessera(String numeroTessera) {
        return em.find(Utente.class, numeroTessera);
    }

    public List<Utente> cercaUtentiPerNome(String nome) {
        TypedQuery<Utente> query = em.createQuery(
                "SELECT u FROM Utente u WHERE u.nome = :nome", Utente.class);
        query.setParameter("nome", nome);
        return query.getResultList();
    }

    public List<Utente> cercaUtentiPerCognome(String cognome) {
        TypedQuery<Utente> query = em.createQuery(
                "SELECT u FROM Utente u WHERE u.cognome = :cognome", Utente.class);
        query.setParameter("cognome", cognome);
        return query.getResultList();
    }
}
