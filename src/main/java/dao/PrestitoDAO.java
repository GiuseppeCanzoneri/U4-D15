package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entities.Prestito;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class PrestitoDAO {
    private static EntityManagerFactory emf;
    private EntityManager em;

    public PrestitoDAO() {
        emf = Persistence.createEntityManagerFactory("U4D15Progetto");
        em = emf.createEntityManager();
    }

    public void aggiungiPrestito(Prestito prestito) {
        em.getTransaction().begin();
        em.persist(prestito);
        em.getTransaction().commit();
    }

    public void restituisciElemento(Long idElemento) {
        em.getTransaction().begin();
        Prestito prestito = em.find(Prestito.class, idElemento);
        if (prestito != null) {
            prestito.setDataRestituzioneEffettiva(LocalDate.now());
        }
        em.getTransaction().commit();
    }

    public List<Prestito> ricercaPrestitiPerUtente(String numeroTessera) {
        TypedQuery<Prestito> query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :numeroTessera", Prestito.class);
        query.setParameter("numeroTessera", numeroTessera);
        return query.getResultList();
    }

    public List<Prestito> ricercaPrestitiScaduti() {
        LocalDate oggi = LocalDate.now();
        try {
            TypedQuery<Prestito> query = em.createQuery("SELECT p FROM Prestito p WHERE p.dataRestituzioneEffettiva IS NULL AND p.dataRestituzionePrevista < :oggi", Prestito.class);
            query.setParameter("oggi", oggi);
            return query.getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList();
        }
    }

        
    }

