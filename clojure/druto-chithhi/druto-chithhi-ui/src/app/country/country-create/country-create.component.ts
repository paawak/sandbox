import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';

import { Country } from '../country.model';
import { CountryService } from '../country.service';

@Component({
  selector: 'app-country-create',
  templateUrl: './country-create.component.html',
  styleUrls: ['./country-create.component.scss']
})
export class CountryCreateComponent implements OnInit {

  newCountry: Country;
  httpErrorMessage: string = null;

  constructor(private countryService: CountryService) { }

  ngOnInit() {
    this.newCountry = new Country();
    this.httpErrorMessage = null;
  }

  public createNewCountry() {
    console.log(this.newCountry);
    this.countryService.addNewCountry(this.newCountry).subscribe(
      (data) => {
        console.log('Data: ' + data);
      },
      (errorResponse: HttpErrorResponse) => {
        this.httpErrorMessage = errorResponse.message;
        console.error('Error creating new Country: ' + errorResponse);
      }
    );
  }

}
