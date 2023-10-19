import {TestBed, ComponentFixture, async, inject} from '@angular/core/testing';
import {Component, NO_ERRORS_SCHEMA} from '@angular/core';
import {HttpModule} from '@angular/http';
import {GeneralInfoComponent} from './component';
import {DataService} from '../../common/config/data-constants';
import {PMFConfigService} from '../../common/config/pmf-config.service';
import {PayLoadService} from '../../common/services/payload-all.service';
import {SelectUpdatesModel} from '../../common/config/select-updates.model';
import {OptionsPipe} from '../../common/pipes/options.pipe';
import {FormsModule} from '@angular/forms';
import {PMFDataServiceDetails} from '../../common/config/pmf-data.service';
import {Router} from '@angular/router';
import {FormValidations} from '../../common/validations/form-validations';

describe('General Info Component', () => {
  let component: GeneralInfoComponent;
  let fixture: ComponentFixture<GeneralInfoComponent>;
  let testBedDataService: DataService;
  let testBedConfigService: PMFConfigService;
  let testBedPayloadService: PayLoadService;
  let testBedSelectUpdatesModel: SelectUpdatesModel;
  let form: any;

  @Component({
    selector: 'practice-address',
    template: '<p>Mock Practice Address Component</p>'
  })
  class MockPracticeAddressComponent {}

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        GeneralInfoComponent,
        MockPracticeAddressComponent,
        OptionsPipe
      ],
      schemas: [NO_ERRORS_SCHEMA],
      providers: [
        DataService,
        PMFDataServiceDetails,
        PMFConfigService,
        PayLoadService,
        SelectUpdatesModel,
        FormValidations,
        {
          provide: Router,
          useClass: class { navigate = jasmine.createSpy('navigate'); }
        }
      ],
      imports: [
        HttpModule,
        FormsModule
      ]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GeneralInfoComponent);
    component = fixture.componentInstance;

    form = fixture.componentInstance.currentForm.form;
    component.ngOnInit();
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
