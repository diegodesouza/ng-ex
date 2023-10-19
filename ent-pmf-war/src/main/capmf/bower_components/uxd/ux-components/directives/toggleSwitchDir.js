'use strict';

/**
 * @author Manas Mehrotra
 * @description styled toggle button input
 */
define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {

  var injectParams = [];

  /* istanbul ignore next */
  var uxdToggleSwitchDir = function () {
    return {
      restrict: 'A',
      require: 'ngModel',
      link: function (scope, element, attrs, ngModel) {
        var jChkBoxInput = angular.element(element[0]);
        var jChkBoxLabel = jChkBoxInput.next();
        var name = attrs.name;

        init();

        function init() {

          if (jChkBoxInput.attr('checked')) {
            jChkBoxInput.next().addClass('active');
          }

          jChkBoxInput.on('click', function () {
            handleClick();
            jChkBoxInput.trigger('focus');

            if (ngModel) {
              ngModel.$setViewValue(jChkBoxInput.val());
            }
          });

          jChkBoxInput.on('focus', function () {
            jChkBoxLabel.addClass('focus');
          });

          jChkBoxInput.on('blur', function () {
            jChkBoxLabel.removeClass('focus');
          });

          ngModel.$formatters.push(function (value) {
            if (typeof value !== 'undefined' && value !== '' && (jChkBoxInput.val() === value || attrs.value === value)) {
              handleClick();
            }

            // Do stuff here, and return the formatted value.
            return value;
          });

          function handleClick() {
            if (name) {
              $('input:radio[name=' + name + ']').each(function () {
                $(this).next().removeClass('active');
                $(this).next().removeClass('focus');
              });
            }

            jChkBoxLabel.addClass('active');
          }
        }
      }
    };
  };

  uxdToggleSwitchDir.$inject = injectParams;

  mod.register('directive', 'uxdToggleSwitchDir', uxdToggleSwitchDir);

});
