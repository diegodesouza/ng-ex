'use strict';

define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {
    var injectParams = [];

    //istanbul ignore next
    var uxdTooltipErrorDirPopup = function () {
        return {
            restrict: 'EA',
            replace: true,
            scope: { title: '@', content: '@', placement: '@', popupClass: '@', animation: '&', isOpen: '&' },
            templateUrl: 'internal_packages/uxd/ux-components/views/tooltipError.html'
        };
    };

    uxdTooltipErrorDirPopup.$inject = injectParams;
    mod.register('directive', 'uxdTooltipErrorDirPopup', uxdTooltipErrorDirPopup);

    injectParams = ['$uxdUiTooltip'];

    //istanbul ignore next
    var uxdTooltipErrorDir = function ($tooltip) {
        var tooltip = $tooltip('uxdTooltipErrorDir', 'tooltip', 'focus');
        return tooltip;
    };

    uxdTooltipErrorDir.$inject = injectParams;
    mod.register('directive', 'uxdTooltipErrorDir', uxdTooltipErrorDir);
});
