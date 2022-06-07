import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Sensor} from "./sensor.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SensorService {
  private url = 'http://localhost:8080/api/';

  constructor(private httpClient:HttpClient) { }

  getSensors(): Observable<Sensor[]> {
    return this.httpClient.get<Sensor[]>(`${this.url}sensor/`)
  }
}
