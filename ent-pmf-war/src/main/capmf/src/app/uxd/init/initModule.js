'use strict';

define(['internal_packages/aaf/core/coreUtils'], function (coreUtils) {
    var mod = angular.module('uxdInitModule', []);
    coreUtils.InitAngularModule(mod);

    return mod;
});
