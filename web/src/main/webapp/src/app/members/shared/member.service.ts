import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";

import {Member, MemberDto} from "./member.model";

import {Observable} from "rxjs";
import {map} from "rxjs/operators";


@Injectable()
export class MemberService {
  private membersUrl = 'http://localhost:8080/api/members/';

  constructor(private httpClient: HttpClient) {
  }

  getMembers(params: any): Observable<MemberDto> {
    return this.httpClient
      .get<MemberDto>(this.membersUrl, { params });
  }

  // getMember(id: number): Observable<Member> {
  //   return this.getMembers()
  //     .pipe(
  //       map(membersdto => membersdto.members.find(member => member.id === id))
  //     );
  // }

  save(member: Member) {
    return this.httpClient.post<Member>(this.membersUrl, member);
  }

  delete(memberId: number) {
    const url = `${this.membersUrl}${memberId}`;
    return this.httpClient.delete(url);
  }

  update(member: Member): Observable<Member> {
    const url = `${this.membersUrl}${member.id}`;
    return this.httpClient
      .put<Member>(url, member);
  }

}
