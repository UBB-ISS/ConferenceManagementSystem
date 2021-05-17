import { of } from "rxjs";

export const MOCK_LOGIN = {
  sendLoginRequest: (username: string, password: string) => {
    if(username === 'lav' && password === 'lav')
      return of({"status": true, "user": {"username": username, "password": password, "affiliation": "admin"}})
    if(username === 'lavi' && password === 'lav')
      return of({"status": true, "user": {"username": username, "password": password, "affiliation": "reviewer"}})
    else
      return of({"status": false, "user": {"username": '', "password": '', "affiliation": ''}});
  }
}
