import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/common/navbar/navbar.component';
import { RegisterComponent } from './components/register/register.component';
import { FormsModule } from '@angular/forms';
import { NotFoundComponent } from './components/common/not-found/not-found.component';
import { LoginComponent } from './components/login/login.component';
import { VerificationSuccessfulComponent } from './components/verification-successful/verification-successful.component';
import { VerificationFailedComponent } from './components/verification-failed/verification-failed.component';
import { CentersListComponent } from './components/centers-list/centers-list.component';
import {MatSortModule} from '@angular/material/sort';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTableModule } from '@angular/material/table';
import { CenterDetailsComponent } from './components/center-details/center-details.component';
import { ToastrModule } from 'ngx-toastr';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    RegisterComponent,
    NotFoundComponent,
    LoginComponent,
    VerificationSuccessfulComponent,
    VerificationFailedComponent,
    CentersListComponent,
    CenterDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatSortModule,
    BrowserAnimationsModule,
    MatTableModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      timeOut: 2000,
      positionClass: 'toast-bottom-right',
      preventDuplicates: true,
    }),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
