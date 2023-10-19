/**
 * Created by AC56833 on 6/1/2017.
 */

export class DayOperation {
    public dayName: string;
    public openingTime: string;
    public closingTime: string;

    constructor(private weekDay: string) {
        this.dayName = weekDay;
        this.openingTime = 'Closed';
        this.closingTime = 'Closed';
    }
}
