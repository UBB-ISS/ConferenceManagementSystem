import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

import { Papers } from "./paper.model";

@Injectable()
export class PaperService {
  private url = 'http://localhost:8080/cms/';

  constructor(private httpClient: HttpClient) {
  }

  getPapersOfAUserInAConference(userId: number, conferenceId: number): Observable<Papers> {
    return this.httpClient.get<Papers>(this.url + `papersOfAUserInAConference/${userId}/${conferenceId}`);
  }
}
