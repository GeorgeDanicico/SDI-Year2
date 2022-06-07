package factory.serverapp.service;

import factory.serverapp.model.Sensor;
import factory.serverapp.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
public class SensorService {
    private ExecutorService executorService;
    private SensorRepository sensorRepository;

    public SensorService(SensorRepository sr1, ExecutorService es1) {
        sensorRepository = sr1;
        executorService = es1;
    }



    public Future<String> addEntity(Sensor entity) {
        return executorService.submit(() -> {
            sensorRepository.save(entity);
            return "Member added successfully";
        });
    }
}
