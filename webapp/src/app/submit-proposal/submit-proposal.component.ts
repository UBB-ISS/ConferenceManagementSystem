import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-submit-proposal',
  templateUrl: './submit-proposal.component.html',
  styleUrls: ['./submit-proposal.component.scss']
})
export class SubmitProposalComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goToConferencesPage(): void {
    this.router.navigate(['conferences']).then(_ => {});
  }
}
