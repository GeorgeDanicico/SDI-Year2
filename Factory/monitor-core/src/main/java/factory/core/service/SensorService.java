package factory.core.service;

import factory.core.model.Sensor;
import factory.core.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SensorService {
    @Autowired
    private SensorRepository sensorRepository;

    public List<Sensor> getAll() {
        return sensorRepository.findAll();
    }
}
