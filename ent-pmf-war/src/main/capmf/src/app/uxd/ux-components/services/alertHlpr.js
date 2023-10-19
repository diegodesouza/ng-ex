'use strict';

/*istanbul ignore next*/
define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {

  var injectParams = ['$http', '$q', '$timeout', '$rootScope'];

  /*istanbul ignore next*/
  var uxdAlertHlpr = function ($http, $q, $timeout, $rootScope) {

    this.openAlert = openAlert;

    this.closeAlert = null;

    var modalData = {
      alertText: '',
      alertType: '',
      autoHideTimeout: ''
    };

    function openAlert(alertText, alertType, autoHideTimeout) {
      modalData.alertText = alertText;
      modalData.alertType = alertType;
      modalData.autoHideTimeout = autoHideTimeout;
      this.handleAlert(modalData); // jshint ignore:line
    }
  };

  /*istanbul ignore next*/
  uxdAlertHlpr.$inject = injectParams;

  /*istanbul ignore next*/
  mod.register('service', 'uxdAlertHlpr', uxdAlertHlpr);

});
