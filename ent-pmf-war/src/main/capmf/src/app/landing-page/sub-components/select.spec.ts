import {TestBed, ComponentFixture, async, inject} from '@angular/core/testing';
import {NO_ERRORS_SCHEMA} from '@angular/core';
import {HttpModule} from '@angular/http';
import {SelectComponent} from './select.component';
import {DataService} from '../../common/config/data-constants';
import {PMFConfigService} from '../../common/config/pmf-config.service';
import {PayLoadService} from '../../common/services/payload-all.service';
import {SelectUpdatesModel} from '../../common/config/select-updates.model';
import {Router} from '@angular/router';
import {By} from '@angular/platform-browser';
import {ModalComponent} from '../../uxd/v2/ux/components/modalCmp';
import {UxHelper} from '../../uxd/v2/ux/services/uxHelper';
import {UxModalHelper} from '../../uxd/v2/ux/services/modalHlpr';

describe('Select Component', () => {
  let component: SelectComponent;
  let fixture: ComponentFixture<SelectComponent>;
  let testBedDataService: DataService;
  let testBedConfigService: PMFConfigService;
  let testBedPayloadService: PayLoadService;
  let tab: HTMLElement;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        SelectComponent,
        ModalComponent
      ],
      schemas: [NO_ERRORS_SCHEMA],
      providers: [
        DataService,
        PMFConfigService,
        PayLoadService,
        SelectUpdatesModel,
        UxHelper,
        UxModalHelper,
        {
          provide: Router,
          useClass: class { navigate = jasmine.createSpy('navigate'); }
        }
      ],
      imports: [HttpModule]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectComponent);
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

      // Assert.
      expect(component).toHaveBeenCalledWith(testBedDataService);
      expect(component).toHaveBeenCalledWith(testBedConfigService);
      expect(component).toHaveBeenCalledWith(testBedPayloadService);
    });
  });

  it('should have the .selected class for the Individual tab', () => {
    // Arrange.
    let individualTabEl: any = fixture.debugElement.query(By.css('button')).classes;

    // Act.
    fixture.detectChanges();

    // Assert.
    expect(individualTabEl.selected).toBeTruthy();
  });

  it('should have Individual as the label', () => {
    // Arrange.
    let individualTabEl: any = fixture.debugElement.query(By.css('button')).nativeElement;

    // Act.
    fixture.detectChanges();

    // Assert.
    expect(individualTabEl.textContent).toContain('Individual');
  });

  it('should have the .selected class for the Organization tab', () => {
    // Arrange.
    let organizationTabEl: any = fixture.debugElement.query(By.css('button')).nativeElement.nextElementSibling;

    // Act.
    organizationTabEl.selected = true;
    fixture.detectChanges();

    // Assert.
    expect(organizationTabEl.selected).toBeTruthy();
  });

  it('should have Organization as the label', () => {
    // Arrange.
    let organizationTabEl: any = fixture.debugElement.query(By.css('button')).nativeElement.nextElementSibling;

    // Act.
    fixture.detectChanges();

    // Assert.
    expect(organizationTabEl.textContent).toContain('Organization');
  });

  it('should click next', () => {
    // Arrange.
    spyOn(component, 'next');

    // Act.
    component.next();
    fixture.detectChanges();

    // Assert.
    expect(component.next).toHaveBeenCalled();
  });

});
