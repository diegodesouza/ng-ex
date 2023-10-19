/**
 * Created by AC56833 on 5/17/2017.
 */

export class TerminatePPA {
    public terminationDate: Date;
    public terminationReason: string;
    public managedCarePCPIndicator: string;

    constructor() {
        this.terminationDate = null;
        this.terminationReason = null;
        this.managedCarePCPIndicator = null;
    }
}
