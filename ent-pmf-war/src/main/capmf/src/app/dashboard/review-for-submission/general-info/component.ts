import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../common/services/payload-all.service';
import {Router} from '@angular/router';

@Component({
  selector: 'review-for-submission-general-info-component',
  template: require('./body.html')
})

export class ReviewForSubmissionGeneralInfoComponent {
  @Input() payLoadService: PayLoadService;

  constructor(private router: Router) {
  }

  getCheckedTargetSystems(): Array<string> {
    const newTargetSystems: Array<string> = [];
    this.payLoadService.targetSystems.forEach((targetSystem) => {
      if(targetSystem.checked === true) {
        newTargetSystems.push(targetSystem.name);
      }
    });
    return newTargetSystems;
  }

  routeToNext() {
    this.router.navigate(['dashboard/generalinfo'], {skipLocationChange: true});
  }
}
