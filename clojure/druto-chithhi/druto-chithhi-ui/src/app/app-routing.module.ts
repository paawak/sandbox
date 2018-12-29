import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CountryComponent } from './country/country.component';
import { StateComponent } from './state/state.component';

const routes: Routes = [
  {path: '', component: CountryComponent},
  {path: 'country', component: CountryComponent},
  {path: 'state', component: StateComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
