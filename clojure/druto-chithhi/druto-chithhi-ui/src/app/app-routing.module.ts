import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CountryComponent } from './country/country.component';
import { StateComponent } from './state/state.component';

const routes: Routes = [
  {path: '', redirectTo: '/country', pathMatch: 'full'},
  {path: 'state', component: StateComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
