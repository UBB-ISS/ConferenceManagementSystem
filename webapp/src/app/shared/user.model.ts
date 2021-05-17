/*
export interface User {
  id: number;
  name: string;
  email: string;
  affiliation: string;
  website: string;
  username: string;
  password: string;
}

export interface Users {
  usersDTO: Array<User>;
}
*/
export class User {
  id: number = 0;
  name: string;
  email: string;
  affiliation: string;
  website: string;
  username: string;
  password: string;

  constructor(name: string, email: string, affiliation: string, website: string, username: string, password: string) {
    this.name = name;
    this.email = email;
    this.affiliation = affiliation;
    this.website = website;
    this.username = username;
    this.password = password;
  }
}

export class Users {
  usersDTO: Array<User> = {} as Array<User>;
}
