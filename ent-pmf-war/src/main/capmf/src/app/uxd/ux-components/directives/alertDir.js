'use strict';

/**
 * @author Lakmal Molligoda
 * @description global alert message directive
 */
define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {

  var injectParams = [];

  /* istanbul ignore next */
  var uxdAlertDir = function () {
    return {
      restrict: 'A',
      template: '<div class="ant-anthem-alert">' +
                  '<div class="media">' +
                    '<div class="media-left media-middle">' +
                      '<span class="fa "></span>' +
                    '</div>' +
                    '<div class="media-body media-middle ">' +
                      '<p data-ng-transclude>ALERT MESSAGE HERE</p>' +
                    '</div>' +
                  '</div>' +
                '</div>',
      transclude: true,
      link: function (scope, element, attrs, ngModel) {
        var jElem = angular.element(element[0]);

        init();

        function init() {
          var alertClass = 'fa-info';
          var rootClass = '';
          if (attrs.alertType === 'positive') {
            alertClass = 'fa-check';
            rootClass = 'ant-positive';
          }
          else if (attrs.alertType === 'negative') {
            alertClass = 'fa-exclamation';
            rootClass = 'ant-negative';
          }

          jElem.find('div.media-left span.fa').addClass(alertClass);

          if (rootClass) {
            jElem.find('.ant-anthem-alert').addClass(rootClass);
          }
        }
      }
    };
  };

  uxdAlertDir.$inject = injectParams;

  mod.register('directive', 'uxdAlertDir', uxdAlertDir);
});
