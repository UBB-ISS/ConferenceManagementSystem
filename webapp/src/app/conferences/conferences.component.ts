import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { User } from "../shared/user.model";

@Component({
  selector: 'app-conferences',
  templateUrl: './conferences.component.html',
  styleUrls: ['./conferences.component.scss']
})
export class ConferencesComponent implements OnInit {
  conferences: null;
  user: User = {} as User;

  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.user.username = this.route.snapshot.queryParams.username;
    this.user.password = this.route.snapshot.queryParams.password;
    this.user.affiliation = this.route.snapshot.queryParams.affiliation;
    console.log(this.user);
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
}
