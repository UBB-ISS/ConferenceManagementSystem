import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

import { PaperService } from "../shared/paper.service";
import { Paper } from "../shared/paper.model";
import {ReviewerPaperService} from "../shared/reviewer-paper.service";
import {ReviewerPaper} from "../shared/reviewer-paper.model";

@Component({
  selector: 'app-bid-proposal',
  templateUrl: './bid-proposal.component.html',
  styleUrls: ['./bid-proposal.component.scss']
})
export class BidProposalComponent implements OnInit {
  userId: number = 0;
  conferenceId: number = 0;
  username: string = '';
  paperId: number = 0;

  paper: Paper = {} as Paper;
  availablePaper: ReviewerPaper = {} as ReviewerPaper;
  ok: boolean = true;

  allPossibleAvailability: Array<string> = ['PLEASED', 'OK', 'REFUSE'];

  constructor(private paperService: PaperService, private availabilityService: ReviewerPaperService,
              private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.queryParams.userId;
    this.conferenceId = this.route.snapshot.queryParams.conferenceId;
    this.username = this.route.snapshot.queryParams.username;
    this.paperId = this.route.snapshot.queryParams.paperId;

    this.getPaperById(this.paperId);
    this.getAvailabilityForUserAndPaper(this.userId, this.paperId);
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

  addAndGoToRolePage(status: string): void {
    this.availabilityService.addAvailability(this.userId, this.paperId, false, status).subscribe(
      () => {
        this.router.navigate(['role'], {
          queryParams: {
            userId: this.userId,
            conferenceId: this.conferenceId,
            role: 'REVIEWER',
            username: this.username
          }
        }).then(_ => {});
      });
  }

  getPaperById(paperId: number) {
    this.paperService.getPaperById(paperId).subscribe(
      (paper) => {
        this.paper = paper;
      });
  }

  getAvailabilityForUserAndPaper(userId: number, paperId: number) {
    this.availabilityService.findAvailabilityByUserId(userId, paperId).subscribe(
      (availablePaper) => {
        this.availablePaper = availablePaper;
        if(this.availablePaper == null) this.ok = false;
      });
  }
}
