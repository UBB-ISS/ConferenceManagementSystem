import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

import { Conference, Conferences } from "./conference.model";
import {Date} from "./date.model";

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

  addConference(name: string, price: number, date: any, bidding: any, submit: any, review: any): Observable<any> {
    return this.httpClient.post(this.url + `conferences`, new Conference(0, name, price, date, bidding, submit, review));
  }

  changeDeadlines(id: number, bidding: any, submit: any, review: any): Observable<any> {
    let conference = new Conference(id, '', 0, new Date("2012-12-12"), bidding, submit, review)
    return this.httpClient.put(this.url + `changeDeadlines`, conference);
  }
}
