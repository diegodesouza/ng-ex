import {Pipe, PipeTransform} from '@angular/core';

/*
 * Transforms 0000000000 to (000)000-00000
 *
 * Takes in a string.
 *
 * Usage:
 *   model.string | maskPhonePipe
 */

@Pipe({ name: 'maskPhonePipe' })

export class MaskPhonePipe implements PipeTransform {
  transform(phone: string): string {
    const PHONE_MASK = /(\d{3})(\d{3})(\d{4})/;
    const match = phone.match(PHONE_MASK);
    const newPhone = '(' + match[1] + ') ' + match[2] + '-' + match[3];

    return newPhone;
  }
}
