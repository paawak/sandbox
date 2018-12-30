import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

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

      public getCountries(): Observable<any> {
          const countriesObservable = new Observable((observer) => {

            setTimeout(() => {
                observer.next(this.countries);
            }, 1000);

            setTimeout(() => {
                observer.complete();
            }, 2000);

          });
          return countriesObservable;
      }

}
