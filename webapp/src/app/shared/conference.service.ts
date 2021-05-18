import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

import { Conferences } from "./conference.model";

@Injectable()
export class ConferenceService {
  private url = 'http://localhost:8080/cms/';

  constructor(private httpClient: HttpClient) {
  }

  getAllConferences(): Observable<Conferences> {
    return this.httpClient.get<Conferences>(this.url + `conferences`);
  }
}
