import { Component, OnInit } from '@angular/core';
import {Address} from "../shared/address.model";
import {AddressService} from "../shared/address.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-address-list',
  templateUrl: './address-list.component.html',
  styleUrls: ['./address-list.component.css']
})
export class AddressListComponent implements OnInit {
  errorMessage: string;
  addresses: Address[];
  selectedAddress: Address;
  page = 1;
  count = 0;

  constructor(private addressService: AddressService,
              private router: Router) {
  }



  ngOnInit(): void {
    this.getAddresses();
  }

  getAddresses() {
    const params = { page: this.page - 1 };

    this.addressService.getAddresses(params)
      .subscribe(addressesDto => {
        this.addresses = addressesDto.addresses;
        this.count = addressesDto.totalItems;

        console.log(addressesDto);
      })
  }

  handlePageChange(event: any) {
    this.page = event;
    this.getAddresses();
  }

  onSelect(address: Address): void {
    this.selectedAddress = address;
  }

  onDelete(): void {
    this.getAddresses();
    this.selectedAddress = null;
  }

  deleteAddress(): void {
    this.addressService.delete(this.selectedAddress.id).subscribe(_ => this.onDelete());
  }

  goToAdd(): void {
    this.router.navigate(['address/new']);
  }

  cancel(): void {
    this.selectedAddress = null;
    this.getAddresses();
  }

  save(): void {
    this.addressService.update(this.selectedAddress)
      .subscribe(_ => this.getAddresses());
  }

}
