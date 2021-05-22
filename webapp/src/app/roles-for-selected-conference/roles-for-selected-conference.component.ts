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

  allPossibleRoles: Array<string> = ['PC_MEMBER', 'CHAIR', 'CO_CHAIR', 'AUTHOR', 'REVIEWER', 'SESSION_CHAIR', 'SPEAKER', 'LISTENER'];

  constructor(private router: Router, private route: ActivatedRoute, private userService: UserService) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.queryParams.userId;
    this.username = this.route.snapshot.queryParams.username;
    this.conferenceId = this.route.snapshot.queryParams.conferenceId;

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
        window.location.reload();
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

  verify(role: string): void {
    let ok = true;
    let verify_author = false;

    for(let r of this.roles) {
      if(r === role) {
        window.alert("You are already a/an " + r);
        ok = false;
      }

      if(r === 'AUTHOR') verify_author = true;
    }

    if(!verify_author && role === 'SPEAKER') {
      window.alert("You cannot be a speaker if you are not an author");
      ok = false;
    }

    if(ok) this.addUserToConference(role);
  }

  goToConferencesPage(): void {
    this.router.navigate(['conferences'], {
      queryParams: {
        userId: this.userId,
        username: this.username
      }
    }).then(_ => {});
  }
}
