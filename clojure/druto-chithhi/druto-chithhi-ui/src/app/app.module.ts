import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HeaderComponent } from './common/header/header.component';
import { CountryModule } from './country/country.module';
import { StateComponent } from './state/state.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    StateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CountryModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
