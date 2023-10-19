'use strict';

define(['internal_packages/aaf/core/coreUtils'], function (coreUtils) {
    var mod = angular.module('uxdUxCompModule', [
        'uxd.ui.bootstrap.tpls',
        'uxd.ui.bootstrap.collapse',
        'uxd.ui.bootstrap.accordion',
        'uxd.ui.bootstrap.alert',
        'uxd.ui.bootstrap.bindHtml',
        'uxd.ui.bootstrap.buttons',
        'uxd.ui.bootstrap.carousel',
        'uxd.ui.bootstrap.dateparser',
        'uxd.ui.bootstrap.position',
        'uxd.ui.bootstrap.datepicker',
        'uxd.ui.bootstrap.dropdown',
        'uxd.ui.bootstrap.modal',
        'uxd.ui.bootstrap.pagination',
        'uxd.ui.bootstrap.tooltip',
        'uxd.ui.bootstrap.popover',
        'uxd.ui.bootstrap.progressbar',
        'uxd.ui.bootstrap.rating',
        'uxd.ui.bootstrap.tabs',
        'uxd.ui.bootstrap.timepicker',
        'uxd.ui.bootstrap.transition',
        'uxd.ui.bootstrap.typeahead'
    ]);

    coreUtils.InitAngularModule(mod);

    return mod;
});
