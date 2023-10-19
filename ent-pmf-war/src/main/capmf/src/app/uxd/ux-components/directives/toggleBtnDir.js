'use strict';

/**
 * @author Manas Mehrotra
 * @description styled toggle switch input
 */
define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {

  var injectParams = [];

  /* istanbul ignore next */
  var uxdToggleBtnDir = function () {
    return {
      restrict: 'A',
      require: 'ngModel',
      link: function (scope, element, attrs, ngModel) {
        var jChkBoxInput = angular.element(element[0]);
        var jChkBoxLabel = jChkBoxInput.next();

        init();

        function init() {
          if (jChkBoxInput.is(':checked') || (ngModel &&
            ((attrs.ngTrueValue && attrs.ngTrueValue.indexOf(ngModel.$viewValue) >= 0) || (!attrs.ngTrueValue && ngModel.$viewValue)))) {
            jChkBoxLabel.addClass('active');
          }

          jChkBoxInput.on('click', function () {
            handleClick();
          });

          jChkBoxInput.on('focus', function () {
            jChkBoxLabel.addClass('focus');
          });

          jChkBoxInput.on('blur', function () {
            jChkBoxLabel.removeClass('focus');
          });

          ngModel.$formatters.push(function (value) {
            if ((attrs.ngTrueValue && attrs.ngTrueValue.indexOf(value) >= 0) || (!attrs.ngTrueValue && value)) {
              jChkBoxLabel.addClass('active');
            }
            else if ((attrs.ngFalseValue && attrs.ngFalseValue.indexOf(value) >= 0) || (!attrs.ngFalseValue && !value)) {
              jChkBoxLabel.removeClass('active');
            }

            // Do stuff here, and return the formatted value.
            return value;
          });

          function handleClick() {
            if (jChkBoxLabel.hasClass('active')) {
              jChkBoxLabel.removeClass('active');

              if (ngModel) {
                ngModel.$setViewValue(false);
              }
            }
            else {
              jChkBoxLabel.addClass('active');

              if (ngModel) {
                ngModel.$setViewValue(true);
              }
            }

          }
        }
      }
    };
  };

  uxdToggleBtnDir.$inject = injectParams;

  mod.register('directive', 'uxdToggleBtnDir', uxdToggleBtnDir);

});
