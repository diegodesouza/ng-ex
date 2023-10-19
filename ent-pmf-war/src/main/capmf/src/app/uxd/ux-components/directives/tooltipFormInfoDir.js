'use strict';

define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {
    var injectParams = [];

    //istanbul ignore next
    var uxdTooltipFormInfoDirPopup = function () {
        return {
            restrict: 'EA',
            replace: true,
            scope: { content: '@', placement: '@', popupClass: '@', animation: '&', isOpen: '&', manualHide: '&' },
            templateUrl: 'internal_packages/uxd/ux-components/views/tooltipFormInfo.html'
        };
    };

    uxdTooltipFormInfoDirPopup.$inject = injectParams;
    mod.register('directive', 'uxdTooltipFormInfoDirPopup', uxdTooltipFormInfoDirPopup);

    injectParams = ['$uxdUiTooltip'];

    //istanbul ignore next
    var uxdTooltipFormInfoDir = function ($tooltip) {
        return $tooltip('uxdTooltipFormInfoDir', 'tooltip', 'focus');
    };

    uxdTooltipFormInfoDir.$inject = injectParams;
    mod.register('directive', 'uxdTooltipFormInfoDir', uxdTooltipFormInfoDir);
});
