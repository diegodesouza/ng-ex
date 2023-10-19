import { Component, Input, OnDestroy } from '@angular/core';

@Component({
    selector: 'accordion',
    template:`
  <ng-content></ng-content>
          `,
    host: {
        'class': 'panel-group'
    }
})
export class Accordion {
    groups: Array<AccordionGroup> = [];

    addGroup(group: AccordionGroup): void {
        this.groups.push(group);
    }

    closeOthers(openGroup: AccordionGroup): void {
        this.groups.forEach((group: AccordionGroup) => {
            if (group !== openGroup) {
                group.isOpen = false;
            }
        });
    }

    removeGroup(group: AccordionGroup): void {
        const index = this.groups.indexOf(group);
        if (index !== -1) {
            this.groups.splice(index, 1);
        }
    }
}

@Component({
    selector: 'accordion-group',
    template:`<div class="accordion-container">
                <div class="panel panel-default" [ngClass]="{'panel-open': isOpen}">
                  <div class="panel-heading" (click)="toggleOpen($event)">
                    <h2 class="panel-title">

                        <span *ngIf="!isOpen" class="fa md">+</span>
                        <span *ngIf="isOpen" class="fa md">-</span>

                      <span class="collapsible-heading-text">{{heading}}</span>
                    </h2>

                  </div>
                  <div class="panel-collapse" [hidden]="!isOpen">
                    <div class="panel-body">
                        <ng-content></ng-content>
                    </div>
                  </div>
                </div>
              </div>
          `,
    styles:[require('./accordion.css')]

})

export class AccordionGroup implements OnDestroy {
    private _isOpen: boolean = false;

    @Input() heading: string;

    @Input()
    set isOpen(value: boolean) {
        this._isOpen = value;
        // if (value) {
        //     this.accordion.closeOthers(this);
        // }
    }

    get isOpen() {
        return this._isOpen;
    }

    constructor(private accordion: Accordion) {
        this.accordion.addGroup(this);
    }

    ngOnDestroy() {
        this.accordion.removeGroup(this);
    }

    toggleOpen(event: MouseEvent): void {
        event.preventDefault();
        this.isOpen = !this.isOpen;
    }
}
