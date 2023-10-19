import { Pipe, PipeTransform } from '@angular/core';

/*
 * Raise the value exponentially
 * Takes an exponent argument that defaults to 1.
 * Usage:
 *   value | exponentialStrength:exponent
 * Example:
 *   {{ 2 |  exponentialStrength:10}}
 *   formats to: 1024
 */

@Pipe({name: 'errorspipe', pure: false})

export class FormErrorsPipe implements PipeTransform {
    transform(errors: {}, fieldPrefix: string, index: number): string {
        let fieldError = fieldPrefix+index;
        if(errors) {
            return errors[fieldError];
        }
        return null;
    }
}
