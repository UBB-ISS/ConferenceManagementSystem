import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from "../shared/user.service";
import { User } from "../shared/user.model";

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.scss']
})
export class CreateAccountComponent implements OnInit {
  user: User = {} as User;

  constructor(private service: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  goToConferences(): void {
    this.router.navigate(['conferences']).then(_ => {});
  }

  createAccount(name: string, email: string, affiliation: string, website: string, username: string, password: string): void {
    this.service.createAccount(name, email, affiliation, website, username, password)
                .subscribe((message) => {
                  this.goToConferences()
                });
  }
}
