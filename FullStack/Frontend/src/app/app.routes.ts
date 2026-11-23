import { Routes } from '@angular/router';
import { Dashboard } from './dashboard/dashboard';
import { Depenses } from './depenses/depenses';
import { Categories } from './categories/categories';

export const routes: Routes = [
  { path: 'dashboard', component: Dashboard },
  { path: 'depenses', component: Depenses },
  { path: 'categories', component: Categories },
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: '**', redirectTo: 'dashboard' },
];
