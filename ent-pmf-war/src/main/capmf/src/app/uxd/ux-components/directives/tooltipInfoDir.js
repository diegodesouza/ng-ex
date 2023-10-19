'use strict';

define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {
    var injectParams = [];

    var uxdTooltipInfoDirPopup = function () {
        return {
            restrict: 'EA',
            replace: true,
            scope: { content: '@', placement: '@', popupClass: '@', animation: '&', isOpen: '&', manualHide: '&' },
            templateUrl: 'internal_packages/uxd/ux-components/views/tooltipInfo.html'
        };
    };

    uxdTooltipInfoDirPopup.$inject = injectParams;
    mod.register('directive', 'uxdTooltipInfoDirPopup', uxdTooltipInfoDirPopup);

    injectParams = ['$uxdUiTooltip'];

    var uxdTooltipInfoDir = function ($tooltip) {
        return $tooltip('uxdTooltipInfoDir', 'tooltip', 'mouseenter');
    };

    uxdTooltipInfoDir.$inject = injectParams;
    mod.register('directive', 'uxdTooltipInfoDir', uxdTooltipInfoDir);
});
