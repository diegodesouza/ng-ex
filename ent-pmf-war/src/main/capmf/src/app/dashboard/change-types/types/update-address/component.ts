import { Component, AfterViewInit, ViewChildren, QueryList } from '@angular/core';
import { PayLoadService } from '../../../../common/services/payload-all.service';
import { NgModel, NgForm } from '@angular/forms';
import { UpdateAddress } from '../../../../common/models/update-address.model';

@Component({
        selector: 'update-address',
        template: require('./body.html')
    }
)

export class UpdateAddressComponent implements AfterViewInit {
    @ViewChildren(NgModel) controls: QueryList<NgModel>;
    parentName: string = 'updateaddress';

    constructor(
        private payLoadService: PayLoadService,
        private parentForm: NgForm
    ) {
        if(!payLoadService.updateAddress) {
            payLoadService.updateAddress = new UpdateAddress();
        }
    }

    ngAfterViewInit() {
        this.controls.forEach((control: NgModel) => {
            this.parentForm.addControl(control);
        });
    }

}
