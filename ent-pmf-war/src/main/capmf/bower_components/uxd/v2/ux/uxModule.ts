import { AlternateToggleBodyComponent } from './components/altToggleBodyCmp';
import { AlternateToggleComponent } from './components/altToggleCmp';
import { ScrollingTabbedViewBodyComponent } from './components/scrollingTabbedViewBody';
import { ScrollingTabbedViewComponent } from './components/scollingTabbedViewCmp';
import { DateTextboxDirective } from './directives/dateTextboxDir';
import { ToggleButtonGroupComponent } from './components/toggleButtonGroupCmp';
import { AlphabetCarouselBodyComponent } from './components/alphabetCarouselBodyCmp';
import { AlphabetCarouselComponent } from './components/alphabetCarouselCmp';
import { CollapseGroupCmp } from './components/collapseGroupCmp';
import { CollapseItemCmp } from './components/collapseItemCmp';
import { FieldSetWrapperComponent } from './components/fieldsetWrapperCmp';
import { RadioInputGroupComponent } from './components/radioInputGroupCmp';
import { UxdTooltipDirective } from './directives/uxdTooltipDir';
import { UxModalHelper } from './services/modalHlpr';
import { TabbedViewBodyComponent } from './components/tabbedViewBodyCmp';
import { CalloutBlockComponent } from './components/calloutBlockCmp';
import { TabbedTilesComponent } from './components/tabbedTilesCmp';
import { CheckboxInputComponent } from './components/checkboxInputCmp';
import { RadioInputComponent } from './components/radioInputCmp';
import { SmoothScrollDirective } from './directives/smoothScrollDir';
import { ToggleSwitchComponent } from './components/toggleSwitchCmp';
import { ToggleButtonComponent } from './components/toggleButtonCmp';
import { ModalComponent } from './components/modalCmp';
import { FormItemComponent, ObjectKeysPipe } from './components/formItemCmp';
import { AlertHelper } from './services/alertHelper';
import { AjaxLoaderComponent } from './components/ajaxLoaderCmp';
import { BackToTopComponent } from './components/backToTopCmp';
import { ButtonDropDownComponent } from './components/btnDropdownCmp';
import { ButtonDropdownItemDirective } from './components/btnDropdownItemCmp';
import { DateLoaderComponent } from './components/dataLoaderCmp';
import { AlertComponent } from './components/alertCmp';
import { DissmisableAlertComponent } from './components/dissmisableAlertCmp';
import { DropDownComponent } from './components/dropdownCmp';
import { InlineAlertComponent } from './components/inlineAlertCmp';
import { BlockToggleDirective } from './directives/blockToggleDir';
import { AlertComponentV2 } from './components/alertCmpV2';
import { CheckboxDirective } from './directives/checkboxDir';
import { OpenModalDirective } from './directives/openModalDir';
import { TabbedViewComponent } from './components/tabbedViewCmp';
import { RadioButtonDirective } from './directives/radioButtonDir';
import { RowToggleDirective } from './directives/rowToggleDir';
import { TextboxDirective } from './directives/textboxDir';
import { ToggleButtonDirective } from './directives/toggleBtnDir';
import { ToggleSwitchDirective } from './directives/toggleSwitchDir';
import { ModalBackdropComponent } from './modal/components/modalBackdropCmp';
import { ModalDirective } from './modal/directives/modalDir';
import { UxHelper } from './services/uxHelper';
import { TooltipContainerComponent } from './tooltip/components/tooltipContainerCmp';
import { TooltipDirective } from './tooltip/directives/tooltipDir';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { TabbedTileComponent } from './components/tabbedTileCmp';

