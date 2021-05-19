import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

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

  constructor(private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.paperId = this.route.snapshot.queryParams.paperId;
    this.userId = this.route.snapshot.queryParams.userId;
    this.conferenceId = this.route.snapshot.queryParams.conferenceId;
    this.role = this.route.snapshot.queryParams.role;
    this.username = this.route.snapshot.queryParams.username;
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
}
