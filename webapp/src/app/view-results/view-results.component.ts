import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

import { Result } from "../shared/result.model";
import { ResultService } from "../shared/result.service";
import { PaperService } from "../shared/paper.service";

@Component({
  selector: 'app-view-results',
  templateUrl: './view-results.component.html',
  styleUrls: ['./view-results.component.scss']
})
export class ViewResultsComponent implements OnInit {
  userId: number = 0;
  conferenceId: number = 0;
  role: string = '';
  username: string = '';

  results: Array<Result> = {} as Array<Result>;

  constructor(private router: Router, private route: ActivatedRoute,
              private resultService: ResultService, private paperService: PaperService) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.queryParams.userId;
    this.conferenceId = this.route.snapshot.queryParams.conferenceId;
    this.role = this.route.snapshot.queryParams.role;
    this.username = this.route.snapshot.queryParams.username;

    this.getAllForAuthorAndConference(this.userId, this.conferenceId);
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

  getAllForAuthorAndConference(authorId: number, conferenceId: number): void {
    this.resultService.getAllForAuthorAndConference(authorId, conferenceId).subscribe(
      results => {
        this.results = results.resultsDTO;

        for(const result of this.results) {
          this.paperService.getPaperById(result.paperId).subscribe(paper => {
            result.title = paper.title;
          })
        }
      });
  }
}
