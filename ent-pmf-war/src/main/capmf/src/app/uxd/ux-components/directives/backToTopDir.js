'use strict';

/**
 * @author Manas Mehrotra
 * @description Scroll back to top of page directive
 */
define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {

  var injectParams = ['$window'];

  /* istanbul ignore next */
  var uxdBackToTopDir = function ($window) {
    return {
      restrict: 'A',
      scope: {startingPixel: '@', pixelFromTop: '@', buttonTitle: '@', mobileView: '@'},
      transclude: true,
      templateUrl: 'internal_packages/uxd/ux-components/views/backToTop.html',
      link: function (scope, element, attrs, ngModel) {
        scope.scrollToPosition = scrollToPosition;

        function scrollToPosition() {
          $('html, body').animate({scrollTop: scope.pixelFromTop}, 'slow');
        }

        var displayScrollButton = function () {
          var backToTopElementObj = angular.element(element[0]);
          if (this.pageYOffset >= scope.startingPixel) {
              if ($('.back-to-top-dir').offset().top + $('.back-to-top-dir').height() >= $('.mbr-bottom-of-page').offset().top - 15) {
                $('.back-to-top-dir').addClass('backtotop-fixed');
              }

              //scope.showButton = true;
              if (backToTopElementObj.find('.back-to-top-dir').hasClass('hidden')) {
                backToTopElementObj.find('.back-to-top-dir').toggleClass('hidden');
              }
          } else {
              
              //scope.showButton = false;
              if (!backToTopElementObj.find('.back-to-top-dir').hasClass('hidden')) {
                backToTopElementObj.find('.back-to-top-dir').toggleClass('hidden');
              }
          }

          if ($(document).scrollTop() + $window.innerHeight < $('.mbr-bottom-of-page').offset().top + 30) {
            $('.back-to-top-dir').removeClass('backtotop-fixed');
          }

          //scope.$apply();
        };

        init();

        function init() {
          var backToTopInitElement = angular.element(element[0]);
          backToTopInitElement.find('.back-to-top-dir').toggleClass('hidden');
          
          //scope.showButton = false;

          if ((scope.mobileView === 'true') && ($(window).width() >= 800)) {
            //scope.showButton = false;
            if (!backToTopInitElement.find('.back-to-top-dir').hasClass('hidden')) {
              backToTopInitElement.find('.back-to-top-dir').toggleClass('hidden');
            }
          }
          else {
            angular.element($window).on('scroll', displayScrollButton);
          }

          scope.$on('$destroy', function () {
            angular.element($window).off('scroll', displayScrollButton);
          });
        }
      }
    };
  };

  uxdBackToTopDir.$inject = injectParams;

  mod.register('directive', 'uxdBackToTopDir', uxdBackToTopDir);
});
