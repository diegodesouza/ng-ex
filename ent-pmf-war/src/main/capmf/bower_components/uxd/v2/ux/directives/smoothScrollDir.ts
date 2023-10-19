import { AfterViewInit, Attribute, Directive, ElementRef, Input, OnInit, Renderer,HostListener } from '@angular/core';
declare var JQuery: any;

//istanbul ignore next
@Directive({
  selector: '[data-uxd-smooth-scroll-dir]'
})
export class SmoothScrollDirective {
  private _jElement: any = $(this._element.nativeElement);
  private options: any;
  private htmlBody: any;
  constructor(private _element: ElementRef,
    protected renderer: Renderer) {
  }

  @HostListener('click', ['$event', '$event.target']) onClick(event: any, target: any) {
    this.options = this._jElement.find('li a');
    event.preventDefault();
    this.htmlBody = this._jElement.offsetParent().offsetParent().find('body');
    let id = target.attributes[1].nodeValue;
    let element = this.htmlBody.find(id);
    if (element.length) {
      let offset = element.offset().top;
      this.htmlBody.animate({ scrollTop: offset - (0) }, 316);
    }
  }
}
