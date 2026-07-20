import { Routes } from '@angular/router';
import { LandingComponent } from './features/landing/landing'; // Chemin à adapter selon où tu as généré le composant
import { LoginComponent } from './features/auth/login/login';
import { RegisterComponent } from './features/auth/register/register';
import { TodosComponent } from './features/todos/CRUD/todos';

export const routes: Routes = [
  // Page d'accueil par défaut (Landing Page)
  { path: '', component: LandingComponent },

  // Routes d'authentification et de l'application
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'tasks', component: TodosComponent },

  // Redirection automatique si la route saisie n'existe pas
  { path: '**', redirectTo: '' },
];
