import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SensorsComponent } from './sensors/sensors.component';
import {HttpClientModule} from "@angular/common/http";
import {SensorService} from "./shared/sensor.service";

@NgModule({
  declarations: [
    AppComponent,
    SensorsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [SensorService],
  bootstrap: [AppComponent]
})
export class AppModule { }
