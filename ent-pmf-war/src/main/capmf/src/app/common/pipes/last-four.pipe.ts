/**
 * Created by AF39842 on 6/08/17.
 */
import {Pipe, PipeTransform} from '@angular/core';

/*
 * Replaces all digits but the last 4
 * with a pound (e.g. #).
 * Takes a string argument.
 * Usage:
 *   value | lastfourPipe
 * Example:
 *   {{ payLoadService.organization.taxId |  lastfourPipe }}
 *   formats to: #########1234
 */

@Pipe({ name: 'lastFourPipe' })

export class LastFourPipe implements PipeTransform {
  transform(input: string): string {
    if(input) {
      return input.replace(/\d(?=\d{4})/g, '#');
    }
  }
}
