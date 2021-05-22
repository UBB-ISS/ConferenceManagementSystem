import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

import { UserConference } from "./user-conference.model";
import { Users } from "./user.model";

@Injectable()
export class UserConferenceService {
  private url = 'http://localhost:8080/cms/';

  constructor(private httpClient: HttpClient) {}

  getUserConference( userId: number,  conferenceId: number): Observable<UserConference> {
    return this.httpClient.get<UserConference>(this.url + `getUserConference/${userId}/${conferenceId}`);
  }

  getAllUsersFromAGivenConferenceWithAGivenRole(conferenceId: number, role: string): Observable<Users> {
    return this.httpClient.get<Users>(this.url + `allUsersFromAGivenConferenceWithAGivenRole/${conferenceId}/${role}`);
  }
}
