
export interface CreateTaskDto {
  title: string;
  description: string;
  startDate: string;
  endDate: string;
}

export interface UpdateTaskDto {
  title?: string;
  description?: string;
  startDate?: string;
  endDate?: string;
  status?: boolean;
}

export interface CreateTaskResponse {
  taskId: number;
  title: string;
  description: string;
  startDate: string;
  endDate: string;
  status: boolean;
  userId: number;
}

export interface UpdateTaskResponse {
  taskId: number;
  title: string;
  description: string;
  startDate: string;
  endDate: string;
  status: boolean;
  userId: number;
}

export interface Task {
  taskId: number;
  title: string;
  description: string;
  startDate: string;
  endDate: string;
  status: boolean;
}
