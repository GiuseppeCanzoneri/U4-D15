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
        libro.setIsbn("32988372256");
        libro.setTitolo("Test");
        libro.setAnnoPubblicazione(2020);
        libro.setNumeroPagine(192 );
        libro.setAutore("test");
        libro.setGenere("test");
        catalogoDAO.aggiungiElemento(libro);

        // Rimozione di un elemento dal catalogo dato il codice ISBN
//        catalogoDAO.rimuoviElemento("1234567890");

        // Ricerca per ISBN
        ElementoCatalogo elemento = catalogoDAO.cercaPerISBN("32988372256");
        if (elemento != null) {
            System.out.println("Elemento trovato: " + elemento.getTitolo());
        } else {
            System.out.println("Elemento non trovato.");
        }

        // Ricerca per anno di pubblicazione
        List<ElementoCatalogo> elementiPerAnno = catalogoDAO.cercaPerAnnoPubblicazione(2020);
        System.out.println("Elementi per anno di pubblicazione (2020):");
        for (ElementoCatalogo elemento1 : elementiPerAnno) {
            System.out.println(elemento1.getTitolo());
        }

        List<ElementoCatalogo> elementiPerAutore = catalogoDAO.cercaPerAutore("Tolkien");
        System.out.println("Elementi per autore (Tolkien):");
        for (ElementoCatalogo elemento1 : elementiPerAutore) {
            System.out.println(elemento1.getTitolo());
        }

        List<ElementoCatalogo> elementiPerTitolo = catalogoDAO.cercaPerTitolo("Il signore degli anelli");
        System.out.println("Elementi per titolo (Il signore degli anelli):");
        for (ElementoCatalogo elemento1 : elementiPerTitolo) {
            System.out.println(elemento1.getTitolo());
        }


        // Aggiunta di un utente
        Utente utente = new Utente();
        utente.setNumeroTessera("0005");
        utente.setNome("Babrizio");
        utente.setCognome("Rossi");
        utente.setDataNascita(LocalDate.of(1990, 1, 1));
        utenteDAO.aggiungiUtente(utente);

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

        // Ricerca di utenti per nome
        List<Utente> utentiPerNome = utenteDAO.cercaUtentiPerNome("Mario");
        if (!utentiPerNome.isEmpty()) {
            System.out.println("Utenti trovati per nome (Mario):");
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

        // Ricerca di utenti per cognome
        List<Utente> utentiPerCognome = utenteDAO.cercaUtentiPerCognome("Rossi");
        if (!utentiPerCognome.isEmpty()) {
            System.out.println("Utenti trovati per cognome (Rossi):");
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
        prestito.setDataRestituzionePrevista(LocalDate.now().plusDays(30));
        prestitoDAO.aggiungiPrestito(prestito);

        Long idElemento = 1L; // Esempio di identificatore di un elemento da restituire
        prestitoDAO.restituisciElemento(idElemento);

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
