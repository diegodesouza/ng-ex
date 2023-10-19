import { ModalBackdropComponent, ModalBackdropOptions } from '../components/modalBackdropCmp';
import { IModalOptions } from '../interfaces/iModalOptions';
import { modalClassNames, modalConfigDefaults, modalSelectors } from '../values/modalOptions';
import { UxHelper } from './../../services/uxHelper';
import {
  AfterViewInit,
  ComponentRef,
  Directive,
  ElementRef,
  EventEmitter,
  HostListener,
  Input,
  OnDestroy,
  Output,
  Renderer
} from '@angular/core';

// todo: in original bs there are was a way to prevent modal from showing
// todo: original modal had resize events
const TRANSITION_DURATION = 300;
const BACKDROP_TRANSITION_DURATION = 150;

//istanbul ignore next
@Directive({
  selector: '[data-uxd-modal]',
  exportAs: 'modal'
})
export class ModalDirective implements AfterViewInit, OnDestroy {
  @Input()
  set config(conf: IModalOptions) {
    this._config = this.getConfig(conf);
  }

  @Output() onShow: EventEmitter<ModalDirective> = new EventEmitter<ModalDirective>();
  @Output() onShown: EventEmitter<ModalDirective> = new EventEmitter<ModalDirective>();
  @Output() onHide: EventEmitter<ModalDirective> = new EventEmitter<ModalDirective>();
  @Output() onHidden: EventEmitter<ModalDirective> = new EventEmitter<ModalDirective>();

  get config(): IModalOptions {
    return this._config;

  }

  // seems like an Options
  isAnimated: boolean = true;

  get isShown(): boolean {
    return this._isShown;
  }

  // todo: implement _dialog
  protected _dialog: any;

  protected _config: IModalOptions;
  protected _isShown: boolean = false;

  protected isBodyOverflowing: boolean = false;
  protected originalBodyPadding: number = 0;
  protected scrollbarWidth: number = 0;

  // reference to backdrop component
  protected backdrop: ComponentRef<ModalBackdropComponent>;

  protected timerHideModal: any = 0;
  protected timerRmBackDrop: any = 0;

  protected get document(): any {
    return this.componentsHelper.getDocument();
  }

  protected hasOpened = false;

  /** Host element manipulations */
  // @HostBinding(`class.${ClassName.IN}`) protected _addClassIn:boolean;

  @HostListener('click', ['$event'])
  onClick(event: any): void {
    if (this.config.ignoreBackdropClick || this.config.backdrop === 'static' || event.target !== this.element.nativeElement) {
      return;
    }

    this.hide(event);
  }

  // todo: consider preventing default and stopping propagation
  @HostListener('keydown.esc')
  onEsc(): void {
    if (this.config.keyboard) {
      this.hide();
    }
  }

  constructor(protected element: ElementRef,
    protected renderer: Renderer,
    protected componentsHelper: UxHelper) {
  }

  ngOnDestroy(): any {
    this.config = void 0;
    // this._element             = null
    // this._dialog              = null
    // this._backdrop            = null
    if (this._isShown) {
      this._isShown = false;
      this.hideModal();
    }
    this._isShown = void 0;
    this.isBodyOverflowing = void 0;
    this.originalBodyPadding = void 0;
    this.scrollbarWidth = void 0;
    this.timerHideModal = void 0;
    this.timerRmBackDrop = void 0;
    if (typeof this.element.nativeElement.remove !== 'undefined' && this.hasOpened) {
      this.element.nativeElement.remove();
    }
    else if (this.document.body.contains(this.element.nativeElement) && this.hasOpened) {
      this.document.body.removeChild(this.element.nativeElement);
    }
  }

  ngAfterViewInit(): any {
    this._config = this._config || this.getConfig();
  }

  /** methods */

  toggle(/*relatedTarget?:ViewContainerRef*/): void {
    return this._isShown ? this.hide() : this.show(/*relatedTarget*/);
  }

