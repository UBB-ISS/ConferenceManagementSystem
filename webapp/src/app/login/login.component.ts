import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

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
    console.log("login:");
    console.log(this.user);
    this.router.navigate(['conferences'], {
      queryParams: {
        userId: this.user.id,
        username: this.user.username
      }
    }).then(_ => {});
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
