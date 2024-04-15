import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'phoneNumber',
  standalone: true
})
export class PhoneNumberPipe implements PipeTransform {
  transform(phoneValue: number | string, country: string = 'US'): string {
    let phoneString = phoneValue + '';
    // Your formatting logic here. This is a simplistic example:
    switch (country) {
      case 'US':
        // Formats to (123) 456-7890
        return phoneString.replace(/(\d{3})(\d{3})(\d{4})/, '($1) $2-$3');
      // You can add more case statements for other countries.
      default:
        return phoneString;
    }
  }
}
