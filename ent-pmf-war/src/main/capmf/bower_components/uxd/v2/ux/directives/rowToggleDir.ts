import { AfterViewInit, Attribute, Directive, ElementRef, Input, OnInit, Renderer } from '@angular/core';
declare var JQuery: any;

//istanbul ignore next
@Directive({
  selector: '[data-uxd-row-toggle-dir]'
})
export class RowToggleDirective implements OnInit, AfterViewInit {
  @Input('ngModel') private _modelValue: string;
  private _jElement: any = $(this._element.nativeElement);
  private _toggleCtrl: any;
  private _toggleBody: any;
  private _toggleCtrlGlyph: any;

  constructor( @Attribute('data-out-class') private _outClass: string,
    @Attribute('data-in-class') private _inClass: string,
    @Attribute('id') private _id: string,
    @Attribute('name') private _name: string,
    private _element: ElementRef,
    protected renderer: Renderer) {
    _outClass = _outClass || 'fa-minus-circle';
    _inClass = _inClass || 'fa-plus-circle';
  }

  ngOnInit() {
    //no-op
  }

  ngAfterViewInit(): any {
    this._toggleCtrl = this._jElement.find('.ant-toggle-ctrl');
    this._toggleBody = this._jElement.find('.ant-toggle-body');
    this._toggleCtrlGlyph = this._jElement.find('.ant-toggle-ctrl .fa');
    this._toggleCtrl.on('click', () => {
      this.toggleBlock();
    });
  }

  toggleBlock() {
    if (!this._toggleBody.hasClass('in')) {
      this._toggleBody.addClass('in');
      this._toggleCtrl.removeClass('collapsed');
      this._toggleCtrlGlyph.removeClass(this._outClass);
      this._toggleCtrlGlyph.addClass(this._inClass);
      this._toggleCtrl.attr('aria-expanded', 'true');
    }
    else {
      this._toggleBody.removeClass('in');
      this._toggleCtrl.addClass('collapsed');
      this._toggleCtrlGlyph.removeClass(this._inClass);
      this._toggleCtrlGlyph.addClass(this._outClass);
      this._toggleCtrl.attr('aria-expanded', 'false');
    }
  }
}
