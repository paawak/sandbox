import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';

import { Country } from './country.model';

@Injectable()
export class CountryService {

      private static readonly COUNTRY_URL_PREFIX = 'http://192.168.1.4:8080/country';

      constructor(private httpClient: HttpClient) {}

      public getCountries(): Observable<Country[]> {
        return <Observable<Country[]>>this.httpClient.get(CountryService.COUNTRY_URL_PREFIX);
      }

      public getCountry(countryId: number): Observable<Country> {
        const countryObservable = new Observable<Country>((observer) => {

          setTimeout(() => {
              const country = {
                id: 1,
                shortname: 'IN',
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

    public addNewCountry(country: Country): Observable<any> {
      const headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
      const formBody = 'name=' + country.name + '&shortname=' + country.shortname;
      return this.httpClient.post(CountryService.COUNTRY_URL_PREFIX, formBody, { headers });
    }

}
