import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StateComponent } from './state/state.component';
import { WelcomePageComponent } from './welcome-page/welcome-page.component';

const routes: Routes = [
  // {path: '', redirectTo: '/welcome', pathMatch: 'full'},
  {path: 'state', component: StateComponent},
  {path: '', component: WelcomePageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
