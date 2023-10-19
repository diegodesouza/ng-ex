'use strict';

define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {

  var injectParams = ['$http', '$q', '$uxdUiModal', '$timeout', '$rootScope'];

  var uxdModalHlpr = function ($http, $q, $modal, $timeout, $rootScope) {
    var modalDefaults = {
      /*backdrop: 'static',
      keyboard: true,
      modalFade: true,
      templateUrl: 'ux-components/views/modal.html',
      windowClass: 'modal-center modal fade in'*/
      backdrop: 'static',
      keyboard: true,
      closeOnEscape: true,
      animation: false,
      templateUrl: 'internal_packages/uxd/ux-components/views/modal.html'
    };

    var modalOptions = {
      headerText: 'Proceed?',
      headerSubText: '',
      bodyText: 'Perform this action?',
      showButtons: true,
      showHeader: true,
      showClose: true,
      buttons: [
        {
          text: 'Close',
          result: 'close'
        },
        {
          text: 'Continue Session',
          result: 'continue-session'
        }
      ],
      init: function () { },
      data: {}
    };

    this.showModal = showModal;

    this.show = show;

    this.openModal = openModal;

    this.closeModal = closeModal;

    function openModal(uniqueClassName, template, openCallback, data) {
      var modalDef = {};
      if (uniqueClassName) {
        modalDef.windowClass = ' ' + uniqueClassName;
      }

      if (template) {
        modalDef.templateUrl = template;
      }

      return showModal(modalDef, {
        init: openCallback || modalOptions.init,
        data: data
      });
    }

    function closeModal(modalInstance) {
      if (modalInstance && modalInstance !== null) {
        modalInstance.close();
      }
    }

    function showModal(customModalDefaults, customModalOptions) {
      if (!customModalDefaults) {
        customModalDefaults = {};
      }

      if (!customModalOptions) {
        customModalOptions = {};
      }

      customModalDefaults.backdrop = 'static';
      return show(customModalDefaults, customModalOptions);
    }

    function show(customModalDefaults, customModalOptions) {

      //Create temp objects to work with since we're in a singleton service
      var tempModalDefaults = {};
      var tempModalOptions = {};

      //Map angular-ui modal custom defaults to modal defaults defined in service
      angular.extend(tempModalDefaults, modalDefaults, customModalDefaults);

      //Map modal.html $scope custom properties to defaults defined in service
      angular.extend(tempModalOptions, modalOptions, customModalOptions);

      if (!tempModalDefaults.controller) {
        var ModalController = function ($scope, $modalInstance) {
          $scope.modalOptions = tempModalOptions;
          $scope.modalOptions.close = function (result) {
            $modalInstance.close();
          };

        };

        ModalController.$inject = ['$scope', '$modalInstance'];
        tempModalDefaults.controller = ModalController;
      }

      return $modal.open(tempModalDefaults);
    }

  };

  uxdModalHlpr.$inject = injectParams;

  mod.register('service', 'uxdModalHlpr', uxdModalHlpr);

});
