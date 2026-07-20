export interface LoginRequest {
  username: string;
  password: string;
}

export interface RegisterRequest {
  username: string;
  password: string;
  name: string;
  surname: string;
}

export interface AuthResponse {
  token: string;
}

export interface User {
  id: number;
  username: string;
  name: string;
  surname: string;
  role: string;
}
