import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Conference } from "../shared/conference.model";
import { ConferenceService } from "../shared/conference.service";

@Component({
  selector: 'app-conferences',
  templateUrl: './conferences.component.html',
  styleUrls: ['./conferences.component.scss']
})
export class ConferencesComponent implements OnInit {
  conferences: Array<Conference> = {} as Array<Conference>;
  id: number = 0;
  username: string = '';

  constructor(private service: ConferenceService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.queryParams.userId;
    this.username = this.route.snapshot.queryParams.username;

    this.getAllConferences();
  }

  getAllConferences(): void {
    this.service.getAllConferences().subscribe(
      (conferences) => {
        this.conferences = conferences.conferencesDTO;
      }
    );
  }

  goToRolesPage(id: number): void {
    this.router.navigate(['roles'], {
      queryParams: {
        userId: this.id,
        username: this.username,
        conferenceId: id
      }
    }).then(_ => {});
  }

  goToAddConferencePage() {
    this.router.navigate(['addConference'], {
      queryParams: {
        userId: this.id,
        username: this.username
      }
    }).then(_ => {});
  }

  dateOf(date: any) {
    return `${date.month}/${date.day}/${date.year}`;
  }

  goToChangeDeadlinesPage(conferenceId: number): void {
    this.router.navigate(['changeDeadlines'], {
      queryParams: {
        userId: this.id,
        username: this.username,
        conferenceId: conferenceId
      }
    }).then(_ => {});
  }
}
