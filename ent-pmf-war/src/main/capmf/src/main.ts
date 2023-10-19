/**
 * Angular bootstrapping
 */
import {platformBrowserDynamic} from '@angular/platform-browser-dynamic';
import {isDevMode} from '@angular/core';

/**
 * App Module
 * our top level module that holds all of our components
 */
import {AppModule} from './app';
import { enableProdMode } from '@angular/core';
// import {environment} from './environments/environment';

/**
 * Bootstrap our Angular app with a top level NgModule
 */
// if(environment.production || process.env.NODE_ENV === 'int') {
  enableProdMode();
// } else {
//   Error['stackTraceLimit'] = Infinity; // tslint:disable-line:no-string-literal
//   require('zone.js/dist/long-stack-trace-zone'); // tslint:disable-line:no-var-requires
// }

platformBrowserDynamic().bootstrapModule(AppModule);
