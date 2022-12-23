import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NotFoundComponent } from './components/common/not-found/not-found.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { VerificationFailedComponent } from './components/verification-failed/verification-failed.component';
import { VerificationSuccessfulComponent } from './components/verification-successful/verification-successful.component';
import { AuthGuardGuard } from './guards/auth-guard.guard';

const routes: Routes = [
  {
    path: 'register',
    component: RegisterComponent,
    pathMatch: 'full',
    // canActivate: [AuthGuardGuard]
  },
  {
    path: 'login',
    component: LoginComponent,
    pathMatch: 'full',
    // canActivate: [AuthGuardGuard]
  },
  {
    path: 'verification-successful',
    component: VerificationSuccessfulComponent,
    pathMatch: 'full',
    // canActivate: [AuthGuardGuard]
  },
  {
    path: 'verification-failed',
    component: VerificationFailedComponent,
    pathMatch: 'full',
    // canActivate: [AuthGuardGuard]
  },
  {
    path: '**',
    component: NotFoundComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
