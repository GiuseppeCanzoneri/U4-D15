package main;

import java.time.LocalDate;
import java.util.List;

import dao.CatalogoDAO;
import dao.PrestitoDAO;
import dao.UtenteDAO;
import entities.*;

public class Main {
    public static void main(String[] args) {
        // Esempio di utilizzo dei DAO
        CatalogoDAO catalogoDAO = new CatalogoDAO();
        PrestitoDAO prestitoDAO = new PrestitoDAO();
        UtenteDAO utenteDAO = new UtenteDAO();
        

        // Aggiunta di un elemento al catalogo
        Libro libro = new Libro();
        libro.setIsbn("3298");
        libro.setTitolo("Tutto sui panini");
        libro.setAnnoPubblicazione(2021);
        libro.setNumeroPagine(129 );
        libro.setAutore("Nizar Carlons");
        libro.setGenere("Panini");
        catalogoDAO.aggiungiElemento(libro);

        // Rimozione di un elemento dal catalogo dato il codice ISBN
       catalogoDAO.rimuoviElemento("32988372256");
       System.out.println("-------------------------");
        // Ricerca per ISBN
        ElementoCatalogo elemento = catalogoDAO.cercaPerISBN("3298");
        if (elemento != null) {
            System.out.println("Elemento trovato: " + elemento.getTitolo());
        } else {
            System.out.println("Elemento non trovato.");
        }
        System.out.println("-------------------------");
        
        // Ricerca per anno di pubblicazione
        List<ElementoCatalogo> elementiPerAnno = catalogoDAO.cercaPerAnnoPubblicazione(2021);
        System.out.println("Elementi per anno di pubblicazione (2021):");
        for (ElementoCatalogo elemento1 : elementiPerAnno) {
            System.out.println(elemento1.getTitolo());
        }
        System.out.println("-------------------------");
        
        List<ElementoCatalogo> elementiPerAutore = catalogoDAO.cercaPerAutore("Nizar Carlons");
        System.out.println("Elementi per autore (Nizar Carlons):");
        for (ElementoCatalogo elemento1 : elementiPerAutore) {
            System.out.println(elemento1.getTitolo());
        }
        System.out.println("-------------------------");
        List<ElementoCatalogo> elementiPerTitolo = catalogoDAO.cercaPerTitolo("Il signore degli anelli");
        System.out.println("Elementi per titolo (Il signore degli anelli):");
        for (ElementoCatalogo elemento1 : elementiPerTitolo) {
            System.out.println(elemento1.getTitolo());
        }


        // Aggiunta di un utente
        Utente utente = new Utente();
        utente.setNumeroTessera("0005");
        utente.setNome("Eleonora");
        utente.setCognome("Marrone");
        utente.setDataNascita(LocalDate.of(2003, 3, 3));
        utenteDAO.aggiungiUtente(utente);
        System.out.println("-------------------------");
     // Ricerca di un utente per numero di tessera
        Utente utenteTrovato = utenteDAO.cercaUtentePerNumeroTessera("0005");
        if (utenteTrovato != null) {
            System.out.println("Utente trovato:");
            System.out.println("Numero tessera: " + utenteTrovato.getNumeroTessera());
            System.out.println("Nome: " + utenteTrovato.getNome());
            System.out.println("Cognome: " + utenteTrovato.getCognome());
            System.out.println("Data di nascita: " + utenteTrovato.getDataNascita());
        } else {
            System.out.println("Utente non trovato per il numero di tessera specificato.");
        }
        System.out.println("-------------------------");
        // Ricerca di utenti per nome
        List<Utente> utentiPerNome = utenteDAO.cercaUtentiPerNome("Eleonora");
        if (!utentiPerNome.isEmpty()) {
            System.out.println("Utenti trovati per nome (Eleonora):");
            for (Utente utente1 : utentiPerNome) {
                System.out.println("Numero tessera: " + utente1.getNumeroTessera());
                System.out.println("Nome: " + utente1.getNome());
                System.out.println("Cognome: " + utente1.getCognome());
                System.out.println("Data di nascita: " + utente1.getDataNascita());
                System.out.println("-------------------------");
            }
        } else {
            System.out.println("Nessun utente trovato per il nome specificato.");
        }
        System.out.println("-------------------------");
        
        // Ricerca di utenti per cognome
        List<Utente> utentiPerCognome = utenteDAO.cercaUtentiPerCognome("Argento");
        if (!utentiPerCognome.isEmpty()) {
            System.out.println("Utenti trovati per cognome (Argento):");
            for (Utente utente1 : utentiPerCognome) {
                System.out.println("Numero tessera: " + utente1.getNumeroTessera());
                System.out.println("Nome: " + utente1.getNome());
                System.out.println("Cognome: " + utente1.getCognome());
                System.out.println("Data di nascita: " + utente1.getDataNascita());
                System.out.println("-------------------------");
            }
        } else {
            System.out.println("Nessun utente trovato per il cognome specificato.");
        }


        // Aggiunta di un prestito
        Prestito prestito = new Prestito();
        prestito.setUtente(utente);
        prestito.setElementoPrestato(libro);
        prestito.setDataInizioPrestito(LocalDate.now());
        prestito.setDataRestituzionePrevista(LocalDate.of(2023, 5, 17));
        prestitoDAO.aggiungiPrestito(prestito);

        Long idElemento = 1L; // Esempio di identificatore di un elemento da restituire
        prestitoDAO.restituisciElemento(idElemento);
        System.out.println("-------------------------");
     // Ricerca dei prestiti per utente
        List<Prestito> prestitiPerUtente = prestitoDAO.ricercaPrestitiPerUtente("0004");
        if (!prestitiPerUtente.isEmpty()) {
            System.out.println("Prestiti trovati per l'utente con numero di tessera 0004:");
            for (Prestito prestito1 : prestitiPerUtente) {
                System.out.println("ID prestito: " + prestito1.getId());
                System.out.println("Data inizio prestito: " + prestito1.getDataInizioPrestito());
                System.out.println("Data restituzione prevista: " + prestito1.getDataRestituzionePrevista());
                System.out.println("Data restituzione effettiva: " + prestito1.getDataRestituzioneEffettiva());
                System.out.println("-------------------------");
            }
        } else {
            System.out.println("Nessun prestito trovato per l'utente con numero di tessera 0004.");
        }

        System.out.println("-------------------------");
        
        // Ricerca dei prestiti scaduti e non ancora restituiti
        List<Prestito> prestitiScaduti = prestitoDAO.ricercaPrestitiScaduti();
        if (!prestitiScaduti.isEmpty()) {
            System.out.println("Prestiti scaduti e non ancora restituiti:");
            for (Prestito prestito1 : prestitiScaduti) {
                System.out.println("ID prestito: " + prestito1.getId());
                System.out.println("Data inizio prestito: " + prestito1.getDataInizioPrestito());
                System.out.println("Data restituzione prevista: " + prestito1.getDataRestituzionePrevista());
                System.out.println("Data restituzione effettiva: " + prestito1.getDataRestituzioneEffettiva());
                System.out.println("-------------------------");
            }
        } else {
            System.out.println("Nessun prestito scaduto e non ancora restituito.");
        }

    }
}
