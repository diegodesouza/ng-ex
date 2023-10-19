import {TestBed, ComponentFixture, async, inject} from '@angular/core/testing';
import {LandingPageComponent} from './component';
import {DataService} from '../common/config/data-constants';
import {PMFConfigService} from '../common/config/pmf-config.service';
import {Component, NO_ERRORS_SCHEMA} from '@angular/core';
import {HttpModule} from '@angular/http';
import {PayLoadService} from '../common/services/payload-all.service';
import {SelectUpdatesModel} from '../common/config/select-updates.model';

describe('Landing Page Component', () => {
  let component: LandingPageComponent;
  let fixture: ComponentFixture<LandingPageComponent>;
  let testBedDataService: DataService;
  let testBedConfigService: PMFConfigService;
  let testBedPayloadService: PayLoadService;
  let testBedSelectUpdatesModel: SelectUpdatesModel;

  @Component({
    selector: 'landing-description',
    template: '<p>Mock Description Component</p>'
  })
  class MockDescriptionComponent {}

  @Component({
    selector: 'landingpage-select',
    template: '<p>Mock Select Component</p>'
  })
  class MockSelectComponent {}

  @Component({
    selector: 'app-header',
    template: '<p>Mock Header Component</p>'
  })
  class MockHeaderComponent {}

  @Component({
    selector: 'app-footer',
    template: '<p>Mock Footer Component</p>'
  })
  class MockFooterComponent {}

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        LandingPageComponent,
        MockDescriptionComponent,
        MockSelectComponent,
        MockHeaderComponent,
        MockFooterComponent
      ],
      schemas: [NO_ERRORS_SCHEMA],
      providers: [
        DataService,
        PMFConfigService,
        PayLoadService,
        SelectUpdatesModel,
      ],
      imports: [HttpModule]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LandingPageComponent);
    component = fixture.componentInstance;
  });

  it('should be defined', () => {
    // Arrange.

    // Act.

    // Assert.
    expect(component).toBeDefined();
  });

  it('should use the services', () => {
    // Arrange.
    inject([ testBedDataService ], ( injectDataService: DataService) => {
      // Act.
      testBedDataService = TestBed.get(DataService);
      testBedConfigService = TestBed.get(PMFConfigService);
      testBedPayloadService = TestBed.get(PayLoadService);
      testBedSelectUpdatesModel = TestBed.get(SelectUpdatesModel);

      // Assert.
      expect(component).toHaveBeenCalledWith(testBedDataService);
      expect(component).toHaveBeenCalledWith(testBedConfigService);
      expect(component).toHaveBeenCalledWith(testBedPayloadService);
      expect(component).toHaveBeenCalledWith(testBedSelectUpdatesModel);
    });
  });

});
