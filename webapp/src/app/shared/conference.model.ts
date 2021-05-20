export class Conference {
  id: number = 0;
  name: string;
  entryFee: number;
  date: string;
  biddingPhaseDeadline: string;
  submitPaperDeadline: string;
  reviewPaperDeadline: string;

  constructor(name: string, entryFee: number, date: string, bidding: string, submit: string, review: string) {
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
