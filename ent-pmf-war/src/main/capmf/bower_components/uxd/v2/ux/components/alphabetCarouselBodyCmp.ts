import { Component } from '@angular/core';

@Component({
  moduleId: module.id,
  selector: '[data-uxd-alphabet-body-cmp]',
  template: `
      <ng-content></ng-content>
  `,
  host: {
    '[class.active]': 'isActive',
    '[class.hidden]': '!isActive',
    '[id]': 'resultId'
  }
})

/**
 *  Child class for the purpose of Alphabet Carousel Component transclusion
 *
 *  @param Boolean { isActive } Boolean to set 'active' class on body container
 *  @see AlphabetCarouselComponent.ts
 */
export class AlphabetCarouselBodyComponent {
  protected isActive: boolean;
  private resultId: String;

  /**
   *  Builds the ID necessary for this body content container. This ID
   *  will be primarily used for accessibility -- aria-controls
   */
  buildIdString(id: string, index: number) {
    this.resultId = id + '-body-' + index;
  }

  getIsActive(): boolean {
    return this.isActive;
  }

  setIsActive(bool: boolean): void {
    this.isActive = bool;
  }
}
