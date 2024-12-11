import { Component } from '@angular/core';
import { Login } from '../models/login.model';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-pagina-inicial',
  templateUrl: './pagina-inicial.component.html',
  styleUrl: './pagina-inicial.component.css',
})
export class PaginaInicialComponent {

  credentials: Login = { username: '', password: '' };
  errorMessage = '';

  constructor(private readonly authService: AuthService, private readonly router: Router) {}

  login(): void {
    this.authService.login(this.credentials).subscribe({
      next: () => this.router.navigate(['/buscar']),
      error: err => this.errorMessage = 'Login failed'
    });
  }

  register(): void {
    this.router.navigate(['/register']); 
  }

}
