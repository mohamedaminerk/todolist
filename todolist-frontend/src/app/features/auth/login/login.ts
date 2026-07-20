import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';

import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterLink,
    ButtonModule,
    InputTextModule,
    PasswordModule,
    ToastModule,
  ],
  providers: [MessageService],
  templateUrl: './login.html',
  styleUrls: ['./login.css'],
})
export class LoginComponent {
  username = '';
  password = '';
  loading = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private messageService: MessageService,
  ) {}

  login(): void {
    if (!this.username || !this.password) {
      this.messageService.add({
        severity: 'warn',
        summary: 'Required',
        detail: 'Please fill in all fields',
      });
      return;
    }

    this.loading = true;

    this.authService
      .login({
        username: this.username,
        password: this.password,
      })
      .subscribe({
        next: () => {
          this.loading = false;
          this.messageService.add({
            severity: 'success',
            summary: 'Success',
            detail: 'Login successful!',
          });
          this.router.navigate(['/tasks']);
        },
        error: (err) => {
          this.loading = false;
          const errorMessage = this.extractErrorMessage(err);
          this.messageService.add({
            severity: 'error',
            summary: 'Login failed',
            detail: errorMessage,
            life: 5000,
          });
        },
      });
  }

  private extractErrorMessage(err: any): string {
    if (err.error?.detail) {
      return err.error.detail;
    }
    if (typeof err.error === 'string') {
      return err.error;
    }
    if (err.error?.message) {
      return err.error.message;
    }
    return 'Invalid credentials';
  }
}
