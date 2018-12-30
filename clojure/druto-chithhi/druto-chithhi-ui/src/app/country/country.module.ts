import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CountryService } from './country.service';

import { CountryComponent } from './country.component';
import { CountryListComponent } from './country-list/country-list.component';
import { CountryListItemComponent } from './country-list-item/country-list-item.component';


@NgModule({
  declarations: [
    CountryComponent,
    CountryListComponent,
    CountryListItemComponent
  ],
  imports: [
    CommonModule
  ],
  providers: [
    CountryService
  ]
})
export class CountryModule { }
