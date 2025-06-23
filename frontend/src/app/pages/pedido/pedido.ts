// src/app/pages/pedido/pedido.ts
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pedido',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pedido.html',
  styleUrls: ['./pedido.css']
})
export class PedidoComponent {
  pedido: any;

  constructor(private router: Router) {
    const nav = this.router.getCurrentNavigation();
    this.pedido = nav?.extras?.state?.['pedido'];
    console.log('Pedido recebido:', this.pedido);
  }

  irParaPagamento() {
    // Apenas placeholder por enquanto
    alert('Redirecionar para pagamento futuramente.');
  }
}
