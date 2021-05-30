import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

import { Paper, Papers } from "./paper.model";

@Injectable()
export class PaperService {
  private url = 'http://localhost:8080/cms/';

  constructor(private httpClient: HttpClient) {
  }

  getPapersOfAUserInAConference(userId: number, conferenceId: number): Observable<Papers> {
    return this.httpClient.get<Papers>(this.url + `papersOfAUserInAConference/${userId}/${conferenceId}`);
  }

  getFinalPapersFromAConference(userId: number, conferenceId: number): Observable<Papers> {
    return this.httpClient.get<Papers>(this.url + `finalPapersFromAConference/${userId}/${conferenceId}`);
  }

  getPaperById(paperId: number): Observable<Paper> {
    return this.httpClient.get<Paper>(this.url + `papers/${paperId}`);
  }

  addPaper(userConferenceId: number, title: string, keywords: string, paperText: string,
           abstractText: string, finalized: boolean, accepted: boolean): Observable<any> {
    return this.httpClient.post(this.url + `papers`,
      new Paper(0, userConferenceId, title, keywords, paperText, abstractText, finalized, accepted))
  }

  updatePaper(paperId: number, title: string, paperText: string, abstractText: string, keywords: string, finalized: boolean, accepted: boolean) {
    return this.httpClient.post(this.url + `papers/update`,
      new Paper(paperId, 0, title, keywords, paperText, abstractText, finalized, accepted))
  }

  getPapersReadyForReview(userId: number, conferenceId: number): Observable<Papers> {
    return this.httpClient.get<Papers>(this.url + `papersReadyForReview/${userId}/${conferenceId}`);
  }

  getAuthorForAGivenPaper(paperId: number): Observable<string> {
    return this.httpClient.get<string>(this.url + `getAuthorForAGivenPaper/${paperId}`);
  }

  getAuthorIDForAGivenPaper(paperId: number): Observable<number> {
    return this.httpClient.get<number>(this.url + `getAuthorIDForAGivenPaper/${paperId}`);
  }
}
