import { Attribute, Component, OnInit, Renderer } from '@angular/core';

//istanbul ignore next
@Component({
  moduleId: module.id,
  selector: '[data-uxd-back-to-top-cmp]',
  template: `<a href="javascript:void(0)" class="round-button back-to-top-dir" (click)="scrollToPosition()" [hidden]="!showButton">
    <span class="fa fa-arrow-up black" aria-hidden="true"></span>
    {{buttonTitle}}
</a>`,
styles: ['[hidden] { display: none !important;}']
})
export class BackToTopComponent implements OnInit {
  public startPixel: number;
  public buttonTitle: string;
  public showButton: boolean = false;
  public pixelFromTop: number;
  public mobileView: boolean;

  constructor( @Attribute('data-starting-pixel') startPoint: number,
    @Attribute('data-button-title') buttonText: string,
    @Attribute('data-pixel-from-top') pixelFromTopPosition: number,
    @Attribute('data-mobile-view') mobileView: boolean,
    private renderer: Renderer) {
    this.startPixel = startPoint;
    this.buttonTitle = buttonText;
    this.pixelFromTop = pixelFromTopPosition;
    this.mobileView = mobileView;
  }

  ngOnInit() {
    if ((this.mobileView === true) && ($(window).width() >= 800)) {
      this.showButton = false;
    }
    else {
      this.renderer.listenGlobal('window', 'scroll', (event: any) => {
        if (window.pageYOffset >= this.startPixel) {
            if ($('.back-to-top-dir').offset().top + $('.back-to-top-dir').height() >= $('.mbr-bottom-of-page').offset().top - 15) {
              $('.back-to-top-dir').addClass('backtotop-fixed');
            }

            this.showButton = true;
        } else {
            this.showButton = false;
        }

        if ($(document).scrollTop() + window.innerHeight < $('.mbr-bottom-of-page').offset().top + 30) {
          $('.back-to-top-dir').removeClass('backtotop-fixed');
        }
      });
    }
  }

  public scrollToPosition(): void {
    $('html, body').animate({scrollTop: this.pixelFromTop}, 'slow');
  }
}
