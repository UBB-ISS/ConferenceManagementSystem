export class Conference {
  id: number = 0;
  name: string;
  entryFee: number;
  date: string;

  constructor(name: string, entryFee: number, date: string) {
    this.name = name;
    this.entryFee = entryFee;
    this.date = date;
  }
}

export class Conferences {
  conferencesDTO: Array<Conference> = {} as Array<Conference>;
}
