import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { Observable } from "rxjs";

import { PaperReview } from "../shared/paper-review.model";
import { PaperReviewService } from "../shared/paper-review.service";
import { PaperService } from "../shared/paper.service";
import { ResultService } from "../shared/result.service";
import {Result} from "../shared/result.model";

@Component({
  selector: 'app-send-results',
  templateUrl: './send-results.component.html',
  styleUrls: ['./send-results.component.scss']
})
export class SendResultsComponent implements OnInit {
  userId: number = 0;
  conferenceId: number = 0;
  role: string = '';
  username: string = '';

  paperReviewsFromConference: Array<PaperReview> = {} as Array<PaperReview>;

  constructor(private router: Router, private route: ActivatedRoute,
              private paperReviewService: PaperReviewService, private paperService: PaperService,
              private resultService: ResultService) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.queryParams.userId;
    this.conferenceId = this.route.snapshot.queryParams.conferenceId;
    this.role = this.route.snapshot.queryParams.role;
    this.username = this.route.snapshot.queryParams.username;

    this.allPaperReviewFromAGivenConference(this.conferenceId);
  }

  goToRolePage(): void {
    this.router.navigate(['role'], {
      queryParams: {
        userId: this.userId,
        conferenceId: this.conferenceId,
        role: this.role,
        username: this.username
      }
    }).then(_ => {});
  }

  allPaperReviewFromAGivenConference(conferenceId: number): void {
    this.paperReviewService.allPaperReviewFromAGivenConference(conferenceId).subscribe(
      paperReviews => {
        this.paperReviewsFromConference = paperReviews.paperReviewsDTO;
        for (const paperReview of this.paperReviewsFromConference) {
          this.paperService.getAuthorIDForAGivenPaper(paperReview.paperId).subscribe(authorId => {
            paperReview.authorID = authorId;
          })
          this.getAuthorForPaper(paperReview.paperId).subscribe(authorName => {
            paperReview.authorName = authorName
          });
          this.paperService.getPaperById(paperReview.paperId).subscribe(paper => {
            paperReview.paperTitle = paper.title;
          });
        }
      });
  }

  getAuthorForPaper(paperId: number): Observable<string> {
    return this.paperService.getAuthorForAGivenPaper(paperId);
  }

  addResult(paperId: number, qualifier: string, authorId?: number): void {
    if (!authorId)
      return
    let result: Result;
    this.resultService.getResultByAuthorAndPaper(authorId, paperId, qualifier).subscribe(r => {
      result = r;

      if(result == null) {
        this.resultService.addResult(authorId, paperId, qualifier).subscribe(_ => {
          window.alert("The result was successfully sent!");
        })
      } else {
        window.alert("You have already sent the result!");
      }
    })
  }
}
