import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { Country } from './country.model';

@Injectable()
export class CountryService {

    private countries: Country[] = [
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

      public getCountries(): Observable<Country[]> {
          const countriesObservable = new Observable<Country[]>((observer) => {

            setTimeout(() => {
                observer.next(this.countries);
            }, 1000);

            setTimeout(() => {
                observer.complete();
            }, 2000);

          });
          return countriesObservable;
      }

      public getCountry(countryId: number): Observable<Country> {
        const countryObservable = new Observable<Country>((observer) => {

          setTimeout(() => {
              const country = {
                id: 1,
                code: 'IN',
                name: 'India'
              };
              observer.next(country);
          }, 100);

          setTimeout(() => {
              observer.complete();
          }, 150);

        });
        return countryObservable;
    }

}
