import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import AuthenticatedUser from '../payload/responses/authenticated-user';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private user$: BehaviorSubject<any> = new BehaviorSubject(null);

  constructor(private http: HttpClient, private router: Router) { }

  login(username: string, password: string): Observable<AuthenticatedUser> {
    return this.http.post<AuthenticatedUser>(environment.backendBaseUrl + 'login', {
      username,
      password
    }, httpOptions);
  }

  register(name: string, lastName:string, username: string, email: string, password: string,
    jmbg:string, prebivaliste:string, grad:string, drzava:string, brojTelefona:string, pol:string, zanimanje:string, info:string): Observable<any> {
    return this.http.post(environment.backendBaseUrl + 'register', {
      name,
      lastName,
      username,
      email,
      password,
      jmbg,
      address: prebivaliste,
      city: grad,
      state: drzava,
      phone: brojTelefona,
      gender: pol,
      profession: zanimanje,
      professionInfo: info
    },
    {
      responseType: 'text'
    });
  }

  private getUserFromStorage(): any {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }

    return null;
  }

  public autoLogin(): void {
      const userFromStorage = this.getUserFromStorage();
      if (userFromStorage) {
        this.user$.next(userFromStorage);
      }
  }

  signOut(): void {
    window.sessionStorage.clear();
    this.user$.next(null);

    this.router.navigate(["/"]);
  }

  public getToken(): string | null {
    return this.user$.value?.token;
  }

  public saveUser(user: AuthenticatedUser): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));

    this.user$.next(user);
  }

  public getUserObservable(): BehaviorSubject<AuthenticatedUser> {
    return this.user$;
  }

}
