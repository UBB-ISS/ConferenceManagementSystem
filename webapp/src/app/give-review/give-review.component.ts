import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

import { Paper } from "../shared/paper.model";
import { PaperService } from "../shared/paper.service";
import { PaperReview } from "../shared/paper-review.model";
import { PaperReviewService } from "../shared/paper-review.service";

@Component({
  selector: 'app-give-review',
  templateUrl: './give-review.component.html',
  styleUrls: ['./give-review.component.scss']
})
export class GiveReviewComponent implements OnInit {
  userId: number = 0;
  conferenceId: number = 0;
  username: string = '';
  paperId: number = 0;

  paper: Paper = {} as Paper;

  allPossibleQualifiers: Array<string> = ['STRONG_ACCEPT', 'ACCEPT', 'WEAK_ACCEPT',
                                          'BORDERLINE',
                                          'WEAK_REJECT', 'REJECT', 'STRONG_REJECT'];

  paperReviews: Array<PaperReview> = {} as Array<PaperReview>;

  constructor(private router: Router, private route: ActivatedRoute,
              private paperService: PaperService, private paperReviewService: PaperReviewService) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.queryParams.userId;
    this.conferenceId = this.route.snapshot.queryParams.conferenceId;
    this.username = this.route.snapshot.queryParams.username;
    this.paperId = this.route.snapshot.queryParams.paperId;

    this.getPaperById(this.paperId);

    this.getPaperReviews();
  }

  goToReviewAssignedPapersPage(): void {
    this.router.navigate(['reviewAssignedPapers'], {
      queryParams: {
        userId: this.userId,
        conferenceId: this.conferenceId,
        username: this.username
      }
    }).then(_ => {});
  }

  getPaperById(paperId: number): void {
    this.paperService.getPaperById(paperId).subscribe(
      (paper) => {
        this.paper = paper;
      });
  }

  getPaperReviews(): void {
    this.paperReviewService.getAllPaperReviews().subscribe(
      (papers) => {
        this.paperReviews = papers.paperReviewsDTO;
      });
  }

  addOrUpdateQualifier(qualifier: string, recommendation: string): void {
    if(qualifier == '') {
      window.alert("You cannot assign a review without a qualifier!");
    } else {
      let operation = '';

      for(let pr of this.paperReviews) {
        if(pr.reviewerId == this.userId && pr.paperId == this.paperId) {
          operation = 'update';
          this.paperReviewService.findPaperReviewByReviewerIdAndPaperId(this.userId, this.paperId).subscribe(
            (paperReview) => {
              this.paperReviewService.updatePaperReview(paperReview.id, this.userId, this.paperId, recommendation, qualifier).subscribe(
                () => {
                  this.getPaperReviews();
                });
            });
        }
      }

      if(operation === '') {
        this.paperReviewService.addPaperReview(this.userId, this.paperId, recommendation, qualifier).subscribe(
          () => {
            this.getPaperReviews();
          });
      }
    }
  }
}
