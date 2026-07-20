import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { TextareaModule } from 'primeng/textarea';
import { CheckboxModule } from 'primeng/checkbox';
import { DialogModule } from 'primeng/dialog';
import { ToastModule } from 'primeng/toast';
import { SkeletonModule } from 'primeng/skeleton';
import { BadgeModule } from 'primeng/badge';
import { TagModule } from 'primeng/tag';
import { DividerModule } from 'primeng/divider';
import { MessageService } from 'primeng/api';
import { TaskService } from '../../../core/services/task.service';
import { AuthService } from '../../../core/services/auth.service';
import { Task } from '../../../core/models/task.model';

@Component({
  selector: 'app-todos',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ButtonModule,
    InputTextModule,
    TextareaModule,
    CheckboxModule,
    DialogModule,
    ToastModule,
    SkeletonModule,
    BadgeModule,
    TagModule,
    DividerModule,
  ],
  providers: [MessageService],
  templateUrl: './todos.html',
  styleUrl: './todos.css',
})
export class TodosComponent implements OnInit {
  todos: Task[] = [];
  loading = true;
  saving = false;
  dialogVisible = false;
  deleteDialogVisible = false;
  todoToDelete: Task | null = null;
  searchQuery = '';
  filterStatus: 'all' | 'pending' | 'completed' = 'all';

  form: { title: string; description: string; startDate: string; endDate: string } = {
    title: '',
    description: '',
    startDate: '',
    endDate: '',
  };
  editingTodo: Task | null = null;

  today = new Date();

  constructor(
    private taskService: TaskService,
    public authService: AuthService,
    private router: Router,
    private messageService: MessageService,
  ) {}

  get currentUser() {
    return this.authService.currentUser;
  }

  get userInitials(): string {
    const name = this.currentUser?.username || 'U';
    return name.slice(0, 2).toUpperCase();
  }

  get filteredTodos(): Task[] {
    return this.todos.filter((t) => {
      const matchesSearch =
        t.title.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
        (t.description || '').toLowerCase().includes(this.searchQuery.toLowerCase());
      const matchesFilter =
        this.filterStatus === 'all' ||
        (this.filterStatus === 'completed' && t.status) ||
        (this.filterStatus === 'pending' && !t.status);
      return matchesSearch && matchesFilter;
    });
  }

  get pendingCount(): number {
    return this.todos.filter((t) => !t.status).length;
  }
  get completedCount(): number {
    return this.todos.filter((t) => t.status).length;
  }
  get progressPercent(): number {
    if (this.todos.length === 0) return 0;
    return Math.round((this.completedCount / this.todos.length) * 100);
  }

  ngOnInit(): void {
    this.loadTodos();
  }

  private getErrorMessage(err: any): string {
    if (!err) return 'Unknown server error';

    return (
      err.error?.message ||
      err.error?.detail ||
      err.error?.error ||
      err.message ||
      (typeof err.error === 'string' ? err.error : 'Server exception occurred')
    );
  }

  loadTodos(): void {
    this.loading = true;
    this.taskService.getAll().subscribe({
      next: (todos) => {
        this.todos = todos;
        this.loading = false;
      },
      error: (err) => {
        this.loading = false;
        this.messageService.add({
          severity: 'error',
          summary: 'Error Loading Tasks',
          detail: this.getErrorMessage(err),
        });
      },
    });
  }

  openCreateDialog(): void {
    this.editingTodo = null;
    this.form = { title: '', description: '', startDate: '', endDate: '' };
    this.dialogVisible = true;
  }

  openEditDialog(todo: Task): void {
    this.editingTodo = todo;
    this.form = {
      title: todo.title,
      description: todo.description || '',
      startDate: todo.startDate,
      endDate: todo.endDate,
    };
    this.dialogVisible = true;
  }

  saveTask(): void {
    if (
      !this.form.title?.trim() ||
      !this.form.description?.trim() ||
      !this.form.startDate?.trim() ||
      !this.form.endDate?.trim()
    ) {
      this.messageService.add({
        severity: 'warn',
        summary: 'Incomplete Form',
        detail: 'Please fill all fields.',
      });
      return;
    }

    this.saving = true;

    if (this.editingTodo) {
      this.taskService.update(this.editingTodo.taskId, this.form).subscribe({
        next: (updated) => {
          const idx = this.todos.findIndex((t) => t.taskId === updated.taskId);
          if (idx !== -1) this.todos[idx] = updated;
          this.dialogVisible = false;
          this.saving = false;
          this.messageService.add({
            severity: 'success',
            summary: 'Saved',
            detail: 'Task updated successfully',
          });
        },
        error: (err) => {
          this.saving = false;
          this.messageService.add({
            severity: 'error',
            summary: 'Update Failed',
            detail: this.getErrorMessage(err),
          });
        },
      });
    } else {
      this.taskService.create({ ...this.form }).subscribe({
        next: (created) => {
          this.todos.unshift(created);
          this.dialogVisible = false;
          this.saving = false;
          this.messageService.add({
            severity: 'success',
            summary: 'Created',
            detail: 'Task added successfully',
          });
        },
        error: (err) => {
          this.saving = false;
          this.messageService.add({
            severity: 'error',
            summary: 'Creation Failed',
            detail: this.getErrorMessage(err),
          });
        },
      });
    }
  }

  toggleComplete(todo: Task): void {
    this.taskService.toggleComplete(todo).subscribe({
      next: (updated) => {
        const idx = this.todos.findIndex((t) => t.taskId === updated.taskId);
        if (idx !== -1) {
          this.todos[idx] = updated;
        }

        this.messageService.add({
          severity: 'success',
          summary: 'Success',
          detail: 'Task status updated!',
        });
      },
      error: (err) => {
        this.messageService.add({
          severity: 'error',
          summary: 'Status Change Failed',
          detail: this.getErrorMessage(err),
        });
      },
    });
  }

  confirmDelete(todo: Task): void {
    this.todoToDelete = todo;
    this.deleteDialogVisible = true;
  }

  executeDelete(): void {
    if (this.todoToDelete) {
      this.deleteTodo(this.todoToDelete);
    }
  }

  deleteTodo(todo: Task): void {
    this.taskService.delete(todo.taskId).subscribe({
      next: () => {
        this.todos = this.todos.filter((t) => t.taskId !== todo.taskId);
        this.deleteDialogVisible = false;
        this.todoToDelete = null;
        this.messageService.add({
          severity: 'success',
          summary: 'Deleted',
          detail: 'Task removed',
        });
      },
      error: (err) => {
        this.messageService.add({
          severity: 'error',
          summary: 'Delete Failed',
          detail: this.getErrorMessage(err),
        });
      },
    });
  }

  trackById(_: number, todo: Task): number {
    return todo.taskId;
  }

  logout(): void {
    this.messageService.add({
      severity: 'info',
      summary: 'Logged out',
      detail: 'You have been logged out successfully.',
      life: 3010,
    });

    setTimeout(() => {
      this.authService.logout();
      this.router.navigate(['/']);
    }, 1000);
  }
}
