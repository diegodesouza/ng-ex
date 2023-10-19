'use strict';

/**
 * @author Lakmal Molligoda
 * @description styled drop down list
 */
define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {

  var injectParams = ['aafContentHlpr',
    '$compile',
    '$timeout',
    '$location',
    'uxdConfigConst',
    '$templateRequest'];

  /* istanbul ignore next */
  var uxdTabCtrlDir = function (aafContentHlpr,
    $compile,
    $timeout,
    $location,
    uxdConfigConst,
    $templateRequest) {

    return {
      restrict: 'A',
      templateUrl: 'internal_packages/uxd/ux-components/views/tabCtrl.html',
      scope: {
      },
      link: function (scope, element, attrs, ngModel) {
        var jElem = angular.element(element[0]);
        var tabCtrls = [];
        var tabAccds = [];
        var currentScope = null;
        var content = aafContentHlpr.getWcsContent('uxdTabCtrlDir')[attrs.id];

        scope.tabs = content.tabs;
        scope.tabClick = tabClick;

        init();

        function init() {
          $timeout(function () {
            setupTabs();
          }, 100);

          scope.$on('$locationChangeSuccess', function (event, newUrl, oldUrl) {
            if (hasTab()) {
              setupTabs();
            }
          });
          
          jElem.on('click', '.active-control', function () {
            $(this).toggleClass('open');
          });
        }

        function hasTab() {
          var r = false;
          angular.forEach(scope.tabs, function (t) {
            if (t.name === getLocationHash()) {
              r = true;
            }
          });

          return r;
        }

        function tabClick(hash) {
          $location.hash(hash);
        }

        function setupTabs() {
          tabCtrls = jElem.find('[data-control]');
          tabAccds = jElem.find('[data-tab-container]');
          
          for (var index = 0; index < scope.tabs.length; index++) {
            var tab = scope.tabs[index];
            if (tab.name === getLocationHash()) {
              loadTab(index);
              break;
            }
          }

          if (index === scope.tabs.length) {
            loadTab(0);
          }
        }

        function getLocationHash() {
          var arr = $location.hash().split('?');
          return arr.length ? arr[0] : '';
        }

        function loadTab(index) {

          tabCtrls.removeClass('active-control open').attr('tabindex', '0');
          tabAccds.removeClass('active-item');

          var activeList = jElem.find('[data-control="tab' + index + '"]');
          activeList.addClass('active-control').attr('tabindex', '-1');
          var offset = 0;
          if (angular.element('.mbr-top-of-page-wrapper').length) {
            offset = activeList.eq(1).offset().top - angular.element('.mbr-top-of-page-wrapper').height();
          } else {
            offset = activeList.eq(1).offset().top;
          }

          $('html, body').animate({ scrollTop: offset });
          jElem.find('[data-tab-container="tab' + index + '"]').addClass('active-item');
          var tabCtrl = jElem.find('[data-tab-container="tab' + index + '"]');
          var reUseIsUndefinedNullOrFalse = isValueUndefinedNullOrFalse(attrs.reuse);
          if (tabCtrl.attr('data-loaded') === 'false' || reUseIsUndefinedNullOrFalse) {

            if (reUseIsUndefinedNullOrFalse && currentScope) {
              currentScope.$destroy();
            }

            var currentTab = jElem.find('#tab-ctrl-container-dynamic-content');
            if (reUseIsUndefinedNullOrFalse && (currentTab && currentTab.length > 0)) {
              currentTab.remove();
            }

            tabCtrl.empty().append(
              '<div class="ant-data-load" >' +
              '<div class="sk-spinner sk-spinner-wave ant-data-spin">' +
              '<div class="sk-rect1"></div>' +
              '<div class="sk-rect2"></div>' +
              '<div class="sk-rect3"></div>' +
              '<div class="sk-rect4"></div>' +
              '<div class="sk-rect5"></div>' +
              '</div>' +
              '<div class="ant-data-text" >' + content.labels.lblLoad + '</div>' +
              '</div>');

            $templateRequest(uxdConfigConst.web.getTabUrl(uxdConfigConst.web.tabUrlPrefix, scope.tabs[index].urlToLoad)).then(function successCallback(response) {
              loadSuccess(tabCtrl, activeList, response);
              tabCtrl.attr('data-loaded', 'true');
            }, function errorCallback(response) {
              loadFail(tabCtrl);
              tabCtrl.attr('data-loaded', 'false');
            });
          }
        }

        function isValueUndefinedNullOrFalse(value) {
          return ((value === undefined || value === null) || (value !== false && value !== 'false'));
        }

        function loadSuccess(tabCtrl, activeList, content) {

          var element = angular.element('<div id = "tab-ctrl-container-dynamic-content"></div>');
          element.append(content);
          currentScope = scope.$new();
          $compile(element)(currentScope);
          tabCtrl.empty().append(element);
          setTimeout(function () {
            activeList.addClass('open');
          }, 100);
        }

        function loadFail(tabCtrl) {
          tabCtrl.empty().html('<div  class="ant-anthem-alert ant-negative negative" >' +
            '<div class="media-left media-middle">' +
            '<span class="fa fa-exclamation-circle"></span>' +
            '</div>' +
            '<div class="media-body">' +
            '<p>' + content.labels.lblLoadFail + '</p>' +
            '</div>' +
            '</div>');
        }
      }
    };
  };

  uxdTabCtrlDir.$inject = injectParams;

  mod.register('directive', 'uxdTabCtrlDir', uxdTabCtrlDir);

});
