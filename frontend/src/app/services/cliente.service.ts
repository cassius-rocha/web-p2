import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ClienteService {
  private apiUrl = 'http://localhost:8080/api/clientes';

  constructor(private http: HttpClient) { }

  criarCliente(cliente: any) {
    return this.http.post(this.apiUrl, cliente).pipe(
      catchError((err) => throwError(() => err))
    );
  }

  loginCliente(loginData: { email: string; senha: string }) {
    return this.http.post<any>('http://localhost:8080/api/clientes/login', loginData);
  }

  redefinirSenha(emailData: { email: string }) {
    return this.http.post('http://localhost:8080/api/clientes/redefinir-senha', emailData, {
      responseType: 'text' 
    });
  }


}