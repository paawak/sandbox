import { Component, OnInit } from '@angular/core';

import { Country } from '../country.model';

import { CountryService } from '../country.service';

@Component({
  selector: 'app-country-create',
  templateUrl: './country-create.component.html',
  styleUrls: ['./country-create.component.scss']
})
export class CountryCreateComponent implements OnInit {

  private newCountry: Country;

  constructor(private countryService: CountryService) { }

  ngOnInit() {
    this.newCountry = new Country();
  }

  public createNewCountry() {
    console.log(this.newCountry);
    this.countryService.addNewCountry(this.newCountry).subscribe(
      (data) => {
        console.log('Data: ' + data);
      },
      (error) => {
        console.error(error);
      }
    );
  }

}
