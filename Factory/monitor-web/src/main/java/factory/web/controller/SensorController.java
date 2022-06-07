package factory.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import factory.core.model.Sensor;
import factory.core.service.SensorService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/sensor")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping(value = "/")
    List<Sensor> getAllSensors() {
        return this.sensorService.getAll();
    }

}