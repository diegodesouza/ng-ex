import { CollapseGroupCmp } from './../components/collapseGroupCmp';
import { AfterViewInit, Attribute, Directive, ElementRef, Input, OnInit, Renderer, HostListener } from '@angular/core';

//istanbul ignore next
@Directive({
  selector: '[data-uxd-block-toggle-dir]'
})
export class BlockToggleDirective implements OnInit {
  private _jElement: any = this._element.nativeElement;
  private _parentElement: any;
  private _limitOneHeader: any;
  private _openBlock: any;

  constructor(
    private _element: ElementRef,
    protected renderer: Renderer) {
  }

  ngOnInit() {
    //no-op
  }

  @HostListener('click') onClick() {
    this._parentElement = this._jElement.parentElement.parentElement;
    this._limitOneHeader = this._parentElement.classList.contains('limit-one-collapse');
    if (this._limitOneHeader) {
      if (this._parentElement.classList.contains('ant-block-expanded')) {
        this.removeExpandClass();
      } else {
        let activeBlocks = this._parentElement.getElementsByClassName('ant-block-expanded');
        if (activeBlocks.length > 0) {
          activeBlocks[0].classList.remove('ant-block-expanded');
        }
        this.addExpandClass();
      }
    } else {
      this._parentElement.classList.toggle('ant-block-expanded');
    }
  }
  addExpandClass() {
    this._parentElement.classList.add('ant-block-expanded');
  }
  removeExpandClass() {
    this._parentElement.classList.remove('ant-block-expanded');
  }
}
