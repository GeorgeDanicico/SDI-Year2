package factory.core.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


@Entity(name = "Sensor")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "Sensor")
public class Sensor extends BaseEntity<Long>{
    @Column(name = "name")
    private String name;

    @Column(name = "measurement")
    private Double measurement;
}