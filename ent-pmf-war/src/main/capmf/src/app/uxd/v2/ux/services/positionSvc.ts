//istanbul ignore next
export class PositionService {
  /**
   * Provides read-only equivalent of jQuery's position function:
   * http://api.jquery.com/position/
   */
  position(nativeEl: HTMLElement): { width: number, height: number, top: number, left: number } {
    let elBCR = this.offset(nativeEl);
    let offsetParentBCR = { top: 0, left: 0 };
    let offsetParentEl = this.parentOffsetEl(nativeEl);
    if (offsetParentEl !== this.document as any) {
      offsetParentBCR = this.offset(offsetParentEl);
      offsetParentBCR.top += offsetParentEl.clientTop - offsetParentEl.scrollTop;
      offsetParentBCR.left += offsetParentEl.clientLeft - offsetParentEl.scrollLeft;
    }

    let boundingClientRect = nativeEl.getBoundingClientRect();
    return {
      width: boundingClientRect.width || nativeEl.offsetWidth,
      height: boundingClientRect.height || nativeEl.offsetHeight,
      top: elBCR.top - offsetParentBCR.top,
      left: elBCR.left - offsetParentBCR.left
    };
  }

  /**
   * Provides read-only equivalent of jQuery's offset function:
   * http://api.jquery.com/offset/
   */
  offset(nativeEl: any): { width: number, height: number, top: number, left: number } {
    let boundingClientRect = nativeEl.getBoundingClientRect();
    return {
      width: boundingClientRect.width || nativeEl.offsetWidth,
      height: boundingClientRect.height || nativeEl.offsetHeight,
      top: boundingClientRect.top + (this.window.pageYOffset || this.document.documentElement.scrollTop),
      left: boundingClientRect.left + (this.window.pageXOffset || this.document.documentElement.scrollLeft)
    };
  }

  /**
   * Provides coordinates for the targetEl in relation to hostEl
   */
  positionElements(hostEl: HTMLElement, targetEl: HTMLElement, positionStr: string, appendToBody: boolean): { top: number, left: number } {
    let positionStrParts = positionStr.split('-');
    let pos0 = positionStrParts[0];
    let pos1 = positionStrParts[1] || 'center';
    let hostElPos = appendToBody ?
      this.offset(hostEl) :
      this.position(hostEl);
    let targetElWidth = targetEl.offsetWidth;
    let targetElHeight = targetEl.offsetHeight;
    let shiftWidth: any = {
      center: (): number => {
        return hostElPos.left + hostElPos.width / 2 - targetElWidth / 2;
      },
      left: (): number => {
        return hostElPos.left;
      },
      right: (): number => {
        return hostElPos.left + hostElPos.width;
      }
    };

    let shiftHeight: any = {
      center: (): number => {
        return hostElPos.top + hostElPos.height / 2 - targetElHeight / 2;
      },
      top: (): number => {
        return hostElPos.top;
      },
      bottom: (): number => {
        return hostElPos.top + hostElPos.height;
      }
    };

    let targetElPos: { top: number, left: number };
    switch (pos0) {
      case 'right':
        targetElPos = {
          top: shiftHeight[pos1](),
          left: shiftWidth[pos0]()
        };
        break;
      case 'left':
        targetElPos = {
          top: shiftHeight[pos1](),
          left: hostElPos.left - targetElWidth
        };
        break;
      case 'bottom':
        targetElPos = {
          top: shiftHeight[pos0](),
          left: shiftWidth[pos1]()
        };
        break;
      default:
        targetElPos = {
          top: hostElPos.top - targetElHeight,
          left: shiftWidth[pos1]()
        };
        break;
    }

    return targetElPos;
  }

  protected get window(): Window {
    return window;
  }

  protected get document(): Document {
    return window.document;
  }

  protected getStyle(nativeEl: HTMLElement, cssProp: string): string {
    // IE
    if ((nativeEl as any).currentStyle) {
      return (nativeEl as any).currentStyle[cssProp];
    }

    if (this.window.getComputedStyle) {
      return (this.window.getComputedStyle(nativeEl) as any)[cssProp];
    }
    // finally try and get inline style
    return (nativeEl.style as any)[cssProp];
  }

  /**
   * Checks if a given element is statically positioned
   * @param nativeEl - raw DOM element
   */
  protected isStaticPositioned(nativeEl: HTMLElement): boolean {
    return (this.getStyle(nativeEl, 'position') || 'static') === 'static';
  }

  /**
   * returns the closest, non-statically positioned parentOffset of a given
   * element
   * @param nativeEl
   */
  protected parentOffsetEl(nativeEl: HTMLElement): any {
    let offsetParent: any = nativeEl.offsetParent || this.document;
    while (offsetParent && offsetParent !== this.document &&
      this.isStaticPositioned(offsetParent)) {
      offsetParent = offsetParent.offsetParent;
    }
    return offsetParent || this.document;
  };
}

export const positionService: PositionService = new PositionService();
