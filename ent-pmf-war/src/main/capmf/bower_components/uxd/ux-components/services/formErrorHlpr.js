'use strict';

/*istanbul ignore next*/
define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {

  var injectParams = ['$http', '$q', '$rootScope'];

  /*istanbul ignore next*/
  var uxdFormErrorHlpr = function ($http, $q, $rootScope) {

    this.displayErrorMessage = displayErrorMessage;
    this.hideErrorMessage = hideErrorMessage;

    var errorData = {
      contentText: '',
      errorClass: ''
    };

    var errorElement = {
      forElement: ''
    };

    function displayErrorMessage(contentText, errorClass, forElement) {
      errorData.contentText = contentText;
      errorData.errorClass = errorClass;
      errorData.forElement = forElement;
      this.displayFormError(errorData); // jshint ignore:line
    }

    function hideErrorMessage(forElement) {
      errorElement.forElement = forElement;
      this.hideFormError(errorElement); // jshint ignore:line
    }
  };

  /*istanbul ignore next*/
  uxdFormErrorHlpr.$inject = injectParams;

  /*istanbul ignore next*/
  mod.register('service', 'uxdFormErrorHlpr', uxdFormErrorHlpr);

});
