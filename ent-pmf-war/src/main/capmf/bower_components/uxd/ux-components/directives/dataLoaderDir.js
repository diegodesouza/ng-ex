'use strict';

/**
 * @author Lakmal Molligoda
 * @description global ajax indicator
 */
define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {

  var injectParams = [];

  /* istanbul ignore next */
  var uxdDataLoaderDir = function () {
    return {
      restrict: 'A',
      template: '<div>' +
                  '<div class="ant-data-load" >' +
                    '<div class="sk-spinner sk-spinner-wave ant-data-spin">' +
                      '<div class="sk-rect1"></div>' +
                      '<div class="sk-rect2"></div>' +
                      '<div class="sk-rect3"></div>' +
                      '<div class="sk-rect4"></div>' +
                      '<div class="sk-rect5"></div>' +
                    '</div>' +
                    '<div class="ant-data-text" data-ng-transclude>Your request is processing...</div>' +
                  '</div>' +
                '</div>',
      transclude: true,
      link: function (scope, element, attrs, ngModel) {

      }
    };
  };

  uxdDataLoaderDir.$inject = injectParams;

  mod.register('directive', 'uxdDataLoaderDir', uxdDataLoaderDir);
});
