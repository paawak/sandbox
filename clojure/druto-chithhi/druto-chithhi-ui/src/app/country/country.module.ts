import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CountryService } from './country.service';

import { CountryRoutingModule } from './country-routing.module';

import { CountryComponent } from './country.component';
import { CountryListComponent } from './country-list/country-list.component';
import { CountryListItemComponent } from './country-list-item/country-list-item.component';
import { CountryDetailItemComponent } from './country-detail-item/country-detail-item.component';


@NgModule({
  declarations: [
    CountryComponent,
    CountryListComponent,
    CountryListItemComponent,
    CountryDetailItemComponent
  ],
  imports: [
    CommonModule,
    CountryRoutingModule
  ],
  providers: [
    CountryService
  ]
})
export class CountryModule { }
