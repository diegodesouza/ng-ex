'use strict';

/**
 * @author Lakmal Molligoda
 * @description styled drop down list
 */
define(['internal_packages/uxd/ux-components/uxCompModule'], function (mod) {

  var injectParams = [];

  /* istanbul ignore next */
  var uxdDdlDir = function () {
    return {
      restrict: 'A',
      template: '<fieldset class="pfSelect">' +
      '<legend></legend>' +
      '<button class="psButton btn btn-primary" tabindex="-1" type="button">' +
      '<span class="psActiveOption">Select One</span>' +
      '<span class="psArrow fa fa-caret-down"></span>' +
      '</button>' +
      '<div style="" class="psDropdown" tabindex="0" data-ng-transclude>' +
      '</div>' +
      '</fieldset>',
      require: 'ngModel',
      transclude: true,
      scope: {
        onChange: '&',
        asyncLoadComplete: '='
      },
      link: function (scope, element, attrs, ngModel) {
        var jElem = angular.element(element[0]);
        if (!jElem.attr('id')) {
          console.log('unique id is required on data-uxd-ddl-dir');
          return;
        }

        var active = jElem.find('.psActiveOption');
        var items;
        var labels;
        var dropdown = jElem.find('.psDropdown');
        var dropdowninner = jElem.find('.psDropdown div');
        var keyInputTimer = 0;
        var keyInput = '';
        var btn = jElem.find('button');
        var prevEle = null;
        var goBack = null;
        var id = jElem.attr('id');
        btn.attr('id', 'psButton' + id);
        active.attr('id', 'psActiveOption' + id);
        jElem.find('legend').attr('id', 'legend' + id);
        dropdown.attr('id', 'psDropdown' + id);

        init();

        function init() {
          if (attrs.asyncLoad === 'true') {
            scope.$watch('asyncLoadComplete', function (e) {
              setTimeout(function () {
                internalInit();
                setDefaultValue(ngModel.$viewValue);
              }, 100);
            });
          }
          else {
            internalInit();
          }
        }

        function internalInit() {
          if (attrs.legendText) {
            jElem.find('legend').html(attrs.legendText);
          }

          if (attrs.defaultVal) {
            active.html(attrs.defaultVal);
          }

          initializeOptions();
          btn.unbind('click.' + id);
          btn.bind('click.' + id, function (e) {
            if (!$(this).next().hasClass('active')) {
              prevEle = dropdown; //added
              handleButton();
              setTimeout(function () {
                if (dropdown.find('.psOption:checked').length) {
                  dropdown.find('.psOption:checked').focus();
                }
              }, (attrs.focusDelay ? parseInt(attrs.focusDelay) : 0));
            } else {
              closeDropdown();
            }
          });

          dropdown.unbind('focus.' + id);
          dropdown.bind('focus.' + id, function (e) {
            if (!$(this).hasClass('active') && goBack === null) {
              prevEle = document.activeElement;
              handleButton();
              setTimeout(function () {
                if (dropdown.find('.psOption:checked').length) {
                  dropdown.find('.psOption:checked').focus();
                }
                else {
                  dropdown.find('.psOption:not(:disabled)')[0].focus();
                }
              }, (attrs.focusDelay ? parseInt(attrs.focusDelay) : 0));
            }

              goBack = null;
          });

          ngModel.$formatters.push(function (value) {
            var val = jElem.find('input[value="' + value + '"]');
            if (!val.next().hasClass('psDisabled')) {
              if (val.length) {
                jElem.find('input').prop('checked', false);
                jElem.find('input').prop('aria-checked', false);
                val.prop('checked', true);
                val.prop('aria-checked', true);
                active.html(val.next().html());

                fireOnChange();
              }
              else {
                jElem.find('input').prop('checked', false);
                jElem.find('input').prop('aria-checked', false);
                active.html(attrs.defaultVal ? attrs.defaultVal : 'Select One');
              }

              return value;
            }
            else {
              return '';
            }
          });

          bindDocMouseUp();
          trackKeyInput();
        }

        function handleButton() {
          if (items.length === 0) {
            initializeOptions();
          }

          openDropdown();
          btn.attr('aria-expanded', 'true');

        }

        function initializeOptions() {
          items = jElem.find('.psDropdown input');
          labels = jElem.find('.psDropdown .psLabel');

          if (labels.length) {
            labels.unbind('click.' + id);
            labels.bind('click.' + id, function (e) {
              if (!$(this).hasClass('psDisabled')) {
                var val = $ESAPI.encoder().encodeForHTML($(this).html());				
                active.html($val);
                var radio = jElem.find('#' + $(this).attr('for'));
                items.prop('checked', false);
                items.prop('aria-checked', false);
                radio.focus().addClass('focus').prop('checked', true);
                if (ngModel) {
                  ngModel.$setViewValue(radio.val());
                }

                fireOnChange();
                setTimeout(function () {
                  closeDropdown();
                }, 100);
                e.preventDefault();
              }
            });
          }

          if (items.length) {
            angular.forEach(items, function (rbtn) {
              if (!$(rbtn).attr('name')) {
                $(rbtn).attr('name', 'rbtn' + id);
              }
            });

            items.unbind('focus.' + id);
            items.bind('focus.' + id, function () {
              if (!jElem.find('input:checked').length) {
                $(this).prop('checked', true);
                $(this).prop('aria-checked', true);
              }

              items.removeClass('focus');
              $(this).addClass('focus');
              var val = $ESAPI.encoder().encodeForHTML($(this).next().html());				
              active.html($val);

              if (!dropdown.hasClass('active')) {
                openDropdown();
              }
            });

            items.unbind('keydown.' + id);
            items.bind('keydown.' + id, function (e) {
              var key = e.which || e.keyCode;

              if (key === 37 || key === 39) { // preventing left & right key to scroll up/down
                return false;
              }

              var selected = dropdown.find('.psOption:checked');
              var current;

              // dropdown.removeClass('active');
              switch (key) {
                case 38: // up
                  if (!selected.length || selected.is(':first-child')) {
                    current = dropdown.find('.psOption').last();
                  }
                  else {
                    current = selected.prev().prev();
                  }

                  dropdown.scrollTop(0);
                  dropdown.scrollTop(current[0].offsetTop);
                  break;

                case 40: // down;
                  if (!selected.length || selected.next().is(':last-child')) {
                    current = dropdown.find('.psOption').eq(0).next();
                  }
                  else {
                    current = selected.next().next().next();
                  }

                  dropdown.scrollTop(0);
                  dropdown.scrollTop(current[0].offsetTop);
                  break;

                case 13: // enter
                  if (ngModel) {
                    ngModel.$setViewValue($(this).val());
                  }

                  closeDropdown();
                  if (e.shiftKey) {
                    prevEle.focus();
                    e.stopPropagation();
                  }

                  break;

                case 27: // esc
                  active.html(attrs.defaultVal ? attrs.defaultVal : 'Select One');
                  closeDropdown();
                  e.stopPropagation();
                  break;

                case 9: // tab
                  if (ngModel) {
                    ngModel.$setViewValue($(this).val());
                  }

                  fireOnChange();
                  setTimeout(function () {
                    closeDropdown();
                  }, 100);

                  if (e.shiftKey && prevEle) {
                    goBack = true;
                    prevEle.focus();
                    e.stopPropagation();
                  }

                  break;

                default: return; // exit this handler for other keys
              }

            });

          }

          if (attrs.hasNgRepeat) {
            setTimeout(function () {
              setDefaultValue(ngModel.$viewValue);
            }, 100);
          }
          else {
            setDefaultValue(ngModel.$viewValue);
          }
        }

        function setDefaultValue(value) {
          var val = jElem.find('input[value="' + value + '"]');
          if (val.length) {
            jElem.find('input').prop('checked', false);
            jElem.find('input').prop('aria-checked', false);
            val.prop('checked', true);
            val.prop('aria-checked', true);
            active.html(val.next().html());
          }
        }

        function closeDropdown() {
          dropdown.removeClass('active');
          dropdown.css('height', '0px');
        }

        function openDropdown() {
          var height = labels.first().outerHeight() * 3;
          if (items.length < 3) {
            height = (items.length * labels.first().outerHeight());
            dropdown.css('overflow', 'hidden').css('width', '100%');
          }
            
          dropdown.css('height', height + 2 + 'px').addClass('active');
          dropdowninner.css('height', height + 2 + 'px');
        }

        function fireOnChange() {
          try {
            if (typeof scope.onChange === 'function') {
              scope.onChange()({
                label: active.html(),
                value: jElem.find('input:checked').val()
              });
            }
          } catch (e) {
            //TODO: handle error
          }
        }

        function bindDocMouseUp() {
          unbindDocMouseUp();
          $(document).bind('mouseup.' + id, function (e) {
            if (!dropdown.is(e.target) &&
              !dropdown.has(e.target).length &&
              !btn.is(e.target) &&
              !btn.has(e.target).length &&
              dropdown.hasClass('active')) {
              closeDropdown();
            }
          });
        }

        function unbindDocMouseUp() {
          $(document).unbind('mouseup.' + id);
        }

        function trackKeyInput() {

          dropdown.bind('keyup', function (e) {
            if (keyInputTimer) {
              clearTimeout(keyInputTimer);
            }

            if ((e.which <= 90 && e.which >= 48) || e.which === 32) {
              keyInput = keyInput + String.fromCharCode(e.which);
            }

            keyInputTimer = setTimeout(onKeyInputTimedout, 500);
          });
        }

        function onKeyInputTimedout() {
          if (keyInput) {
            var found = false;
            var curr = active.text().toLowerCase().indexOf(keyInput.toLowerCase()) === 0;
            var fEle = null;
            var lbl = labels.filter(function () {
              var f = this.textContent.toLowerCase().indexOf(keyInput.toLowerCase()) === 0;
              if (curr && fEle === null && f) {
                fEle = this;
              }

              if (curr && !found) {
                if (this.textContent.toLowerCase() === active.text().toLowerCase()) {
                  found = true;
                }

                return false;
              }

              return f;
            });

            if (curr && !lbl.length) {
              lbl = $(fEle);
            }

            if (lbl.length && !lbl.hasClass('psDisabled')) {
              jElem.find('input').prop('checked', false);
              jElem.find('input').prop('aria-checked', false);
              var radio = jElem.find('#' + lbl.attr('for'));
              radio.prop('checked', true);
              radio.prop('aria-checked', true);
              radio.focus();
			  var val = $ESAPI.encoder().encodeForHTML(lbl.html());				
              active.html($val);
              if (ngModel) {
                ngModel.$setViewValue(radio.val());
              }
            }

            keyInput = '';
          }
        }
      }
    };
  };

  uxdDdlDir.$inject = injectParams;

  mod.register('directive', 'uxdDdlDir', uxdDdlDir);

});
