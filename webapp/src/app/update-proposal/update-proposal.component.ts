import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {UserConferenceService} from "../shared/user-conference.service";
import {PaperService} from "../shared/paper.service";

@Component({
  selector: 'app-update-proposal',
  templateUrl: './update-proposal.component.html',
  styleUrls: ['./update-proposal.component.scss']
})
export class UpdateProposalComponent implements OnInit {
  paperId: number = 0;
  userId: number = 0;
  conferenceId: number = 0;
  role: string = '';
  username: string = '';
  finalized = false;
  accepted = false;
  title: string = '';
  paperText: string = '';
  abstractText: string = '';
  keywords: string = '';

  constructor(private router: Router,
              private route: ActivatedRoute,
              private paperService: PaperService) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.queryParams.userId;
    this.conferenceId = this.route.snapshot.queryParams.conferenceId;
    this.role = this.route.snapshot.queryParams.role;
    this.username = this.route.snapshot.queryParams.username;
    this.paperId = this.route.snapshot.queryParams.paperId;
    this.finalized = this.route.snapshot.queryParams.finalized === 'true';
    this.accepted = this.route.snapshot.queryParams.accepted === 'true';
    this.title = this.route.snapshot.queryParams.title;
    this.paperText = this.route.snapshot.queryParams.paperText;
    this.abstractText = this.route.snapshot.queryParams.abstractText;
    this.keywords = this.route.snapshot.queryParams.keywords;
  }

  goToViewProposalsPage(): void {
    this.router.navigate(['role'], {
      queryParams: {
        userId: this.userId,
        conferenceId: this.conferenceId,
        role: this.role,
        username: this.username
      }
    }).then(_ => {});
  }

  updatePaper() {
    this.paperService.updatePaper(this.paperId, this.title, this.paperText, this.abstractText, this.keywords, this.finalized, this.accepted)
      .subscribe(
        result => {
          console.log(result);
        }
      )
  }
}
