export class ReviewerPaper {
  id: number = 0;
  userId: number;
  paperId: number;
  assigned: boolean;
  availability: string;

  paperTitle?: string;

  constructor(id= 0, userId: number, paperId: number, assigned: boolean, availability: string) {
    this.id = id;
    this.userId = userId;
    this.paperId = paperId;
    this.assigned = assigned;
    this.availability = availability;
  }
}

export class ReviewerPapers {
  reviewerPapersDTO: Array<ReviewerPaper> = {} as Array<ReviewerPaper>;
}
