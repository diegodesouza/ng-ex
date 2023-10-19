'use strict';

/**
 * @author Lakmal Molligoda
 * @description styled radio button input
 */
define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {

  var injectParams = [];

  /* istanbul ignore next */
  var uxdRbtnDir = function () {
    return {
      restrict: 'A',
      require: 'ngModel',
      link: function (scope, element, attrs, ngModel) {
        init();

        function init() {
          var jChkBoxInput = angular.element(element[0]);
          var jChkBoxLabel = jChkBoxInput.next();
          var name = attrs.name;

          if (jChkBoxInput.attr('checked')) {
            jChkBoxInput.next().addClass('active');
          }

          jChkBoxInput.on('click', function () {
            if (attrs.optional === 'true' && $('input:radio[name="' + name + '"]:checked').length && jChkBoxInput.next().hasClass('active')) {
              $(this).next().removeClass('active');
              if (ngModel) {
                ngModel.$setViewValue('');
              }
            }
            else {
              handleClick();
              jChkBoxInput.trigger('focus');

              if (ngModel) {
                ngModel.$setViewValue(jChkBoxInput.val());
              }
            }
          });

          jChkBoxLabel.on('click', function () {
            if ((typeof attrs.optional === 'undefined' || attrs.optional !== 'true') && !jChkBoxLabel.hasClass('prDisabled')) {
              jChkBoxInput.trigger('click');
            }
          });

          jChkBoxInput.on('focus', function () {
            jChkBoxLabel.addClass('focus');
          });

          jChkBoxInput.on('blur', function () {
            jChkBoxLabel.removeClass('focus');
          });

          ngModel.$formatters.push(function (value) {
            if (typeof value !== 'undefined' && value !== '' && jChkBoxInput.val() === value) {
              handleClick();
            }
            else if (!value) {
              jChkBoxLabel.removeClass('active');
              jChkBoxLabel.removeClass('focus');
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

  uxdRbtnDir.$inject = injectParams;

  mod.register('directive', 'uxdRbtnDir', uxdRbtnDir);

});
