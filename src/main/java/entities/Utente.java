package entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter

public class Utente {
    @Id
    private String numeroTessera;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;

    // Costruttori, getter e setter
}