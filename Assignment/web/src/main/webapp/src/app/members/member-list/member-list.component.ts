import {ApplicationRef, Component, OnInit} from '@angular/core';
import { Member } from "../shared/member.model";
import { MemberService } from "../shared/member.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-member-list',
  templateUrl: './member-list.component.html',
  styleUrls: ['./member-list.component.css']
})
export class MemberListComponent implements OnInit {
  errorMessage: string;
  members: Member[];
  selectedMember: Member;
  memberBirthDate: string;
  page = 1;
  count = 0;

  constructor(private memberService: MemberService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getMembers();
  }

  getMembers() {
    const params = { page: this.page - 1 };

    this.memberService.getMembers(params)
      .subscribe(membersDto => {
        this.members = membersDto.members;
        this.count = membersDto.totalItems;
      })
  }

  handlePageChange(event: any) {
    this.page = event;
    this.getMembers();
  }

  onSelect(member: Member): void {
    this.selectedMember = member;
    const d = new Date(member.dateOfBirth);
    this.memberBirthDate = d.getFullYear() + "-" + d.getMonth() + "-" + d.getDay();
  }

  onDelete(): void {
    this.getMembers();
    this.selectedMember = null;
  }

  deleteMember(): void {
    this.memberService.delete(this.selectedMember.id).subscribe(_ => this.onDelete());
  }

  goToAdd(): void {
    this.router.navigate(['member/new']);
  }

  save(): void {
    this.selectedMember.dateOfBirth = new Date(this.memberBirthDate);
    console.log(this.selectedMember.dateOfBirth);
    this.memberService.update(this.selectedMember)
      .subscribe(_ => this.getMembers());
  }

  cancel(): void {
    this.selectedMember = null;
    this.getMembers();
  }
}
