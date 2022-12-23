import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isUserLoggedIn = false;
  userId: string | null = null;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.getUserObservable().subscribe(user => {
      if (user) {
        this.isUserLoggedIn = true;
        this.userId = user.id;
      } else {
        this.isUserLoggedIn = false;
        this.userId = null;
      }
    })
  }

  logout(): void {
    this.authService.signOut();
  }

}
