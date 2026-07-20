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
  selector: 'app-register',
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
  templateUrl: 'register.html',
  styleUrl: 'register.css',
})
export class RegisterComponent {
  name = '';
  surname = '';
  username = '';
  password = '';
  loading = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private messageService: MessageService,
  ) {}

  register(): void {
    if (!this.name || !this.surname || !this.username || !this.password) {
      this.messageService.add({
        severity: 'warn',
        summary: 'Required',
        detail: 'Please fill in all fields',
      });
      return;
    }
    this.loading = true;
    this.authService
      .register({
        name: this.name,
        surname: this.surname,
        username: this.username,
        password: this.password,
      })
      .subscribe({
        next: () => {
          this.loading = false;
          this.messageService.add({
            severity: 'success',
            summary: 'Success',
            detail: 'Account created! You can now log in.',
          });
        },
        error: (err) => {
          this.loading = false;
          const errorMessage = this.extractErrorMessage(err);
          this.messageService.add({
            severity: 'error',
            summary: 'Registration failed',
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
    return 'Something went wrong. Please try again.';
  }
}
