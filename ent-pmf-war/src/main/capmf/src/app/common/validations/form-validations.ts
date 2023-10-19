/**
 * Created by AD94882 on 4/18/17.
 */

import { Injectable } from '@angular/core';
import { PayLoadService } from '../services/payload-all.service';
import {AbstractControl, NgForm} from '@angular/forms';
import {Subscription} from 'rxjs/Subscription';

@Injectable()
export class FormValidations {
    formErrors = {};
    validationMessages = {};
    customValidators = {};
    subscriptions = {};
    customErrorMessages = {};

    phoneMask = ['(', /[1-9]/, /\d/, /\d/, ')', ' ', /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];

    constructor(private payLoadService: PayLoadService) {
    }

    subscribeForm(changedForm: NgForm) {
        if (changedForm) {
            let subscription: Subscription = changedForm.valueChanges
                .subscribe((data) => this.onValueChanged(changedForm, data));
            this.subscriptions[changedForm.name] = subscription;
        }
    }

    unSubscribeForm(changedForm: NgForm) {
        if(changedForm && this.subscriptions[changedForm.name]) {
            this.subscriptions[changedForm.name].unsubscribe();
            delete this.subscriptions[changedForm.name];
        }
    }

    reSubscribeForm(changedForm: NgForm) {
        this.unSubscribeForm(changedForm);
        this.subscribeForm(changedForm);
    }

    onValueChanged(changedForm: NgForm, data?: any) {
        if (!changedForm) { return; }
        const form = changedForm.form;
        for (const controlName in form.controls) {
            let control = form.get(controlName);
            this.executeCustomValidator(control, controlName);
            if (control && (control.valid && !control.errors)) {
                if (this.formErrors[controlName]) { delete this.formErrors[controlName]; }
            }
            if (control && control.dirty && (!control.valid || control.errors)) {
                this.displayErrorMessages(control, controlName);
            }
        }
    }

    executeCustomValidator(edControl: AbstractControl, field: string) {
        if(this.customValidators[field]) {
            this.customValidators[field](this, edControl, field);
        }
    }

    registerCustomValidators(validators: {}) {
        for(let key in validators) {
            this.customValidators[key] = validators[key];
        }
    }

    registerValidationMessages(vMessages: {}) {
        for(const key in vMessages) {
            this.validationMessages[key] = vMessages[key];
        }
    }

    displayErrorMessages(control: any, controlName: string) {
        const messages = this.validationMessages[controlName];
        if(messages) {
            for (const key in control.errors) {
                this.formErrors[controlName] = messages[key];
            }
        }
    }

    addCustomMessage(key: string, value: string)  {
      this.customErrorMessages[key] = value;
    }

    clearCustomMessage(key: string)  {
      delete this.customErrorMessages[key];
    }

    clearAllCustomMessages(key: string)  {
      this.customErrorMessages = {};
    }
}
