package entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Rivista extends ElementoCatalogo {
    @Enumerated(EnumType.STRING)
    private Periodicita periodicita;

    
}