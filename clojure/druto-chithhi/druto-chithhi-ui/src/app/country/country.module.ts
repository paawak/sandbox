import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

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
  ]
})
export class CountryModule { }
