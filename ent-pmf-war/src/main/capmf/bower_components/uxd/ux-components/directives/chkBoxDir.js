'use strict';

/**
 * @author Lakmal Molligoda
 * @description styled radio button input
 */
define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {

  var injectParams = [];

  /* istanbul ignore next */
  var uxdChkBoxDir = function () {
    return {
      restrict: 'A',
      require: '?ngModel',
      link: function (scope, element, attrs, ngModel) {

        var jChkBoxInput = angular.element(element[0]);
        var jChkBoxLabel = jChkBoxInput.next();
        var groupParent = null;
        var groupChildren = null;

        init();

        function init() {

          jChkBoxLabel.on('click', function (e, fromChild, fromSelectAll) {
            if (!jChkBoxLabel.hasClass('pcDisabled')) {
              handleClick(e, fromChild, fromSelectAll);
            }
          });

          jChkBoxInput.bind('keydown', function (e, fromChild, fromSelectAll) {
            if (e.keyCode === 32) {
              e.preventDefault();
              handleClick(e, fromChild, fromSelectAll);
            }
          });

          function handleClick(e, fromChild, fromSelectAll) {
            if (jChkBoxLabel.hasClass('active')) {
              jChkBoxLabel.removeClass('active');
              jChkBoxInput.removeAttr('data-checked');
              if (ngModel) {
                ngModel.$setViewValue(false);
              }

              if (attrs.selectAll === 'false') {
                if (typeof getGroupParent().attr('data-checked') !== 'undefined' && !fromSelectAll) {
                  getGroupParent().next().trigger('click', [true, false]);

                  setTimeout(function () {
                    jChkBoxInput.focus();
                  }, 50);
                }
                else {
                    jChkBoxInput.focus();
                }
              }
              else if (attrs.selectAll === 'true' && !fromChild) {
                getGroupChildren().each(function () {
                  if (typeof $(this).attr('data-checked') !== 'undefined') {
                    $(this).next().trigger('click', [false, true]);
                  }
                });

                setTimeout(function () {
                  getGroupParent().focus();
                }, 50);
              }
            } else {
              jChkBoxLabel.addClass('active');

              jChkBoxInput.attr('data-checked', 'checked');
              if (ngModel) {
                ngModel.$setViewValue(true);
              }

              if (attrs.selectAll === 'false' && !fromSelectAll && typeof getGroupParent().attr('data-checked') === 'undefined') {
                var allChecked = true;
                getGroupChildren().each(function () {
                  if (typeof $(this).attr('data-checked') === 'undefined' && !$(this).is(':disabled')) {
                    allChecked = false;
                    return;
                  }
                });

                if (allChecked) {
                  getGroupParent().next().trigger('click', [true, false]);

                  setTimeout(function () {
                    jChkBoxInput.focus();
                  }, 50);
                }
                else {
                    jChkBoxInput.focus();
                }
              }
              else if (attrs.selectAll === 'true' && !fromChild) {
                getGroupChildren().each(function () {
                  if (typeof $(this).attr('data-checked') === 'undefined') {
                    $(this).next().trigger('click', [false, true]);
                  }
                });

                setTimeout(function () {
                  getGroupParent().focus();
                }, 50);
              }
            }
          }

          jChkBoxInput.on('focus', function () {
            jChkBoxLabel.addClass('focus');
          });

          jChkBoxInput.on('blur', function () {
            jChkBoxLabel.removeClass('focus');
          });

          if (ngModel) {
            ngModel.$formatters.push(function (value) {
              if (value) {
                jChkBoxLabel.addClass('active');
              }
              else {
                jChkBoxLabel.removeClass('active');
              }

              // Do stuff here, and return the formatted value.
              return value;
            });

          }
        }

        function getGroupParent() {
          if (groupParent === null) {
            groupParent = $('input[data-group="' + attrs.group + '"][data-select-all="true"]');
          }

          return groupParent;
        }

        function getGroupChildren() {
          if (groupChildren === null) {
            groupChildren = $('input[data-group="' + attrs.group + '"][data-select-all="false"]');
          }

          return groupChildren;
        }
      }
    };
  };

  uxdChkBoxDir.$inject = injectParams;

  mod.register('directive', 'uxdChkBoxDir', uxdChkBoxDir);
});
