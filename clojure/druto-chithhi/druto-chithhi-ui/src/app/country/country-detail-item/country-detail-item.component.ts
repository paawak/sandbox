import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Country } from '../country.model';
import { CountryService } from '../country.service';


@Component({
  selector: 'app-country-detail-item',
  templateUrl: './country-detail-item.component.html',
  styleUrls: ['./country-detail-item.component.scss']
})
export class CountryDetailItemComponent implements OnInit {

  country: Country;

  constructor(private route: ActivatedRoute, private countryService: CountryService) { }

  ngOnInit() {

    this.route.params.subscribe(
      (params) => {
        const countryId = params['countryId'];
        const countryObservable = this.countryService.getCountry(countryId);
        countryObservable.subscribe(
          (data: Country) => {
            this.country = data;
            console.log('country: ' + this.country);
          },
          (error) => {
            console.log(error);
          },
          () => {
            console.log('Completed fetching country data');
          }
        );
      }
    );
  }

}
