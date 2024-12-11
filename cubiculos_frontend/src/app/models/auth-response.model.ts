export interface Estudiante {
    name: string,
    email: string,
    phone: string
    idEstado:number,
    codigo:number
  }
  
  export interface AuthResponse {
    token: string;
    estudiante: Estudiante;
    role: string;
  }