import {User} from "./user.model";

export class UserConference {
  id: number = 0;
  conferenceID: number;
  userID: number;
  role: string;
  paid: boolean;

  constructor(conferenceID: number, userID: number, role: string, paid: boolean) {
    this.conferenceID = conferenceID;
    this.userID = userID;
    this.role = role;
    this.paid = paid;
  }
}

export class UserConferences {
  userConferencesDTO: Array<UserConference> = {} as Array<UserConference>;
}
