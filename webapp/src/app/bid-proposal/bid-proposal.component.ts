import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

import { PaperService } from "../shared/paper.service";
import { Paper } from "../shared/paper.model";

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

  allPossibleAvailability: Array<String> = ['PLEASED', 'OK', 'REFUSE'];

  constructor(private paperService: PaperService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.queryParams.userId;
    this.conferenceId = this.route.snapshot.queryParams.conferenceId;
    this.username = this.route.snapshot.queryParams.username;
    this.paperId = this.route.snapshot.queryParams.paperId;

    this.getPaperById(this.paperId);
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

  getPaperById(paperId: number) {
    this.paperService.getPaperById(paperId).subscribe(
      (paper) => {
        this.paper = paper;
      });
  }
}
