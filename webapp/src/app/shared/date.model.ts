export class Date {
  day: number;
  month: number;
  year: number;

  constructor(date: string) {
    let dates = this.convertStringToNumber(date);

    this.day = Number(dates[2]);
    this.month = Number(dates[1]);
    this.year = Number(dates[0]);
  }

  convertStringToNumber(date: string): Array<String> {
    return date.split('-');
  }
}
