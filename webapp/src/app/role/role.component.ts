import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";

import { Paper } from "../shared/paper.model";
import { PaperService } from "../shared/paper.service";

@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.scss']
})
export class RoleComponent implements OnInit {
  userId: number = 0;
  conferenceId: number = 0;
  role: string = '';
  username: string = '';
  papers: Array<Paper> = {} as Array<Paper>;

  constructor(private paperService: PaperService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.queryParams.userId;
    this.conferenceId = this.route.snapshot.queryParams.conferenceId;
    this.role = this.route.snapshot.queryParams.role;
    this.username = this.route.snapshot.queryParams.username;
    console.log("role:");
    console.log(this.userId);
    console.log(this.conferenceId);
    console.log(this.role);

    this.getAllPapersOfAUserInAConference(this.userId, this.conferenceId);
  }

  getAllPapersOfAUserInAConference(userId: number, conferenceId: number): void {
    this.paperService.getPapersOfAUserInAConference(userId, conferenceId)
      .subscribe((papers) => {
        this.papers = papers.papersDTO;
        console.log(this.papers);
      })
  }

  goToCreateProposalPage(): void {
    this.router.navigate(['submitProposal'], {
      queryParams: {
        userId: this.userId,
        conferenceId: this.conferenceId,
        role: this.role,
        username: this.username
      }
    }).then(_ => {});
  }

  goToUpdateProposalPage(paperId: number): void {
    this.router.navigate(['updateProposal'], {
      queryParams: {
        paperId: paperId,
        userId: this.userId,
        conferenceId: this.conferenceId,
        role: this.role,
        username: this.username
      }
    }).then(_ => {});
  }
}
