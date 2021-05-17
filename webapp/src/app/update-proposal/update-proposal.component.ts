import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-proposal',
  templateUrl: './update-proposal.component.html',
  styleUrls: ['./update-proposal.component.scss']
})
export class UpdateProposalComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goToViewProposalsPage(): void {
    this.router.navigate(['viewProposals']).then(_ => {});
  }
}
