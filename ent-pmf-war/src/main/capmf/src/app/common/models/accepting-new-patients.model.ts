/**
 * Created by AB74296 on 5/17/2017.
 */

export class AcceptingNewPatients {
    public acceptingNewPatientIndicator: string;
    public patientMinimumAge: string;
    public patientMaximumAge: string;
    public patientGender: string;
    public patientSpecialty: string;

    constructor() {
        this.acceptingNewPatientIndicator = 'yes';
        this.patientMinimumAge = null;
        this.patientMaximumAge = null;
        this.patientGender = null;
        this.patientSpecialty = null;
    }
}
