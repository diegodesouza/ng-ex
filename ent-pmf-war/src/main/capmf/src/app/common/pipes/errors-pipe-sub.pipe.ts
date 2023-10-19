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

@Pipe({name: 'errorsappendparentname', pure: false})

export class FormErrorsPipeWithParent implements PipeTransform {
    transform(errors: {}, parentPrefix: string, fieldName: string): string {
        let fieldError = parentPrefix+fieldName;
        if(errors) {
            return errors[fieldError];
        } else {
            return null;
        }
    }
}