  show(/*relatedTarget?:ViewContainerRef*/): void {
    this.hasOpened = true;

    if (this.onShow) {
      this.onShow.emit(this);
    }

    if (this._isShown) {
      return;
    }
    clearTimeout(this.timerHideModal);
    clearTimeout(this.timerRmBackDrop);

    this._isShown = true;

    this.checkScrollbar();
    this.setScrollbar();

    if (this.document && this.document.body) {
      this.renderer.setElementClass(this.document.body, modalClassNames.OPEN, true);
    }

    this.showBackdrop(() => {
      this.showElement(/*relatedTarget*/);
    });
  }

  hide(event?: Event): void {
    if (event) {
      event.preventDefault();
    }

    if (this.onHide) {
      this.onHide.emit(this);
    }

    // todo: add an option to prevent hiding
    if (!this._isShown) {
      return;
    }

    clearTimeout(this.timerHideModal);
    clearTimeout(this.timerRmBackDrop);

    this._isShown = false;
    this.renderer.setElementClass(this.element.nativeElement, modalClassNames.IN, false);
    this.renderer.setElementClass(this.element.nativeElement, modalClassNames.ACTIVE, false);
    // this._addClassIn = false;

    if (this.isAnimated) {
      this.timerHideModal = setTimeout(() => this.hideModal(), TRANSITION_DURATION);
    } else {
      this.hideModal();
    }
  }

  /** Private methods */
  protected getConfig(config?: IModalOptions): IModalOptions {
    return Object.assign({}, modalConfigDefaults, config);
  }

  /**
   *  Show dialog
   */
  protected showElement(/*relatedTarget?:ViewContainerRef*/): void {
    // todo: replace this with component helper usage `add to root`
    //if (!this.element.nativeElement.parentNode ||
    //(this.element.nativeElement.parentNode.nodeType !== Node.ELEMENT_NODE)) {
    // don't move modals dom position
    if (this.document && this.document.body) {
      this.document.body.appendChild(this.element.nativeElement);
    }
    //}

    this.renderer.setElementAttribute(this.element.nativeElement, 'aria-hidden', 'false');
    this.renderer.setElementStyle(this.element.nativeElement, 'display', 'block');
    this.renderer.setElementProperty(this.element.nativeElement, 'scrollTop', 0);

    if (this.isAnimated) {
      UxHelper.reflow(this.element.nativeElement);
    }

    // this._addClassIn = true;
    this.renderer.setElementClass(this.element.nativeElement, modalClassNames.IN, true);
    this.renderer.setElementClass(this.element.nativeElement, modalClassNames.ACTIVE, true);

    const transitionComplete = () => {
      if (this._config.focus) {
        const autoFocusEle = $(this.element.nativeElement).find('[data-autofocus]');
        const lastFocusEle = $(this.element.nativeElement).find('[data-lastfocus]');
        if (autoFocusEle && autoFocusEle.length) {
          autoFocusEle.on('keydown', (e: any) => {
            if (e.shiftKey && e.keyCode === 9) {
              e.preventDefault();
              if (lastFocusEle && lastFocusEle.length) {
                lastFocusEle[0].focus();
              }
              else {
                this.element.nativeElement.focus();
              }
            }
          });
          autoFocusEle.focus();
        }
        else {
          this.element.nativeElement.focus();
        }

        if (lastFocusEle && lastFocusEle.length) {
          lastFocusEle.on('keydown', (e: any) => {
            if (!e.shiftKey && e.keyCode === 9) {
              e.preventDefault();
              if (autoFocusEle && autoFocusEle.length) {
                autoFocusEle[0].focus();
              }
              else {
                this.element.nativeElement.focus();
              }
            }
          });
        }
      }

      if (this.onShown) {
        this.onShown.emit(this);
      }
    };

    if (this.isAnimated) {
      setTimeout(transitionComplete, TRANSITION_DURATION);
    } else {
      transitionComplete();
    }
  }

  protected hideModal(): void {
    this.renderer.setElementAttribute(this.element.nativeElement, 'aria-hidden', 'true');
    this.renderer.setElementStyle(this.element.nativeElement, 'display', 'none');
    this.showBackdrop(() => {
      if (this.document && this.document.body) {
        this.renderer.setElementClass(this.document.body, modalClassNames.OPEN, false);
      }
      this.resetAdjustments();
      this.resetScrollbar();
      if (this.onHidden) {
        this.onHidden.emit(this);
      }
    });
  }

