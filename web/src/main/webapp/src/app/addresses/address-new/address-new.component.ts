import { Component, OnInit } from '@angular/core';
import {AddressService} from "../shared/address.service";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'app-address-new',
  templateUrl: './address-new.component.html',
  styleUrls: ['./address-new.component.css']
})
export class AddressNewComponent implements OnInit {

  constructor(private addressService: AddressService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {

  }

  goBack(): void {
    this.location.back();
  }

  saveAddress(city: string, street: string): void {
    this.addressService.save({ id: null, city, street,})
      .subscribe(_ => this.goBack(),
        error => console.log(error));
  }

}
