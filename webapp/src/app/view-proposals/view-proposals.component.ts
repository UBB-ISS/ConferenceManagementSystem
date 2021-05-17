import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-proposals',
  templateUrl: './view-proposals.component.html',
  styleUrls: ['./view-proposals.component.scss']
})
export class ViewProposalsComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goToUpdateProposalPage(): void {
    this.router.navigate(['updateProposal']).then(_ => {});
  }

  goToConferencesPage(): void {
    this.router.navigate(['conferences']).then(_ => {});
  }
}
