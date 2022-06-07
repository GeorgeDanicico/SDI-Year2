package factory.core.repository;

import factory.core.model.Sensor;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends IRepository<Sensor, Long>{
}
