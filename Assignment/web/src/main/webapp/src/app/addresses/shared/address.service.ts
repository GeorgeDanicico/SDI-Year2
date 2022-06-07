import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";

import {Address, AddressDto} from "./address.model";

import {Observable} from "rxjs";
import {map} from "rxjs/operators";


@Injectable()
export class AddressService {
  private addressesUrl = 'http://localhost:8080/api/addresses/';

  constructor(private httpClient: HttpClient) {
  }

  getAddresses(params: any): Observable<AddressDto> {
    return this.httpClient
      .get<AddressDto>(this.addressesUrl, { params });
  }

  getAddress(params, id: number): Observable<Address> {
    return this.getAddresses(params)
      .pipe(
        map(addresssdto => addresssdto.addresses.find(address => address.id === id))
      );
  }

  save(address: Address) {
    return this.httpClient.post<Address>(this.addressesUrl, address);
  }

  delete(addressId: number) {
    const url = `${this.addressesUrl}${addressId}`;
    return this.httpClient.delete(url);
  }

  update(address: Address): Observable<Address> {
    const url = `${this.addressesUrl}${address.id}`;
    return this.httpClient
      .put<Address>(url, address);
  }

}
