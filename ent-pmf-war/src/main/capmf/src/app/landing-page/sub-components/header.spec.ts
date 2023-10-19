import {TestBed, ComponentFixture, async, inject} from '@angular/core/testing';
import {NO_ERRORS_SCHEMA} from '@angular/core';
import {HeaderComponent} from './header.component';
import {PMFConfigService} from '../../common/config/pmf-config.service';
import {DataService} from '../../common/config/data-constants';

describe('Header Component', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;
  let testBedService: PMFConfigService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [HeaderComponent],
      schemas: [NO_ERRORS_SCHEMA],
      providers: [PMFConfigService, DataService]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderComponent);
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

  it('should create an instance of Component', () => {
    // Arrange.

    // Act.

    // Assert.
    expect(component).toBeTruthy();
  });

  it('should be called with the service', () => {
    // Arrange.
    inject([PMFConfigService], (injectedService: PMFConfigService) => {

      // Act.
      testBedService = TestBed.get(PMFConfigService);

      // Assert.
      expect(component).toHaveBeenCalledWith(testBedService);
    });
  });
});
