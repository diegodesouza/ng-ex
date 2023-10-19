import {TestBed, ComponentFixture, async, inject} from '@angular/core/testing';
import {NO_ERRORS_SCHEMA} from '@angular/core';
import {DescriptionComponent} from './description.component';
import {DataService} from '../../common/config/data-constants';
import {PMFConfigService} from '../../common/config/pmf-config.service';

describe('Description Component', () => {
  let component: DescriptionComponent;
  let fixture: ComponentFixture<DescriptionComponent>;
  let testBedService: DataService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DescriptionComponent ],
      schemas: [NO_ERRORS_SCHEMA],
      providers: [DataService, PMFConfigService]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DescriptionComponent);
    component = fixture.componentInstance;

    fixture.detectChanges();
  });

  afterEach(() => {
    testBedService = null;
    component = null;
  });

  it('should be defined', () => {
    // Arrange.

    // Act.

    // Assert.
    expect(component).toBeDefined();
  });

  it('should be called with the service', () => {
    // Arrange.
    inject([DataService], (injectedService: DataService) => {

      // Act.
      testBedService = TestBed.get(DataService);

      // Assert.
      expect(component).toHaveBeenCalledWith(testBedService);
    });
  });

});
