import {
  ApplicationRef,
  ComponentFactoryResolver,
  ComponentRef,
  EventEmitter,
  Injectable,
  Injector,
  ReflectiveInjector,
  ResolvedReflectiveProvider,
  Type,
  ViewContainerRef
} from '@angular/core';
import { DOCUMENT } from '@angular/platform-browser';

//istanbul ignore next
/**
 *  Helper provider to handle various element level functions.
 */
@Injectable()
export class UxHelper {
  root: ViewContainerRef;

  private _events: Array<any> = [];

  constructor(protected applicationRef: ApplicationRef,
    protected componentFactoryResolver: ComponentFactoryResolver,
    protected injector: Injector) {
  }

  static reflow(element: any): void {
    new Function('bs', 'return bs')(element.offsetHeight);
  }

  // source: https://github.com/jquery/jquery/blob/master/src/css/var/getStyles.js
  static getStyles(elem: any): any {
    // Support: IE <=11 only, Firefox <=30 (#15098, #14150)
    // IE throws on elements created in popups
    // FF meanwhile throws on frame elements through "defaultView.getComputedStyle"
    let view = elem.ownerDocument.defaultView;

    if (!view || !view.opener) {
      view = window;
    }

    return view.getComputedStyle(elem);
  }

  getDocument(): any {
    return this.injector.get(DOCUMENT);
  }

  /**
   * In some cases, like using ngUpgrate,
   * you need to explicitly set view container ref
   * to made this method working you need to add:
   * ```typescript
   *  @Component({
   *   selector: 'my-app',
   *   ...
   *   })
   *  export class MyApp {
   *    constructor(componentsHelper:ComponentsHelper, viewContainerRef: ViewContainerRef) {
   *        // A Default view container ref, usually the app root container ref.
   *        // Has to be set manually until we can find a way to get it automatically.
   *        componentsHelper.setRootViewContainerRef(viewContainerRef)
   *      }
   *  }
   * ```
   */
  setRootViewContainerRef(value: ViewContainerRef): void {
    this.root = value;
  }

  /**
   * This is a name conventional class to get application root view component ref
   * @returns {ViewContainerRef} - application root view component ref
   */
  getRootViewContainerRef(): ViewContainerRef {
    // https://github.com/angular/angular/issues/9293
    if (this.root) {
      return this.root;
    }

    const comps = this.applicationRef.components;

    if (!comps.length) {
      throw new Error(`ApplicationRef instance not found`);
    }

    try {
      /* one more ugly hack, read issue above for details */
      const rootComponent = (this.applicationRef as any)._rootComponents[0];
      this.root = rootComponent._hostElement.vcRef;
      return this.root;
    } catch (e) {
      throw new Error(`ApplicationRef instance not found`);
    }
  }

  /**
   * Creates an instance of a Component and attaches it to the View Container found at the
   * `location` specified as {@link ViewContainerRef}.
   *
   * You can optionally provide `providers` to configure the {@link Injector} provisioned for this
   * Component Instance.
   *
   * Returns {@link ComponentRef} representing the newly created Component.
   * @param {ComponentClass} - @Component class
   * @param {location} - reference to the location
   * @param {providers} - optional array of providers
   * @returns {ComponentRef<T>} - returns ComponentRef<T>
   */
  appendNextToLocation<T>(componentClass: Type<T>,
    location: ViewContainerRef,
    providers?: ResolvedReflectiveProvider[]): ComponentRef<T> {
    let componentFactory = this.componentFactoryResolver.resolveComponentFactory(componentClass);
    let parentInjector = location.parentInjector;
    let childInjector: Injector = parentInjector;
    if (providers && providers.length > 0) {
      childInjector = ReflectiveInjector.fromResolvedProviders(providers, parentInjector);
    }

    return location.createComponent(componentFactory, location.length, childInjector);
  }

  /**
   * Helper methods to add ComponentClass(like modal backdrop) with options
   * of type ComponentOptionsClass to element next to application root
   * or next to provided instance of view container
   * @param {ComponentClass} - @Component class
   * @param {ComponentOptionsClass} - options class
   * @param {options} - instance of options
   * @returns {ComponentRef<T>} - returns ComponentRef<T>
   */
  appendNextToRoot<T>(componentClass: Type<T>,
    componentOptionsClass: any,
    options: any): ComponentRef<T> {
    /* renamed lctn cause of checkmarx */
    let lctn = this.getRootViewContainerRef();
    let providers = ReflectiveInjector.resolve([
      { provide: componentOptionsClass, useValue: options }
    ]);
    return this.appendNextToLocation(componentClass, lctn, providers);
  }

  getUxEvents(eventName: string) {
    if (this._events.find((e) => e.name === eventName)) {
      return this._events.find((e) => e.name === eventName).value;
    }

    let e = {
      name: eventName,
      value: new EventEmitter<any>()
    };

    this._events.push(e);

    return e.value;

  }
}
