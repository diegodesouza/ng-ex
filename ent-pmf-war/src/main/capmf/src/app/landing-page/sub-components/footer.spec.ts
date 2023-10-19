import {TestBed, ComponentFixture, async, inject} from '@angular/core/testing';
import {NO_ERRORS_SCHEMA} from '@angular/core';
import {PMFConfigService} from '../../common/config/pmf-config.service';
import {FooterComponent} from './footer-component';

describe('Footer Component', () => {
  let component: FooterComponent;
  let fixture: ComponentFixture<FooterComponent>;
  let testBedService: PMFConfigService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [FooterComponent],
      schemas: [NO_ERRORS_SCHEMA],
      providers: [PMFConfigService]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FooterComponent);
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
    inject([PMFConfigService], (injectedService: PMFConfigService) => {

      // Act.
      testBedService = TestBed.get(PMFConfigService);

      // Assert.
      expect(component).toHaveBeenCalledWith(testBedService);
    });
  });
});
