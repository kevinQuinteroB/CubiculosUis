import { Estudiante } from '../models/auth-response.model';

const apiUrl = 'http://localhost:8080';

export const environment = {
  production: true,
  apiUrl: apiUrl, 
  authApiUrl: `${apiUrl}/api/v1/auth`,
  cubiculosApiUrl: `${apiUrl}/service`,
  currtentUser: null as Estudiante | null, 
  role: null as string | null,
};
