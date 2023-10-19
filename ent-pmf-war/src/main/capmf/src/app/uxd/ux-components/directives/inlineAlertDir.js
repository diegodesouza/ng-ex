'use strict';

/**
 * @author Lakmal Molligoda
 * @description global alert message directive
 */
define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {

  var injectParams = [];

  /* istanbul ignore next */
  var uxdInlineAlertDir = function () {
    return {
      restrict: 'A',
      template: '<p class="ant-inline-alert"> <ng-transclude> </ng-transclude> </p>',
      transclude: true,
      link: function (scope, element, attrs, ngModel) {

        init();

        function init() {
        }
      }
    };
  };

  uxdInlineAlertDir.$inject = injectParams;

  mod.register('directive', 'uxdInlineAlertDir', uxdInlineAlertDir);
});
