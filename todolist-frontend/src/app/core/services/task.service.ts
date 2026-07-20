import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Task, CreateTaskDto, UpdateTaskDto } from '../models/task.model';
import { environment } from '../../../enviroments/enviroment';

@Injectable({ providedIn: 'root' })
export class TaskService {
  private apiUrl = `${environment.apiUrl}/tasks`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Task[]> {
    return this.http.get<Task[]>(this.apiUrl);
  }

  create(dto: CreateTaskDto): Observable<Task> {
    return this.http.post<Task>(this.apiUrl, dto);
  }

  update(taskId: number, dto: UpdateTaskDto): Observable<Task> {
    return this.http.patch<Task>(`${this.apiUrl}/${taskId}`, dto);
  }

  toggleComplete(task: Task): Observable<Task> {
    // FIX: Send the title and other fields so the backend DTO doesn't receive null!
    const payload = {
      title: task.title || 'Untitled',
      description: task.description || '',
      startDate: task.startDate || '',
      endDate: task.endDate || '',
      status: !task.status,
    };

    return this.http.patch<Task>(`${this.apiUrl}/${task.taskId}`, payload);
  }

  delete(taskId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${taskId}`);
  }
}
