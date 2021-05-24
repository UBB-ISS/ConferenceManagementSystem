import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

import { ConferenceService } from "../shared/conference.service";
import { Date } from "../shared/date.model";

@Component({
  selector: 'app-add-conference',
  templateUrl: './add-conference.component.html',
  styleUrls: ['./add-conference.component.scss']
})
export class AddConferenceComponent implements OnInit {
  userId: number = 0;
  username: string = '';

  constructor(private conferenceService: ConferenceService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.queryParams.userId;
    this.username = this.route.snapshot.queryParams.username;
  }

  addConference(name: string, price: number, date: string, bidding: string, submit: string, review: string): void {
    let dateConference: Date = new Date(date);
    let biddingPhase: Date = new Date(bidding);
    let submitPhase: Date = new Date(submit);
    let reviewPhase: Date = new Date(review);

    this.conferenceService.addConference(name, price, dateConference, biddingPhase, submitPhase, reviewPhase).subscribe(
      () => { this.goToConferencesPage(); })
  }

  goToConferencesPage(): void {
    this.router.navigate(['conferences'], {
      queryParams: {
        userId: this.userId,
        username: this.username
      }
    }).then(_ => {});
  }
}
