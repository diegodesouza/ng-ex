'use strict';

define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {
    var injectParams = [];

    var uxdTooltipHelpDirPopup = function () {
        return {
            restrict: 'EA',
            replace: true,
            scope: { title: '@', content: '@', placement: '@', popupClass: '@', animation: '&', isOpen: '&' },
            templateUrl: 'internal_packages/uxd/ux-components/views/tooltipHelp.html'
        };
    };

    uxdTooltipHelpDirPopup.$inject = injectParams;
    mod.register('directive', 'uxdTooltipHelpDirPopup', uxdTooltipHelpDirPopup);

    injectParams = ['$uxdUiTooltip'];

    var uxdTooltipHelpDir = function ($tooltip) {
        var tooltip = $tooltip('uxdTooltipHelpDir', 'tooltip', 'focus');
        /*var compile = angular.copy(tooltip.compile);
        tooltip.compile = function (element, attrs) {
            console.log(attrs);
            attrs.$observe('errorType', function (val) {
                var errorType = JSON.parse(val);
                if (typeof errorType['required'] != 'undefined') {
                    attrs.tcpTooltipError = 'required error';
                    attrs.$$element[0].setAttribute('tcp-tooltip-error', 'required error');
                    console.log(attrs);
                }
            });
            return compile(element, attrs);
        };*/
        return tooltip;
    };

    uxdTooltipHelpDir.$inject = injectParams;
    mod.register('directive', 'uxdTooltipHelpDir', uxdTooltipHelpDir);
});
