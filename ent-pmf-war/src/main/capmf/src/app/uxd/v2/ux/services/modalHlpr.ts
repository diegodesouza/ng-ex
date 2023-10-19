import { Injectable, ElementRef } from '@angular/core';

@Injectable()
export class UxModalHelper {
  handleClick(event: any, element: ElementRef): boolean {
    return true;
  }
}
