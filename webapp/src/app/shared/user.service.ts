import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

import { User, Users } from "./user.model";
import {UserConference} from "./userConference.model";

@Injectable()
export class UserService {
  private url = 'http://localhost:8080/cms/';

  constructor(private httpClient: HttpClient) {
  }

  getAllUsers(): Observable<Users> {
    return this.httpClient.get<Users>(this.url + `users`);
  }

  loginUser(username: string, password: string): Observable<User> {
    return this.httpClient.get<User>(this.url + `login/${username}/${password}`);
  }

  createAccount(name: string, email: string, affiliation: string, website: string, username: string, password: string): Observable<any> {
    return this.httpClient.post(this.url + `createAccount`, new User(name, email, affiliation, website, username, password));
  }

  getAllRolesForAGivenUser(id: number): Observable<Array<string>> {
    return this.httpClient.get<Array<string>>(this.url + `allRolesForAGivenUser/${id}`);
  }

  getAllRolesForAGivenUserInAGivenConference(userId: number, conferenceId: number): Observable<Array<string>> {
    return this.httpClient.get<Array<string>>(this.url + `allRolesForAGivenUserInAGivenConference/${userId}/${conferenceId}`);
  }

  addUserToConference(userId: number, conferenceId: number, paid: boolean, role: string): Observable<any> {
    return this.httpClient.post(this.url + `userConferences`, new UserConference(conferenceId, userId, role, paid));
  }
}
