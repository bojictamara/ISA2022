import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CenterDetailsComponent } from './components/center-details/center-details.component';
import { CentersListComponent } from './components/centers-list/centers-list.component';
import { NotFoundComponent } from './components/common/not-found/not-found.component';
import { ComplaintAnswerComponent } from './components/complaint-answer/complaint-answer.component';
import { ComplaintWritingComponent } from './components/complaint-writing/complaint-writing.component';
import { ComplaintsAdminComponent } from './components/complaints-admin/complaints-admin.component';
import { IndexComponent } from './components/index/index.component';
import { LoginComponent } from './components/login/login.component';
import { MyComplaintsComponent } from './components/my-complaints/my-complaints.component';
import { QuestionnaireComponent } from './components/questionnaire/questionnaire.component';
import { RegisterComponent } from './components/register/register.component';
import { ReservedAppointmentsComponent } from './components/reserved-appointments/reserved-appointments.component';
import { VerificationFailedComponent } from './components/verification-failed/verification-failed.component';
import { VerificationSuccessfulComponent } from './components/verification-successful/verification-successful.component';
import { AdminGuard } from './guards/admin.guard';
import { AuthGuardGuard } from './guards/auth-guard.guard';

const routes: Routes = [
  {
    path: 'my-appointments',
    component: ReservedAppointmentsComponent,
    pathMatch: 'full',
    canActivate: [AuthGuardGuard]
  },
  {
    path: 'admin-complaints',
    component: ComplaintsAdminComponent,
    pathMatch: 'full',
    canActivate: [AuthGuardGuard, AdminGuard] // Admin guard
  },
  {
    path: 'not-answered-complaints',
    component: ComplaintAnswerComponent,
    pathMatch: 'full',
    canActivate: [AuthGuardGuard, AdminGuard] // Admin guard
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
  },
  {
    path: 'register',
    component: RegisterComponent,
    pathMatch: 'full',
  },
  {
    path: 'login',
    component: LoginComponent,
    pathMatch: 'full',
  },
  {
    path: 'verification-successful',
    component: VerificationSuccessfulComponent,
    pathMatch: 'full',
  },
  {
    path: 'verification-failed',
    component: VerificationFailedComponent,
    pathMatch: 'full',
  },
  {
    path: '',
    component: IndexComponent,
    pathMatch: 'full',
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
