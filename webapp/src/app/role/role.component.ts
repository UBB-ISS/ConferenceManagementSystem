import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";

import { Paper } from "../shared/paper.model";
import { PaperService } from "../shared/paper.service";
import {ConferenceService} from "../shared/conference.service";

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
  finalPapersFromAConference: Array<Paper> = [];

  constructor(private conferenceService: ConferenceService, private paperService: PaperService,
              private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.queryParams.userId;
    this.conferenceId = this.route.snapshot.queryParams.conferenceId;
    this.role = this.route.snapshot.queryParams.role;
    this.username = this.route.snapshot.queryParams.username;
    this.getAllPapersOfAUserInAConference(this.userId, this.conferenceId);

    this.getAllPapersOfAUserInAConference(this.userId, this.conferenceId);
    this.getFinalPapersFromAConference(this.conferenceId);
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
    // @ts-ignore
    const {abstractText, accepted, finalized, keywords, paperText, title} = this.papers.find(({id}) => id === paperId);
    this.router.navigate(['updateProposal'], {
      queryParams: {
        paperId: paperId,
        userId: this.userId,
        conferenceId: this.conferenceId,
        role: this.role,
        username: this.username,
        title: title,
        keywords: keywords,
        abstractText: abstractText,
        paperText: paperText,
        accepted: accepted,
        finalized: finalized
      }
    }).then(_ => {});
  }

  dateOf(date: any) {
    return `${date.month}/${date.day}/${date.year}`;
  }

  goToBidPaperPage(paperId: number): void {
    this.conferenceService.getConferenceById(this.conferenceId).subscribe(
      (conference) => {
        let ok = true;
        console.log('a');
        console.log(this.dateOf(conference.biddingPhaseDeadline));
        console.log(new Date().toLocaleDateString());
        if(this.dateOf(conference.biddingPhaseDeadline) < new Date().toLocaleDateString()) {
          window.alert("Bidding phase finished");
          ok = false;
        }

        if(ok) {
          this.router.navigate(['bidProposal'], {
            queryParams: {
              userId: this.userId,
              conferenceId: this.conferenceId,
              username: this.username,
              paperId: paperId
            }
          }).then(_ => {});
        }
      });
  }

  getFinalPapersFromAConference(conferenceId: number): void {
    this.paperService.getFinalPapersFromAConference(conferenceId)
        .subscribe((acceptedPapers) => {
            this.finalPapersFromAConference = acceptedPapers.papersDTO;
          });
  }

  goToReviewPapersPage(): void {

  }

  goToRolesPage(): void {
    this.router.navigate(['roles'], {
      queryParams: {
        userId: this.userId,
        username: this.username,
        conferenceId: this.conferenceId
      }
    }).then(_ => {});
  }
}
