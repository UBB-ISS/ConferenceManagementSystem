import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

import { ReviewerPaper, ReviewerPapers } from "./reviewer-paper.model";

@Injectable()
export class ReviewerPaperService {
  private url = 'http://localhost:8080/cms/';

  constructor(private httpClient: HttpClient) {
  }

  getAll(): Observable<ReviewerPapers> {
    return this.httpClient.get<ReviewerPapers>(this.url + `availabilities`);
  }

  getAllFromAGivenConference(conferenceId: number): Observable<ReviewerPapers> {
    return this.httpClient.get<ReviewerPapers>(this.url + `allAvailabilitiesFromAGivenConference/${conferenceId}`);
  }

  addAvailability(userId: number, paperId: number, assigned: boolean, availability: string): Observable<any> {
    return this.httpClient.post(this.url + `availabilities`, new ReviewerPaper(0, userId, paperId, assigned, availability));
  }

  changeStatus(id: number, status: string): Observable<any> {
    console.log("service");
    console.log(id);
    let reviewerPaper = new ReviewerPaper(id, 0, 0,true, status);
    console.log(reviewerPaper);
    return this.httpClient.put(this.url + `changeStatusForAvailability/${id}`, reviewerPaper);
  }

  findAvailabilityByUserId(userId: number, paperId: number): Observable<ReviewerPaper> {
    return this.httpClient.get<ReviewerPaper>(this.url + `availabilityByUserIdAndPaperId/${userId}/${paperId}`);
  }
}
