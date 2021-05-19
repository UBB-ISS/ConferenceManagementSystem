import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

import { Conference, Conferences } from "./conference.model";

@Injectable()
export class ConferenceService {
  private url = 'http://localhost:8080/cms/';

  constructor(private httpClient: HttpClient) {
  }

  getAllConferences(): Observable<Conferences> {
    return this.httpClient.get<Conferences>(this.url + `conferences`);
  }

  getConferenceById(conferenceId: number): Observable<Conference> {
    return this.httpClient.get<Conference>(this.url + `conference/${conferenceId}`);
  }
}
