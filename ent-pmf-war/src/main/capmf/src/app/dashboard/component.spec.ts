import { NO_ERRORS_SCHEMA } from '@angular/core';
import { async, TestBed, ComponentFixture, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

/**
 * Load the implementations that should be tested.
 */
import {DashboardComponent} from './component';
import {PayLoadService} from '../common/services/payload-all.service';
import {ScrollService} from '../common/services/scroll.service';

xdescribe(`Home`, () => {
  let comp: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;
  let injector: TestBed;
  let service: PayLoadService;
  let httpMock: HttpTestingController;

  /**
   * async beforeEach.
   */
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [DashboardComponent],
      schemas: [NO_ERRORS_SCHEMA],
      imports: [HttpClientTestingModule],
      providers: [
        PayLoadService,
        ScrollService
      ]
    })

    /**
     * Compile template and css.
     */
      .compileComponents();
    injector = getTestBed();
    service = injector.get(PayLoadService);
    httpMock = injector.get(HttpTestingController);
  }));

  /**
   * Synchronous beforeEach.
   */
  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardComponent);
    comp = fixture.componentInstance;

    /**
     * Trigger initial data binding.
     */
    fixture.detectChanges();
  });

  xit('should have default data', () => {
    expect(comp.ngAfterViewInit).toHaveBeenCalled();
  });

});
