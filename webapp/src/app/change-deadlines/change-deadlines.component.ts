import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

import { ConferenceService } from "../shared/conference.service";
import { Date } from "../shared/date.model";

@Component({
  selector: 'app-change-deadlines',
  templateUrl: './change-deadlines.component.html',
  styleUrls: ['./change-deadlines.component.scss']
})
export class ChangeDeadlinesComponent implements OnInit {
  userId: number = 0;
  username: string = '';
  conferenceId: number = 0;

  constructor(private conferenceService: ConferenceService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.queryParams.userId;
    this.username = this.route.snapshot.queryParams.username;
    this.conferenceId = this.route.snapshot.queryParams.conferenceId;
  }

  goToConferencesPage(): void {
    this.router.navigate(['conferences'], {
      queryParams: {
        userId: this.userId,
        username: this.username
      }
    }).then(_ => {});
  }

  changeDeadlines(bidding: string, submit: string, review: string): void {
    let biddingPhase: Date = new Date(bidding);
    let submitPhase: Date = new Date(submit);
    let reviewPhase: Date = new Date(review);
    console.log(biddingPhase);

    this.conferenceService.changeDeadlines(this.conferenceId, biddingPhase, submitPhase, reviewPhase).subscribe(
      () => { this.goToConferencesPage(); })
  }
}
