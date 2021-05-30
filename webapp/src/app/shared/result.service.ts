import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

import { Result, Results } from "./result.model";

@Injectable()
export class ResultService {
  private url = 'http://localhost:8080/cms/';

  constructor(private httpClient: HttpClient) {
  }

  getAllForAuthor(authorId: number): Observable<Results> {
    return this.httpClient.get<Results>(this.url + `allResultsForAuthor/${authorId}`);
  }

  addResult(authorId: number, paperId: number, qualifier: string): Observable<any> {
    return this.httpClient.post(this.url + `addResult`, new Result(0, authorId, paperId, qualifier));
  }

  getResultByAuthorAndPaper(authorId: number, paperId: number, qualifier: string): Observable<Result> {
    return this.httpClient.get<Result>(this.url + `getResultByAuthorAndPaper/${authorId}/${paperId}/${qualifier}`);
  }

  getAllForAuthorAndConference(authorId: number, conferenceId: number): Observable<Results> {
    return this.httpClient.get<Results>(this.url + `getAllForAuthorAndConference/${authorId}/${conferenceId}`);
  }
}
