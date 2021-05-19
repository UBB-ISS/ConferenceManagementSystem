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
    console.log("conferences:");
    console.log(this.id);
    console.log(this.username);
    this.getAllConferences();
  }

  getAllConferences(): void {
    this.service.getAllConferences().subscribe(
      (conferences) => {
        this.conferences = conferences.conferencesDTO;
        console.log(this.conferences);
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

  dateOf(date: any) {
    return `${date.year}-${date.monthValue}-${date.dayOfMonth}`;
  }
}
