import { Injectable } from '@angular/core';

@Injectable()
export class CountryService {

    private countries: any[] = [
        {
          id: 1,
          code: 'IN',
          name: 'India'
        },
        {
          id: 2,
          code: 'US',
          name: 'United States Of America'
        },
        {
          id: 3,
          code: 'BN',
          name: 'Bangladesh'
        }
      ];

      public getCountries(): any[] {
          return this.countries;
      }

}
