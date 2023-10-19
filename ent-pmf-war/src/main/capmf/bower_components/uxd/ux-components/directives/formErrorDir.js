'use strict';

/**
 * @author Manas Mehrotra
 * @description Error Message for Forms
 * Error message directive is hidden by default, use 'data-display' attribute to display the directive by default
 * Directive is invoked by 'uxdFormErrorHlpr' service. 'displayErrorMessage' to use error message and 'hideErrorMessage' to hide the message.
 */
define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {

  var injectParams = ['uxdFormErrorHlpr'];

  /* istanbul ignore next */
  var uxdFormErrorDir = function (uxdFormErrorHlpr) {
    return {
      restrict: 'A',
      scope: {
        content: '@',
        errorType: '@'
      },
      transclude: true,
      templateUrl: 'internal_packages/uxd/ux-components/views/formError.html',
      link: function (scope, element, attrs, ngModel) {
        init();
        
        function init() {
          if (!attrs.display) {
            hideErrorMessage();
          }
          
          if ((scope.errorType === '') || (scope.errorType === null)) {
            scope.errorType = 'fa fa-exclamation-circle red';
          }

          uxdFormErrorHlpr.displayFormError = function (data) {
            if ((data.contentText !== '') && (data.contentText !== null)) {
              scope.content = data.contentText;
              scope.errorType = data.errorClass;
              showErrorMessage();
            }
          };

          uxdFormErrorHlpr.hideFormError = function (data) {
            if ((data.forElement !== '') && (data.forElement !== null)) {
              $('#' + data.forElement).hide();
            }
          };
        }

        function showErrorMessage() {
          $('#' + attrs.id).show();
        }

        function hideErrorMessage() {
          $('#' + attrs.id).hide();
        }
      }
    };
  };

  uxdFormErrorDir.$inject = injectParams;

  mod.register('directive', 'uxdFormErrorDir', uxdFormErrorDir);
});
