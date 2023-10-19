/**
 * Created by AC56833 on 5/22/2017.
 */

export class HospitalPrivilege {
    public requestActionCd: string;
    public privilegeType: string;
    public affiliationName: string;

    constructor() {
        this.requestActionCd = 'A';
        this.privilegeType = null;
        this.affiliationName = null;
    }
}
