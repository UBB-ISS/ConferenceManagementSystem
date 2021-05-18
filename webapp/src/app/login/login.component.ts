import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { MOCK_LOGIN } from "../shared/mock-login.service";
import { User } from "../shared/user.model";
import { UserService } from "../shared/user.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  user: User = {} as User;

  constructor(private service: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  gotoForgetPassword(): void {
    this.router.navigate(['']).then(_ => {});
  }

  goToConferences(): void {
    console.log(this.user);
    this.router.navigate(['conferences']/*,
      {
        queryParams:
          {
            username: this.user.username,
            password: this.user.password,
            affiliation: this.user.affiliation
          }
      }*/).then(_ => {});
  }

  loginUser(username: string, password: string): void {
    this.service.loginUser(username, password).subscribe(
      user => {
        if(user != null) {
          this.user = user;
          this.goToConferences();
        } else window.alert("This user does not exist!");
      }
    );
  }
}
