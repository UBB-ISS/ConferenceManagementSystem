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
    this.getFinalPapersFromAConference(this.userId, this.conferenceId);
  }

  getAllPapersOfAUserInAConference(userId: number, conferenceId: number): void {
    this.paperService.getPapersOfAUserInAConference(userId, conferenceId)
      .subscribe((papers) => {
        this.papers = papers.papersDTO;
      })
  }

  dateOf(date: any) {
    return `${date.month}/${date.day}/${date.year}`;
  }

  goToCreateProposalPage(): void {
    this.conferenceService.getConferenceById(this.conferenceId).subscribe(
      (conference) => {
        /*let ok = true;
        if (this.dateOf(conference.submitPaperDeadline) < new Date().toLocaleDateString()) {
          window.alert("Submit phase finished");
          ok = false;
        }*/

        let reviewDate = this.dateOf(conference.submitPaperDeadline).split('/');
        let currentDate = new Date().toLocaleDateString().split('/');

        let okk = true, ok = true;
        if(reviewDate[2] > currentDate[2]) okk = false;
        else {
          if(reviewDate[2] == currentDate[2] && reviewDate[0] > currentDate[0]) okk = false;
          else {
            if(reviewDate[0] == currentDate[0] && reviewDate[1] >= currentDate[1]) okk = false;
          }
        }

        if (okk) {
          window.alert("Submit phase finished");
          ok = false;
        }

        if (ok) {
          this.router.navigate(['submitProposal'], {
            queryParams: {
              userId: this.userId,
              conferenceId: this.conferenceId,
              role: this.role,
              username: this.username
            }
          }).then(_ => {
          });
        }
      });
  }

  goToViewResultsPage(): void {
    this.conferenceService.getConferenceById(this.conferenceId).subscribe(
      (conference) => {
        let reviewDate = this.dateOf(conference.reviewPaperDeadline).split('/');
        let currentDate = new Date().toLocaleDateString().split('/');

        let okk = true, ok = true;
        if(reviewDate[2] > currentDate[2]) okk = false;
        else {
          if(reviewDate[2] == currentDate[2] && reviewDate[0] > currentDate[0]) okk = false;
          else {
            if(reviewDate[0] == currentDate[0] && reviewDate[1] >= currentDate[1]) okk = false;
          }
        }

        if (!okk) {
          window.alert("Review phase not yet finished");
          ok = false;
        }

        if (ok) {
          this.router.navigate(['viewResults'], {
            queryParams: {
              userId: this.userId,
              conferenceId: this.conferenceId,
              role: this.role,
              username: this.username
            }
          }).then(_ => {
          });
        }
      });
  }

  goToUpdateProposalPage(paperId: number): void {
    this.conferenceService.getConferenceById(this.conferenceId).subscribe(
      (conference) => {
        /*let ok = true;
        if (this.dateOf(conference.submitPaperDeadline) < new Date().toLocaleDateString()) {
          window.alert("Submit phase finished");
          ok = false;
        }*/

        let reviewDate = this.dateOf(conference.submitPaperDeadline).split('/');
        let currentDate = new Date().toLocaleDateString().split('/');

        let okk = true, ok = true;
        if(reviewDate[2] > currentDate[2]) okk = false;
        else {
          if(reviewDate[2] == currentDate[2] && reviewDate[0] > currentDate[0]) okk = false;
          else {
            if(reviewDate[0] == currentDate[0] && reviewDate[1] >= currentDate[1]) okk = false;
          }
        }

        if (okk) {
          window.alert("Submit phase finished");
          ok = false;
        }

        if(ok) {
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
      });
  }

  goToBidPaperPage(paperId: number): void {
    this.conferenceService.getConferenceById(this.conferenceId).subscribe(
      (conference) => {
/*
        let ok = true;
        if(this.dateOf(conference.biddingPhaseDeadline) < new Date().toLocaleDateString()) {
          window.alert("Bidding phase finished");
          ok = false;
        }
*/

        let reviewDate = this.dateOf(conference.biddingPhaseDeadline).split('/');
        let currentDate = new Date().toLocaleDateString().split('/');

        let okk = true, ok = true;
        if(reviewDate[2] > currentDate[2]) okk = false;
        else {
          if(reviewDate[2] == currentDate[2] && reviewDate[0] > currentDate[0]) okk = false;
          else {
            if(reviewDate[0] == currentDate[0] && reviewDate[1] >= currentDate[1]) okk = false;
          }
        }

        if (okk) {
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

  getFinalPapersFromAConference(userId: number, conferenceId: number): void {
    this.paperService.getFinalPapersFromAConference(userId, conferenceId)
      .subscribe((finalPapers) => {
        this.finalPapersFromAConference = finalPapers.papersDTO;
      });
  }

  goToReviewAssignedPapersPage(): void {
    this.conferenceService.getConferenceById(this.conferenceId).subscribe(
      (conference) => {
        let ok = true;
        if (this.dateOf(conference.reviewPaperDeadline) < new Date().toLocaleDateString()) {
          window.alert("Review phase finished");
          ok = false;
        }

        if (ok) {
          this.router.navigate(['reviewAssignedPapers'], {
            queryParams: {
              userId: this.userId,
              conferenceId: this.conferenceId,
              username: this.username
            }
          }).then(_ => {
          });
        }
      });
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

  goToSendPaperToReviewerPage(): void {
    this.router.navigate(['sendPaperToReviewer'], {
      queryParams: {
        userId: this.userId,
        conferenceId: this.conferenceId,
        role: this.role,
        username: this.username
      }
    }).then(_ => {});
  }

  goToRequestCloserEvaluationPage(): void {
    this.router.navigate(['']).then(_ => {});
  }

  goToSendResultsPage(): void {
    this.conferenceService.getConferenceById(this.conferenceId).subscribe(
      (conference) => {
        let ok = true;

        let reviewDate = this.dateOf(conference.reviewPaperDeadline).split('/');
        let currentDate = new Date().toLocaleDateString().split('/');

        let okk = true;
        if(reviewDate[2] > currentDate[2]) okk = false;
        else {
          if(reviewDate[2] == currentDate[2] && reviewDate[0] > currentDate[0]) okk = false;
          else {
            if(reviewDate[0] == currentDate[0] && reviewDate[1] >= currentDate[1]) okk = false;
          }
        }

        if (!okk) {
          window.alert("Review phase not yet finished");
          ok = false;
        }

        if (ok) {
          this.router.navigate(['sendResults'], {
            queryParams: {
              userId: this.userId,
              conferenceId: this.conferenceId,
              role: this.role,
              username: this.username
            }
          }).then(_ => {
          });
        }
      });
  }

  goToSelectPapersForSectionPage(): void {
    this.router.navigate(['']).then(_ => {});
  }
}
