package factory.serverapp.repository;

import factory.serverapp.model.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SensorRepository {
    @Autowired
    private JdbcOperations jdbcOperations;

    public void save(Sensor entity) throws Exception{
        Optional.ofNullable(entity).orElseThrow(() -> new Exception("Entity must not be null!"));

        System.out.println("Entity has been added");
        insertData(entity);
    }

    private void insertData(Sensor sensor) {
        var sql = "INSERT INTO sensor(name, measurement) VALUES(?, ?)";
        jdbcOperations.update(sql, sensor.getName(), sensor.getMeasurement());
    }
}