@NgModule({
  imports: [
    CommonModule,
    FormsModule],
  declarations: [
    AjaxLoaderComponent,
    ModalBackdropComponent,
    ModalDirective,
    DropDownComponent,
    TooltipContainerComponent,
    TooltipDirective,
    InlineAlertComponent,
    TextboxDirective,
    DateLoaderComponent,
    DissmisableAlertComponent,
    RadioButtonDirective,
    CheckboxDirective,
    TabbedViewComponent,
    ToggleButtonDirective,
    ToggleSwitchDirective,
    BlockToggleDirective,
    RowToggleDirective,
    BackToTopComponent,
    ButtonDropDownComponent,
    ButtonDropdownItemDirective,
    OpenModalDirective,
    FormItemComponent,
    CollapseGroupCmp,
    CollapseItemCmp,
    ToggleButtonComponent,
    ToggleSwitchComponent,
    CalloutBlockComponent,
    RadioInputComponent,
    CheckboxInputComponent,
    AlertComponentV2,
    ModalComponent,
    AlertComponent,
    SmoothScrollDirective,
    ToggleButtonGroupComponent,
    TabbedViewBodyComponent,
    TabbedTileComponent,
    TabbedTilesComponent,
    ObjectKeysPipe,
    UxdTooltipDirective,
    FieldSetWrapperComponent,
    RadioInputGroupComponent,
    ScrollingTabbedViewComponent,
    ScrollingTabbedViewBodyComponent,
    DateTextboxDirective,
    AlphabetCarouselComponent,
    AlphabetCarouselBodyComponent,
    AlternateToggleComponent,
    AlternateToggleBodyComponent
  ],
  entryComponents: [
    AjaxLoaderComponent,
    ModalBackdropComponent,
    DropDownComponent,
    TooltipContainerComponent,
    InlineAlertComponent,
    AlertComponent,
    TabbedViewComponent,
    AlertComponentV2,
    ModalComponent,
    DateLoaderComponent,
    DissmisableAlertComponent,
    BackToTopComponent,
    ButtonDropDownComponent,
    FormItemComponent,
    CollapseGroupCmp,
    CollapseItemCmp,
    ToggleButtonComponent,
    ToggleSwitchComponent,
    AlertComponent,
    TabbedTileComponent,
    TabbedTilesComponent,
    RadioInputComponent,
    CheckboxInputComponent,
    RadioInputGroupComponent
  ],
  providers: [
    UxHelper,
    AlertHelper,
    UxModalHelper
  ],
  exports: [
    AjaxLoaderComponent,
    ModalBackdropComponent,
    ModalDirective,
    DropDownComponent,
    TooltipContainerComponent,
    TooltipDirective,
    InlineAlertComponent,
    AlertComponent,
    TextboxDirective,
    DateLoaderComponent,
    DissmisableAlertComponent,
    RadioButtonDirective,
    CheckboxDirective,
    TabbedViewComponent,
    ToggleButtonDirective,
    ToggleSwitchDirective,
    BlockToggleDirective,
    RowToggleDirective,
    ButtonDropDownComponent,
    ButtonDropdownItemDirective,
    OpenModalDirective,
    BackToTopComponent,
    FormItemComponent,
    CollapseGroupCmp,
    CollapseItemCmp,
    ToggleButtonComponent,
    ToggleSwitchComponent,
    CalloutBlockComponent,
    RadioInputComponent,
    CheckboxInputComponent,
    AlertComponentV2,
    SmoothScrollDirective,
    ToggleButtonGroupComponent,
    ModalComponent,
    SmoothScrollDirective,
    SmoothScrollDirective,
    TabbedViewBodyComponent,
    TabbedTileComponent,
    TabbedTilesComponent,
    UxdTooltipDirective,
    FieldSetWrapperComponent,
    RadioInputGroupComponent,
    ScrollingTabbedViewComponent,
    ScrollingTabbedViewBodyComponent,
    DateTextboxDirective,
    AlphabetCarouselComponent,
    AlphabetCarouselBodyComponent,
    AlternateToggleComponent,
    AlternateToggleBodyComponent
  ]
})
export class UxModule { }
