export class Result {
  id: number = 0;
  authorId: number;
  paperId: number;
  qualifier: string;

  title?: string;

  constructor(id: number = 0, authorId: number, paperId: number, qualifier: string) {
    this.authorId = authorId;
    this.paperId = paperId;
    this.qualifier = qualifier;
  }
}

export class Results {
  resultsDTO: Array<Result> = {} as Array<Result>;
}
