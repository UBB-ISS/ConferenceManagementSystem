import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

import { User } from "../shared/user.model";
import { ReviewerPaper } from "../shared/reviewer-paper.model";
import { UserConferenceService } from "../shared/user-conference.service";
import { ReviewerPaperService } from "../shared/reviewer-paper.service";
import { PaperService } from "../shared/paper.service";

@Component({
  selector: 'app-send-paper-to-reviewer',
  templateUrl: './send-paper-to-reviewer.component.html',
  styleUrls: ['./send-paper-to-reviewer.component.scss']
})
export class SendPaperToReviewerComponent implements OnInit {
  userId: number = 0;
  conferenceId: number = 0;
  role: string = '';
  username: string = '';

  reviewers: Array<User> = {} as Array<User>;
  bidPapers: Array<ReviewerPaper> = {} as Array<ReviewerPaper>;

  constructor(private router: Router, private route: ActivatedRoute,
              private userConferenceService: UserConferenceService, private reviewerPaperService: ReviewerPaperService,
              private paperService: PaperService) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.queryParams.userId;
    this.conferenceId = this.route.snapshot.queryParams.conferenceId;
    this.role = this.route.snapshot.queryParams.role;
    this.username = this.route.snapshot.queryParams.username;

    this.getAllReviewers();
    this.getAllBidPapers();
  }

  goBack(): void {
    if(this.role === 'CHAIR') {
      this.router.navigate(['role'], {
        queryParams: {
          userId: this.userId,
          conferenceId: this.conferenceId,
          role: this.role,
          username: this.username
        }
      }).then(_ => {});
    } else {
      this.router.navigate(['roles'], {
        queryParams: {
          userId: this.userId,
          username: this.username,
          conferenceId: this.conferenceId
        }
      }).then(_ => {});
    }
  }

  getAllReviewers(): void {
    this.userConferenceService.getAllUsersFromAGivenConferenceWithAGivenRole(this.conferenceId, 'REVIEWER').subscribe(
      (users) => {
        this.reviewers = users.usersDTO;
      });
  }

  getAllBidPapers(): void {
    this.reviewerPaperService.getAllFromAGivenConference(this.conferenceId).subscribe(
      (papers) => {
        this.bidPapers = papers.reviewerPapersDTO;

        for(const bidPaper of this.bidPapers) {
          this.paperService.getPaperById(bidPaper.paperId).subscribe(paper => {
            bidPaper.paperTitle = paper.title;
          })
        }
      });
  }

  assignPaperToReviewer(userId: number, paperId: number, status: string): void {
    this.reviewerPaperService.findAvailabilityByUserId(userId, paperId).subscribe(
      (availability) => {
        if(availability.assigned) window.alert("You have already assigned this paper!");
        else {
          this.reviewerPaperService.changeStatus(availability.id, status).subscribe(()=>{
            window.alert("You have successfully assigned this paper!");
          })
        }
      });
  }
}
