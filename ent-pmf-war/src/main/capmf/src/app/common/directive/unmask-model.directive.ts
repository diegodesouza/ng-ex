import { Directive, ElementRef, OnInit, OnDestroy } from '@angular/core';
import { NgControl } from '@angular/forms';

@Directive({
  selector: '[unmaskModel]'
})

export class UnmaskModelDirective implements OnInit, OnDestroy {

  // @Input() unmask: string;
  private subscriber;

  constructor(private elementRef: ElementRef, private model: NgControl) {}

  ngOnInit() {
    this.subscriber = this.model.control.valueChanges.subscribe(() => {
      const newValue = this.elementRef.nativeElement.value.replace(/\D\s*/g, '');

      // if we feel the need to re-use this directive on other masking,
      // we can use the @input and get the regex from it.

      // const newValue = this.elementRef.nativeElement.value.replace(new RegExp(this.unmask), '');

      this.model.control.setValue(newValue, {
        emitEvent: false,
      });
    });
  }

  ngOnDestroy() {
    this.subscriber.unsubscribe();
  }
}
