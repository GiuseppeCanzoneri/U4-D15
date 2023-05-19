package entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Libro extends ElementoCatalogo {
    private String autore;
    private String genere;


}
