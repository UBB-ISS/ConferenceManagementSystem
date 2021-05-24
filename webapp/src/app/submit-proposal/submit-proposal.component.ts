import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {UserConferenceService} from "../shared/user-conference.service";
import {PaperService} from "../shared/paper.service";

@Component({
  selector: 'app-submit-proposal',
  templateUrl: './submit-proposal.component.html',
  styleUrls: ['./submit-proposal.component.scss']
})
export class SubmitProposalComponent implements OnInit {
  userId: number = 0;
  conferenceId: number = 0;
  role: string = '';
  username: string = '';
  userConferenceId: number = 0;
  constructor(private router: Router,
              private route: ActivatedRoute,
              private userConferenceService: UserConferenceService,
              private paperService: PaperService) {
  }

  ngOnInit(): void {
    this.userId = this.route.snapshot.queryParams.userId;
    this.conferenceId = this.route.snapshot.queryParams.conferenceId;
    this.role = this.route.snapshot.queryParams.role;
    this.username = this.route.snapshot.queryParams.username;

    this.userConferenceService.getUserConferenceWithRole(this.userId, this.conferenceId, this.role)
      .subscribe(
        userConference => {
          this.userConferenceId = userConference.id;
        }
      )
  }

  goToRolePage(): void {
    this.router.navigate(['role'], {
      queryParams: {
        userId: this.userId,
        conferenceId: this.conferenceId,
        role: this.role,
        username: this.username
      }
    }).then(_ => {});
  }

  createProposal(title: string, keywords: string, paperText: string,
                 abstractText: string) {
    this.paperService.addPaper(this.userConferenceId, title, keywords, paperText, abstractText, false, false)
      .subscribe(
          (result: any) => {
          this.goToRolePage();
        }
      );
  }
}
