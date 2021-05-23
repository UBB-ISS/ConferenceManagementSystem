import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

import { Paper } from "../shared/paper.model";
import { PaperService } from "../shared/paper.service";

@Component({
  selector: 'app-review-assigned-papers',
  templateUrl: './review-assigned-papers.component.html',
  styleUrls: ['./review-assigned-papers.component.scss']
})
export class ReviewAssignedPapersComponent implements OnInit {
  userId: number = 0;
  conferenceId: number = 0;
  username: string = '';

  papersReadyForReview: Array<Paper> = {} as Array<Paper>;

  constructor(private router: Router, private route: ActivatedRoute,
              private paperService: PaperService) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.queryParams.userId;
    this.conferenceId = this.route.snapshot.queryParams.conferenceId;
    this.username = this.route.snapshot.queryParams.username;

    this.getPapersReadyForReview(this.userId);
  }

  goToRolePage(): void {
    this.router.navigate(['role'], {
      queryParams: {
        userId: this.userId,
        conferenceId: this.conferenceId,
        role: 'REVIEWER',
        username: this.username
      }
    }).then(_ => {});
  }

  getPapersReadyForReview(userId: number): void {
    this.paperService.getPapersReadyForReview(userId).subscribe(
      (papers) => {
        this.papersReadyForReview = papers.papersDTO;
      });
  }

  goToGiveReviewPage(paperId: number): void {
    this.router.navigate(['giveReview'], {
      queryParams: {
        userId: this.userId,
        conferenceId: this.conferenceId,
        username: this.username,
        paperId: paperId
      }
    }).then(_ => {});
  }
}
