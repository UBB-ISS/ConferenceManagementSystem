export class Paper {
  id: number = 0;
  userConferenceId: number;
  title: string;
  keywords: string;
  paperText: string;
  abstractText: string;
  finalized: boolean;
  accepted: boolean;

  constructor(userConferenceId: number, title: string, keywords: string, paperText: string, abstractText: string,
              finalized: boolean, accepted: boolean) {
    this.userConferenceId = userConferenceId;
    this.title = title;
    this.keywords = keywords;
    this.paperText = paperText;
    this.abstractText = abstractText;
    this.finalized = finalized;
    this.accepted = accepted;
  }
}

export class Papers {
  papersDTO: Array<Paper> = {} as Array<Paper>;
}
