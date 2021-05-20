export class Conference {
  id: number = 0;
  name: string;
  entryFee: number;
  date: any;
  biddingPhaseDeadline: any;
  submitPaperDeadline: any;
  reviewPaperDeadline: any;

  constructor(id: number = 0, name: string, entryFee: number, date: any, bidding: any, submit: any, review: any) {
    this.id = id;
    this.name = name;
    this.entryFee = entryFee;
    this.date = date;
    this.biddingPhaseDeadline = bidding;
    this.submitPaperDeadline = submit;
    this.reviewPaperDeadline = review;
  }
}

export class Conferences {
  conferencesDTO: Array<Conference> = {} as Array<Conference>;
}
