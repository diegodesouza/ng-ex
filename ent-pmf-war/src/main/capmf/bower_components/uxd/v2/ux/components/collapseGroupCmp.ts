import { Component, OnInit, Attribute, Input, ViewChild,ElementRef } from '@angular/core';

//istanbul ignore next
@Component({
  moduleId: module.id,
  selector: '[data-uxd-collapse-group-cmp]',
  template: `<div data-uxd-collapse-item-cmp [ngClass]="{'addStripe': (collapseGroup.data.length > 3)}" [collapseItem]="collapseGroup" [headingContent]="collapseItem.headingContent"
    [bodyContent]="collapseItem.content" [id]="collapseItem.id + $index" *ngFor="let collapseItem of collapseGroup.data; let $index = index">
</div>`
})
export class CollapseGroupCmp implements OnInit{
  @Input() public collapseGroup: any;
  private _jElement: any = this._element.nativeElement;
  constructor(
    private _element: ElementRef){
  }
  ngOnInit() {
    if (this.collapseGroup.limitOneBlock){
      this._jElement.classList.add('limit-one-collapse');
    }
  }
}
