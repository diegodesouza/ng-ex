'use strict';

define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {

  var injectParams = [];

  /* istanbul ignore next */
  var uxdBlockToggleDir = function () {
    return {
      restrict: 'A',
      link: function (scope, element, attrs, ctrl) {
        var jElem = angular.element(element[0]);
        var toggleCtrl = jElem.find('.ant-responsive-collapse-heading');
        
        init();

        function init() {
          toggleCtrl.bind('click', function () {
            handleToggle();
          });
        }

        function handleToggle() {
          jElem.toggleClass('ant-block-expanded');
        }
      }
    };
  };

  uxdBlockToggleDir.$inject = injectParams;

  mod.register('directive', 'uxdBlockToggleDir', uxdBlockToggleDir);
});
