/**
 * Created by AD94882 on 10/18/17.
 */

import {Directive, HostListener} from '@angular/core';

@Directive({
  selector: '[data-review-print]'
})

export class ReviewPrint {
  @HostListener('click')
  onClick() {
    (window as any).print();
  }
}
