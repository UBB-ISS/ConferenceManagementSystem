export class PaperReview {
  id: number = 0;
  reviewerId: number;
  paperId: number;
  recommendation: string;
  qualifier: string;

  authorID?: number;
  authorName?: string;
  paperTitle?: string;

  constructor(id: number = 0, reviewerId: number, paperId: number, recommendation: string, qualifier: string) {
    this.id = id;
    this.reviewerId = reviewerId;
    this.paperId = paperId;
    this.recommendation = recommendation;
    this.qualifier = qualifier;
  }
}

export class PaperReviews {
  paperReviewsDTO: Array<PaperReview> = {} as Array<PaperReview>;
}
