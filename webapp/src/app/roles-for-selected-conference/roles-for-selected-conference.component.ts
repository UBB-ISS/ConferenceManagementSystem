import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { UserService } from "../shared/user.service";

@Component({
  selector: 'app-roles-for-selected-conference',
  templateUrl: './roles-for-selected-conference.component.html',
  styleUrls: ['./roles-for-selected-conference.component.scss']
})
export class RolesForSelectedConferenceComponent implements OnInit {
  userId: number = 0;
  username: string = '';
  conferenceId: number = 0;
  roles: Array<string> = {} as Array<string>;

  constructor(private router: Router, private route: ActivatedRoute, private userService: UserService) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.queryParams.userId;
    this.username = this.route.snapshot.queryParams.username;
    this.conferenceId = this.route.snapshot.queryParams.conferenceId;
    console.log("roles:");
    console.log(this.userId);
    console.log(this.username);
    console.log(this.conferenceId);

    this.getAllRolesForAGivenUserInAGivenConference(this.userId, this.conferenceId);
  }

  getAllRolesForAGivenUserInAGivenConference(userId: number, conferenceId: number): void {
    this.userService.getAllRolesForAGivenUserInAGivenConference(userId, conferenceId).subscribe(
      (roles) => {
        this.roles = roles;
        console.log(roles);
      }
    )
  }

  addUserToConference(role: string): void {
    this.userService.addUserToConference(this.userId, this.conferenceId, false, role)
      .subscribe(() => {
        this.router.navigate(['roles']).then(_ => {});
      });
  }

  goToRolePage(role: string) {
    this.router.navigate(['role'], {
      queryParams: {
        userId: this.userId,
        conferenceId: this.conferenceId,
        role: role,
        username: this.username
      }
    }).then(_ => {});
  }
}
