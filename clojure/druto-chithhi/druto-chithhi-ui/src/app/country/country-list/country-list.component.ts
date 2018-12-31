import { Component, OnInit } from '@angular/core';

import { Country } from '../country.model';
import { CountryService } from '../country.service';

@Component({
  selector: 'app-country-list',
  templateUrl: './country-list.component.html',
  styleUrls: ['./country-list.component.scss']
})
export class CountryListComponent implements OnInit {

  countries: Country[] = [];

  constructor(private countryService: CountryService) { }

  ngOnInit() {
    const countriesObservable = this.countryService.getCountries();
    countriesObservable.subscribe(
      (data: Country[]) => {
        this.countries = data;
      },
      (error) => {
        console.log(error);
      },
      () => {
        console.log('Completed fetching countries data');
      }
    );
  }

}
