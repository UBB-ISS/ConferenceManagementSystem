import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { User } from "../shared/user.model";
import {Conference, Conferences} from "../shared/conference.model";
import {ConferenceService} from "../shared/conference.service";
import * as moment from "moment";

@Component({
  selector: 'app-conferences',
  templateUrl: './conferences.component.html',
  styleUrls: ['./conferences.component.scss']
})
export class ConferencesComponent implements OnInit {
  conferences: Array<Conference> = {} as Array<Conference>;
  user: User = {} as User;

  constructor(private service: ConferenceService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    /*this.user.username = this.route.snapshot.queryParams.username;
    this.user.password = this.route.snapshot.queryParams.password;
    this.user.affiliation = this.route.snapshot.queryParams.affiliation;
    console.log(this.user);*/
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

  goToSubmitProposalPage(): void {
    this.router.navigate(['submitProposal']).then(_ => {});
  }

  goToRolesPage(): void {
    this.router.navigate(['roles']).then(_ => {});
  }

  goToViewProposalsPage(): void {
    this.router.navigate(['viewProposals']).then(_ => {});
  }

  dateOf(date: any) {
    return `${date.year}-${date.monthValue}-${date.dayOfMonth}`;
  }
}
