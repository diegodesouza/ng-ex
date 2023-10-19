'use strict';

/**
 * @author Lakmal Molligoda
 * @description swipe effect on table rows.
 */
define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {

  var injectParams = ['$swipe',
    '$document',
    '$window',
    '$timeout'];

  //istanbul ignore next
  var uxdSwipeToDelDir = function ($swipe,
    $document,
    $window,
    $timeout) {

    return {
      restrict: 'A',
      template: '<div class="uxd-item-swipe-wrapper" style="position: relative">' +
      '<div class="uxd-swiper" data-ng-style="swiperStyle" data-ng-transclude style="position: relative"></div>' +
      '<div class="uxd-undo-div" data-ng-style="undoStyle" data-ng-click="proceed = false"><span class="pull-left uxd-undo-text">Archived</span><button class="pull-right uxd-undo-btn" data-ng-click="handleUndoClick()"><span class="fa md fa-archive gray-outline"></span><span class="sr-only">Undo</span></button></div>' +
      '<div class="uxd-peek-div" data-ng-style="peekStyle" data-ng-click="proceed = false"><span class="fa md fa-archive salmon"></span></div>' +
      '<div class="uxd-pend-div" data-ng-style="pendStyle" ><div data-uxd-data-loader-dir><span data-ng-bind-html="::pendingText"></span></div></div>' +
      '</div>',
      transclude: true,
      scope: {
        onRemove: '&',
        swipeData: '@',
        completed: '=',
        pendingText: '@',
        onUndoAction: '&',
        onCompleteAction: '&'
      },
      link: {
        //TODO: move this component
        /* istanbul ignore next */
        post: function postLink(scope, iElement, iAttrs, controller) {
          var startCoords, $swiper, undoTimeout;
          $swiper = angular.element('.uxd-swiper', iElement);
          scope.proceed = false;
          scope.handleUndoClick = handleUndoClick;
          scope.undoStyle = {
            display: 'none'
          };
          scope.peekStyle = {
            display: 'none'
          };
          scope.pendStyle = {
            display: 'none'
          };

          function fullSwipe(coords) {
            var movedWidth = coords.x > 0 ? startCoords.x - coords.x : Math.abs(coords.x) + startCoords.x;
            return ((startCoords.x > coords.x) && (movedWidth > $swiper.width() * (1 / 2))) ? true : false;
          }

          function cssPrefix(property, value) {
            var vendors = ['', '-o-', '-moz-', '-ms-', '-khtml-', '-webkit-'];
            var styles = {};
            for (var i = vendors.length - 1; i >= 0; i--) {
              styles[vendors[i] + property] = value;
            }

            return styles;
          }

          function updateElementPosition(pos) {
            if ('threeD' in iAttrs) {
              $swiper.css(cssPrefix('transform', 'translate(' + pos + 'px)'));
            } else {
              $swiper.css('left', pos);
            }
          }

          scope.$watch('completed', function (val) {
            if (typeof val !== 'undefined' && !val) {
              updateElementPosition(0);
            }
          });

          $swipe.bind($swiper, {
            'start': function (coords) {
              if (window.innerWidth <= 800) {
                startCoords = coords;
                scope.swiperStyle = { opacity: 0.5 };
                scope.peekStyle.display = 'block';
                scope.$apply();
              }
            },
            'cancel': function () {
              if (window.innerWidth <= 800) {
                scope.swiperStyle = cssPrefix('transition', 'all 0.2s ease-in-out');
                scope.swiperStyle.opacity = 1;
                scope.peekStyle.display = 'none';
                scope.$apply();
              }
            },
            'move': function (coords) {
              if (window.innerWidth <= 800) {
                updateElementPosition(coords.x - startCoords.x);
              }
            },
            'end': function (endCoords) {
              if (window.innerWidth <= 800) {
                if (fullSwipe(endCoords)) {
                  scope.proceed = true;
                  scope.pendStyle.display = 'block';
                  var promise = scope.onRemove();
                  onComplete(promise);
                } else {
                  scope.proceed = false;
                  updateElementPosition(0);
                }

                scope.swiperStyle = cssPrefix('transition', 'all 0.2s ease-in-out');
                scope.swiperStyle.opacity = 1;
                scope.peekStyle.display = 'none';
                scope.$apply();
              }
            }
          });

          function onComplete(promise) {
            if (promise) {
              promise.then(function (result) {
                scope.pendStyle.display = 'none';
                updateElementPosition(-1000);
                if (scope.onCompleteAction) {
                  scope.undoStyle.display = 'block';
                  undoTimeout = $timeout(function () {
                    scope.onCompleteAction();
                  }, parseInt(iAttrs.undoTimeout ? iAttrs.undoTimeout : '0'));
                }
              }, function (error) {
                scope.pendStyle.display = 'none';
                updateElementPosition(-1000);
              });
            }
          }

          function handleUndoClick() {
            if (undoTimeout) {
              $timeout.cancel(undoTimeout);
            }

            if (scope.onUndoAction) {
              scope.onUndoAction();
            }
          }
        }
      }
    };
  };

  uxdSwipeToDelDir.$inject = injectParams;

  mod.register('directive', 'uxdSwipeToDelDir', uxdSwipeToDelDir);

});
