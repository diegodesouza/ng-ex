import {Pipe, PipeTransform} from '@angular/core';

/*
 * Sets default options for the
 * auto dropdown
 * Takes in a string for a placeholder.
 *
 * Usage:
 *   {} | optionsPipe: 'Placeholder'
 * Example:
 *   [options]='options | optionsPipe: 'Suffix''
 */

@Pipe({ name: 'optionsPipe' })

export class OptionsPipe implements PipeTransform {
  transform(options: any, placeholder: string, data: any): {} {
    let defaultOptions: Select2Options;
    defaultOptions = {};

    defaultOptions['width'] = '100%';
    defaultOptions['containerCssClass'] = 'pmf-auto-dropdown';
    defaultOptions['placeholder'] = placeholder;

    if(data && data.length <= 5) {
      defaultOptions['dropdownCssClass'] = 'no-search';
    }

    return defaultOptions;
  }
}
