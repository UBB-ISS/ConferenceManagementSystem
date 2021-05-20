import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {UserConference} from "./user-conference.model";
import {Observable} from "rxjs";

@Injectable()
export class UserConferenceService {
  private url = 'http://localhost:8080/cms/';

  constructor(private httpClient: HttpClient) {}

  getUserConference( userId: number,  conferenceId: number): Observable<UserConference> {
    return this.httpClient.get<UserConference>(this.url + `getUserConference/${userId}/${conferenceId}`);
  }
}
