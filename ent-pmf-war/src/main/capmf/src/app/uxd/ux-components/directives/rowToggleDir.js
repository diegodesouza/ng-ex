'use strict';

define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {

  var injectParams = [];

  /* istanbul ignore next */
  var uxdRowToggleDir = function () {
    return {
      restrict: 'A',
      link: function (scope, element, attrs, ctrl) {
        var jElem = angular.element(element[0]);
        var toggleCtrl = jElem.find('.ant-toggle-ctrl');
        var toggleBody = jElem.find('.ant-toggle-body');
        var toggleCtrlGlyph = jElem.find('.ant-toggle-ctrl .fa');
        var inClass = attrs.outClass || 'fa-minus-circle';
        var outClass = attrs.inClass || 'fa-plus-circle';

        scope.manualToggle = handleToggle;
        init();

        function init() {
          toggleCtrl.bind('click', function () {
            handleToggle();
          });
        }

        function handleToggle() {
          if (!toggleBody.hasClass('in')) {
            toggleBody.addClass('in');
            toggleCtrl.removeClass('collapsed');
            toggleCtrlGlyph.removeClass(outClass);
            toggleCtrlGlyph.addClass(inClass);
            toggleCtrl.attr('aria-expanded', 'true');
          }
          else {
            toggleBody.removeClass('in');
            toggleCtrl.addClass('collapsed');
            toggleCtrlGlyph.removeClass(inClass);
            toggleCtrlGlyph.addClass(outClass);
            toggleCtrl.attr('aria-expanded', 'false');
          }

        }
      }
    };
  };

  uxdRowToggleDir.$inject = injectParams;

  mod.register('directive', 'uxdRowToggleDir', uxdRowToggleDir);

});
