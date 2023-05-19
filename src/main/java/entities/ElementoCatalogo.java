package entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ElementoCatalogo {
    @Id
    private String isbn;
    private String titolo;
    private int annoPubblicazione;
    private int numeroPagine;

  
}