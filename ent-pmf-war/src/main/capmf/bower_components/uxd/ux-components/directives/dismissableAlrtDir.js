'use strict';

/**
 * @author Manas Mehrotra
 * @description Dismissable Alert Pattern
 */
define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {

  var injectParams = ['uxdAlertHlpr'];

  /* istanbul ignore next */
  var uxdDismissableAlrtDir = function (uxdAlertHlpr) {
    return {
      restrict: 'A',
      scope: { content: '@', dismissType: '@' },
      transclude: true,
      templateUrl: 'internal_packages/uxd/ux-components/views/dismissableAlert.html',
      link: function (scope, element, attrs, ngModel) {
        var alertTimeout;
        var preventReload = false;
        var baseElement = angular.element(element[0].getElementsByClassName('ant-anthem-alert'));
        var prevFocusElem = null;
        init();

        function init() {
          element.bind('click', createDismissableAlert);
          angular.element(element[0].getElementsByClassName('ant-dismiss-alert')).attr('role', 'alert');

          uxdAlertHlpr.handleAlert = function (data) {
            if ((data.alertText !== '') && (data.alertText !== null)) {
              scope.content = data.alertText;
              scope.dismissType = data.alertType;
              scope.timeOut = data.autoHideTimeout;
              preventReload = false;
              if (document.activeElement) {
                prevFocusElem = document.activeElement;
              }

              createDismissableAlert();
            }
          };

          uxdAlertHlpr.closeAlert = function () {
            closeAlert();
          };
        }

        var closeButton = angular.element(element[0].getElementsByClassName('ant-dismiss-alert'));
        closeButton.on('click', function () {
          closeAlert();
          preventReload = true;
          if (prevFocusElem != null) {
            prevFocusElem.focus();
            prevFocusElem = null;
          }
        });

        function closeAlert() {
          baseElement.removeClass('on');
          baseElement.attr('aria-hidden', 'true');
          baseElement.attr('tabIndex', -1);
        }

        function createDismissableAlert() {
          var timer = scope.timeOut || 4000;
          if (!preventReload) {
            baseElement.addClass('on');
            baseElement.attr('aria-hidden', 'false');
            baseElement.attr('tabIndex', 0);
            baseElement.find('.ant-dismiss-alert').focus();

            if (timer > 0) {
              if (alertTimeout) {
                clearTimeout(alertTimeout);
              }

              alertTimeout = setTimeout(function () {
                baseElement.removeClass('on');
                baseElement.attr('aria-hidden', 'true');
                baseElement.attr('tabIndex', -1);
                if (prevFocusElem != null) {
                  prevFocusElem.focus();
                  prevFocusElem = null;
                }
              }, timer);
            }
          }

          preventReload = false;
          scope.timeOut = 4000;
        }
      }
    };
  };

  uxdDismissableAlrtDir.$inject = injectParams;

  mod.register('directive', 'uxdDismissableAlrtDir', uxdDismissableAlrtDir);
});
