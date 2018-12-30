import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HeaderComponent } from './common/header/header.component';
import { CountryComponent } from './country/country.component';
import { StateComponent } from './state/state.component';
import { CountryListComponent } from './country/country-list/country-list.component';
import { CountryListItemComponent } from './country/country-list-item/country-list-item.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    CountryComponent,
    StateComponent,
    CountryListComponent,
    CountryListItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
