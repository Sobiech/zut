package pl.kso.awmps.domain.entity;

import javax.persistence.Table;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;


@Data
@Entity
@Table(name = "car", schema = "makro" )
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue
    private Long id;
    private @NonNull
    String name;
}