'use strict';

/**
 * @author Lakmal Molligoda
 * @description styled drop down list
 */
define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {

  var injectParams = [];

  /* istanbul ignore next */
  var uxdBtnDdlDir = function () {
    return {
      restrict: 'A',
      template: '<button type="button" class="btn dropdown-toggle" aria-expanded="false">' +
      '<span class="buttonMain">What can we help you with today?</span>' +
      '<span class="fa fa-caret-down"></span>' +
      '<span class="sr-only">Toggle Dropdown</span>' +
      '</button>' +
      '<div class="ant-main-div" data-ng-transclude="">' +
      '</div>',
      require: '?ngModel',
      transclude: true,
      scope: {
        onChange: '&'
      },
      link: function (scope, element, attrs, ngModel) {
        var jElem = angular.element(element[0]);
        var btn = jElem.find('button');
        var items;
        var active = jElem.find('.buttonMain');
        var hiddenLi = jElem.find('.dropdown-menu > li > *');
        for (var i = 0; i < hiddenLi.length; i++) {
          var curElm = hiddenLi[i];
          if (curElm.style.display === 'none') {
                curElm.parentNode.parentNode.removeChild(curElm.parentNode);
            }
        }

        var dropdown = jElem.find('.dropdown-menu > li');
        var id = jElem.attr('id');
        init();

        function init() {
          if (attrs.asyncLoad) {
            scope.$on('asyncLoadComplete', function (e) {
              internalInit();
            });
          }
          else {
            internalInit();
          }
        }

        function internalInit() {
          jElem.find('.buttonMain').html(attrs.label);
          btn.addClass(attrs.btnClass);
          initializeOptions();
          bindDocMouseUp(id);
        }

        function initializeOptions() {
          var click = 0;
          btn.bind('click.' + id, function (e) {
            if (jElem.hasClass('open') && click > 0) {
              btn.attr('aria-expanded', 'false');
              jElem.removeClass('open');
            }
            else {
              btn.attr('aria-expanded', 'true');
              jElem.addClass('open');
            }

            click++;
          });

          btn.bind('focus.' + id, function (e) {
            if (!$(this).hasClass('active')) {
              btn.attr('aria-expanded', 'true');
              jElem.addClass('open');
              dropdown.eq(0).addClass('active');
            }

            btn.bind('focusout.' + id, function () {
              click = 0;
              dropdown.removeClass('active');
            });
          });

          items = jElem.find('div.ant-main-div li');
          if (items.length) {
            dropdown.bind('mouseover.' + id, function (e) {
              dropdown.removeClass('active');
            });

            btn.bind('keydown.' + id, function (e) {
              var key = e.which || e.keyCode;
              var selected = dropdown.filter('.active');
              var current;

              dropdown.removeClass('active');

              switch (key) {
                case 38: // up
                  if (!selected.length || selected.is(':first-child')) {
                    current = dropdown.last();
                  }
                  else {
                    current = selected.prev();
                  }

                  active.html(current.find('a:first').text());
                  current.addClass('active');
                  break;

                case 40: // down
                  if (!selected.length || selected.is(':last-child')) {
                    current = dropdown.eq(0);
                  }
                  else {
                    current = selected.next();
                  }

                  active.html(current.find('a:first').text());
                  current.addClass('active');
                  break;

                case 13: // enter
                  closeDropdown();
                  selected.find('a:not(".ng-hide")').click();
                  break;

                case 27: // esc
                  closeDropdown();
                  break;

                case 9: // tab
                  setTimeout(function () {
                    closeDropdown();
                  }, 100);
                  break;

                default: return; // exit this handler for other keys
              }

              e.preventDefault(); // preventing page scroll
              return false;
            });
          }
        }

        function bindDocMouseUp(id) {
          unbindDocMouseUp(id);
          $(document).bind('mouseup', function (e) {
            if (!jElem.is(e.target) &&
              jElem.has(e.target).length === 0 &&
              jElem.hasClass('open')) {
              closeDropdown();
            }
          });
        }

        function unbindDocMouseUp(id) {
          $(document).unbind('mouseup.' + id);
        }

        function closeDropdown() {
          jElem.removeClass('open');
          btn.blur();
        }
      }
    };
  };

  uxdBtnDdlDir.$inject = injectParams;

  mod.register('directive', 'uxdBtnDdlDir', uxdBtnDdlDir);

});
