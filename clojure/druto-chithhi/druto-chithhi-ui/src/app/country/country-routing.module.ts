import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CountryComponent } from './country.component';
import { CountryListComponent } from './country-list/country-list.component';
import { CountryDetailItemComponent } from './country-detail-item/country-detail-item.component';
import { CountryCreateComponent } from './country-create/country-create.component';

const routes: Routes = [
  {
    path: 'country',
    component: CountryComponent,
    children: [
      { path: 'new', component: CountryCreateComponent },
      { path: ':countryId', component: CountryDetailItemComponent },
      { path: '', component: CountryListComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class CountryRoutingModule { }
