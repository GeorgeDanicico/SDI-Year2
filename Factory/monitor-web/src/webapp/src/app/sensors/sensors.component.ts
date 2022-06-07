import { Component, OnInit } from '@angular/core';
import {Sensor} from "../shared/sensor.model";
import {SensorService} from "../shared/sensor.service";

@Component({
  selector: 'app-sensors',
  templateUrl: './sensors.component.html',
  styleUrls: ['./sensors.component.css']
})
export class SensorsComponent implements OnInit {

  sensors: Sensor[];


  constructor(private sensorService: SensorService) { }

  ngOnInit(): void {
    this.getSensors();
  }

  getSensors() {
    this.sensorService.getSensors()
      .subscribe(sensors => {
        this.sensors = sensors;
        console.log(sensors);
      })
  }

}
