import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CenterDetailsComponent } from './components/center-details/center-details.component';
import { CentersListComponent } from './components/centers-list/centers-list.component';
import { NotFoundComponent } from './components/common/not-found/not-found.component';
import { ComplaintAnswerComponent } from './components/complaint-answer/complaint-answer.component';
import { ComplaintWritingComponent } from './components/complaint-writing/complaint-writing.component';
import { ComplaintsAdminComponent } from './components/complaints-admin/complaints-admin.component';
import { LoginComponent } from './components/login/login.component';
import { MyComplaintsComponent } from './components/my-complaints/my-complaints.component';
import { QuestionnaireComponent } from './components/questionnaire/questionnaire.component';
import { RegisterComponent } from './components/register/register.component';
import { VerificationFailedComponent } from './components/verification-failed/verification-failed.component';
import { VerificationSuccessfulComponent } from './components/verification-successful/verification-successful.component';
import { AuthGuardGuard } from './guards/auth-guard.guard';

const routes: Routes = [
  {
    path: 'admin-complaints',
    component: ComplaintsAdminComponent,
    pathMatch: 'full',
    canActivate: [AuthGuardGuard] // Admin guard
  },
  {
    path: 'not-answered-complaints',
    component: ComplaintAnswerComponent,
    pathMatch: 'full',
    canActivate: [AuthGuardGuard] // Admin guard
  },
  {
    path: 'my-complaints',
    component: MyComplaintsComponent,
    pathMatch: 'full',
    canActivate: [AuthGuardGuard]
  },
  {
    path: 'complaints/new',
    component: ComplaintWritingComponent,
    pathMatch: 'full',
    canActivate: [AuthGuardGuard]
  },
  {
    path: 'questionnaire',
    component: QuestionnaireComponent,
    pathMatch: 'full',
    canActivate: [AuthGuardGuard]
  },
  {
    path: 'centers/:id',
    component: CenterDetailsComponent,
    pathMatch: 'full',
    canActivate: [AuthGuardGuard]
  },
  {
    path: 'centers',
    component: CentersListComponent,
    pathMatch: 'full',
    // canActivate: [AuthGuardGuard]
  },
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
