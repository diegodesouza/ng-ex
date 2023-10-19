'use strict';

/**
 * @author Lakmal Molligoda
 * @description directive that can be used to open modal upon element jquery event
 * Usage: <a data-uxd-open-modal-dir data-modal-open-event="modal open event (Ex: click)" data-modal-css-class="unique css class for the modal" data-modal-html-template="modal template id"></a>
 */
define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {

  var injectParams = ['uxdModalHlpr'];

  /* istanbul ignore next */
  var uxdOpenModalDir = function (uxdModalHlpr) {
    return {
      restrict: 'A',
      link: function (scope, element, attrs, ngModel) {
        var jElem = angular.element(element[0]);
        init();

        function init() {
          jElem.bind(attrs.modalOpenEvent, function (e) {
            openModal();
          });
        }

        function openModal() {
          var modData = {};
          uxdModalHlpr.openModal(attrs.modalCssClass, attrs.modalHtmlTemplate, null, modData);
        }

      }
    };
  };

  uxdOpenModalDir.$inject = injectParams;

  mod.register('directive', 'uxdOpenModalDir', uxdOpenModalDir);

});
