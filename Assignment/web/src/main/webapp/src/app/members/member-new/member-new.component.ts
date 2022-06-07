import {Component, Input, OnInit} from '@angular/core';
import {Member} from "../shared/member.model";
import {MemberService} from "../shared/member.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from "@angular/common";
import {switchMap} from "rxjs";

@Component({
  selector: 'app-member-new',
  templateUrl: './member-new.component.html',
  styleUrls: ['./member-new.component.css']
})
export class MemberNewComponent implements OnInit {

  constructor(private memberService: MemberService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {

  }

  goBack(): void {
    this.location.back();
  }

  saveMember(name: string, dateOfBirth: string, addressId: string): void {


    this.memberService.save({ id: null, name, dateOfBirth: new Date(dateOfBirth), address: {
      id: parseInt(addressId), street: null, city: null,
      }})
      .subscribe(_ => this.goBack(),
                error => console.log(error));
  }

}
