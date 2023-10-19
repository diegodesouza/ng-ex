'use strict';

/**
 * @author Ryan Decker
 * @description listener for element in view
 */
define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {

  var injectParams = [];

  /* istanbul ignore next */
  var uxdInViewDir = function () {
    return {
      restrict: 'A',
      
      link: function (scope, element, attrs, ngModel) {

        var jElem = angular.element(element[0]);
          
        $(window).scroll(function () {
            elemInView();
        });
            
        function elemInView() {
            var pageTop = $(window).scrollTop();
            var pageBottom = pageTop + window.innerHeight;
            var elementTop = jElem.offset().top;
            var elementBottom = elementTop + jElem.height();
            if (elementTop > pageTop && elementBottom < pageBottom) {
                jElem.addClass('animate');
            } else {
                jElem.removeClass('animate');
            }
        }
     }
    };
};

  uxdInViewDir.$inject = injectParams;

  mod.register('directive', 'uxdInViewDir', uxdInViewDir);
});
