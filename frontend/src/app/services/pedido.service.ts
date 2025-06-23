import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class PedidoService {
  private apiUrl = 'http://localhost:8080/api/pedidos';

  constructor(private http: HttpClient) {}

  criarPedido(pedido: any): Observable<any> {
    return this.http.post(this.apiUrl, pedido, {
      headers: {
        'Content-Type': 'application/json'
      }
    });
  }
}