  // todo: original show was calling a callback when done, but we can use promise
  protected showBackdrop(callback?: Function): void {
    if (this._isShown && this.config.backdrop && (!this.backdrop || !this.backdrop.instance.isShown)) {
      this.removeBackdrop();
      this.backdrop = this.componentsHelper
        .appendNextToRoot(
        ModalBackdropComponent,
        ModalBackdropOptions,
        new ModalBackdropOptions({ animate: false }));

      if (this.isAnimated) {
        this.backdrop.instance.isAnimated = this.isAnimated;
        UxHelper.reflow(this.backdrop.instance.element.nativeElement);
      }

      this.backdrop.instance.isShown = true;
      if (!callback) {
        return;
      }

      if (!this.isAnimated) {
        callback();
        return;
      }

      setTimeout(callback, BACKDROP_TRANSITION_DURATION);
    } else if (!this._isShown && this.backdrop) {
      this.backdrop.instance.isShown = false;

      let callbackRemove = () => {
        this.removeBackdrop();
        if (callback) {
          callback();
        }
      };

      if (this.backdrop.instance.isAnimated) {
        this.timerRmBackDrop = setTimeout(callbackRemove, BACKDROP_TRANSITION_DURATION);
      } else {
        callbackRemove();
      }
    } else if (callback) {
      callback();
    }
  }

  protected removeBackdrop(): void {
    if (this.backdrop) {
      this.backdrop.destroy();
      this.backdrop = void 0;
    }
  }

  /** Events tricks */

  // no need for it
  // protected setEscapeEvent():void {
  //   if (this._isShown && this._config.keyboard) {
  //     $(this._element).on(Event.KEYDOWN_DISMISS, (event) => {
  //       if (event.which === 27) {
  //         this.hide()
  //       }
  //     })
  //
  //   } else if (!this._isShown) {
  //     $(this._element).off(Event.KEYDOWN_DISMISS)
  //   }
  // }

  // protected setResizeEvent():void {
  // console.log(this.renderer.listenGlobal('', Event.RESIZE));
  // if (this._isShown) {
  //   $(window).on(Event.RESIZE, $.proxy(this._handleUpdate, this))
  // } else {
  //   $(window).off(Event.RESIZE)
  // }
  // }

  protected resetAdjustments(): void {
    this.renderer.setElementStyle(this.element.nativeElement, 'paddingLeft', '');
    this.renderer.setElementStyle(this.element.nativeElement, 'paddingRight', '');
  }

  /** Scroll bar tricks */

  protected checkScrollbar(): void {
    this.isBodyOverflowing = this.document.body.clientWidth < window.innerWidth;
    this.scrollbarWidth = this.getScrollbarWidth();
  }

  protected setScrollbar(): void {
    if (!this.document) {
      return;
    }

    const fixedEl = this.document.querySelector(modalSelectors.FIXED_CONTENT);

    if (!fixedEl) {
      return;
    }

    const bodyPadding = parseInt(UxHelper.getStyles(fixedEl).paddingRight || 0, 10);
    this.originalBodyPadding = parseInt(this.document.body.style.paddingRight || 0, 10);

    if (this.isBodyOverflowing) {
      this.document.body.style.paddingRight = `${bodyPadding + this.scrollbarWidth}px`;
    }
  }

  protected resetScrollbar(): void {
    this.document.body.style.paddingRight = this.originalBodyPadding;
  }

  // thx d.walsh
  protected getScrollbarWidth(): number {
    const scrollDiv = this.renderer.createElement(this.document.body, 'div', void 0);
    scrollDiv.className = modalClassNames.SCROLLBAR_MEASURER;
    const scrollbarWidth = scrollDiv.offsetWidth - scrollDiv.clientWidth;
    this.document.body.removeChild(scrollDiv);
    return scrollbarWidth;
  }
}
