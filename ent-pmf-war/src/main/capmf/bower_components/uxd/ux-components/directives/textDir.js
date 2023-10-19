'use strict';

/**
 * @author Lakmal Molligoda
 * @description styled text input
 */
define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {

  var injectParams = [];

  /* istanbul ignore next */
  var uxdTextDir = function () {
    return {
      restrict: 'A',
      require: '?ngModel',
      link: function (scope, element, attrs, ngModel) {
        init();

        function init() {
          var jElem = angular.element(element[0]);

          jElem.on('focus', function () {
            jElem.addClass('focus');
          });

          jElem.on('blur', function () {
            jElem.removeClass('focus');
            if (jElem.val() !== '') {
              jElem.addClass('hasValue');
            } else {
              jElem.removeClass('hasValue');
            }
          });

          if (ngModel) {
            ngModel.$formatters.push(function (value) {
              if (typeof value !== 'undefined' && value !== '') {
                jElem.addClass('hasValue');
              } else {
                jElem.removeClass('hasValue');
              }

              // Do stuff here, and return the formatted value.
              return value;
            });
          }

          if (attrs.isDate) {
            $(jElem).datepicker({
              showOn: 'button',
              buttonText: '<span class="fa fa-calendar"></span>',
              showOtherMonths: true,
              beforeShow: function () {
                if (attrs.maxDate) {
                  $(jElem).datepicker('option', 'maxDate', attrs.maxDate);
                }

                if (attrs.minDate) {
                  $(jElem).datepicker('option', 'minDate', attrs.minDate);
                }

                jElem.next().find('.fa').removeClass('fa-calendar').addClass('fa-remove');
              },
              onClose: function () {
                if ($(this).val() !== '') {
                  $(this).addClass('hasValue');
                } else {
                  $(this).removeClass('hasValue');
                }

                $(this).focus();
                jElem.next().find('.fa').addClass('fa-calendar').removeClass('fa-remove');
              }
            });

            $(window).resize(function () {
              jElem.datepicker('hide');
            });

            if (attrs.maxDate) {
              $(jElem).datepicker('option', 'maxDate', attrs.maxDate);
            }

            if (attrs.minDate) {
              $(jElem).datepicker('option', 'minDate', attrs.minDate);
            }

            jElem.next().prop('tabindex', '-1');
          }
        }
      }
    };
  };

  uxdTextDir.$inject = injectParams;

  mod.register('directive', 'uxdTextDir', uxdTextDir);

});
