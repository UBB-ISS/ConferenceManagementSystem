import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

import { PaperReview } from "./paper-review.model";
import { PaperReviews } from "./paper-review.model";

@Injectable()
export class PaperReviewService {
  private url = 'http://localhost:8080/cms/';

  constructor(private httpClient: HttpClient) {
  }

  getAllPaperReviews(): Observable<PaperReviews> {
    return this.httpClient.get<PaperReviews>(this.url + `paperReviews`);
  }

  addPaperReview(reviewerId: number, paperId: number, recommendation: string, qualifier: string): Observable<any> {
    return this.httpClient.post(this.url + `addPaperReview`,
      new PaperReview(0, reviewerId, paperId, recommendation, qualifier));
  }

  updatePaperReview(id: number, reviewerId: number, paperId: number, recommendation: string, qualifier: string): Observable<any> {
    return this.httpClient.put(this.url + `updatePaperReview`,
      new PaperReview(id, reviewerId, paperId, recommendation, qualifier));
  }

  findPaperReviewByReviewerIdAndPaperId(reviewerId: number, paperId: number): Observable<PaperReview> {
    return this.httpClient.get<PaperReview>(this.url + `findPaperReviewByReviewerIdAndPaperId/${reviewerId}/${paperId}`);
  }
}
