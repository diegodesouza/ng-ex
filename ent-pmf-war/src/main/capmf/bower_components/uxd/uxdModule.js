'use strict';

define(['internal_packages/aaf/core/coreUtils'], function (coreUtils) {
  var mod = angular.module('uxdModule', [
    'aafModule',
    'uxdInitModule',
    'uxdUxCompModule',

  //angular core modules
    'ngRoute',
    'ngAnimate',
    'ngAria',
    'ngSanitize',
    'ngTouch',
    'ngCookies',
    'ngResource',
    'ngMessages',

    //3rd party modules
  ]);

  coreUtils.InitAngularModule(mod);

  var injectParams = [];

  function configure() {
    //TODO: add config options
  }

  configure.$inject = injectParams;

  mod.config(configure);
  return mod;
});
