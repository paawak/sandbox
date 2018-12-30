import { Component, OnInit } from '@angular/core';

import { CountryService } from '../country.service';

@Component({
  selector: 'app-country-list',
  templateUrl: './country-list.component.html',
  styleUrls: ['./country-list.component.scss']
})
export class CountryListComponent implements OnInit {

  countries: any[];

  constructor(private countryService: CountryService) { }

  ngOnInit() {
    const countriesObservable = this.countryService.getCountries();
    countriesObservable.subscribe(
      (data) => {
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
