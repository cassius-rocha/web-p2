import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { CestaService } from '../../services/cesta.service';
import { PedidoService } from '../../services/pedido.service';

@Component({
  selector: 'app-cesta',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cesta.html',
  styleUrls: ['./cesta.css']
})
export class CestaComponent {
  constructor(
    public cestaService: CestaService,
    private pedidoService: PedidoService,
    private router: Router
  ) { }

  limparCesta(): void {
    this.cestaService.limpar();
  }

  calcularTotal(): number {
    return this.cestaService.itens.reduce((total, item) =>
      total + item.produto.preco * item.quantidade, 0);
  }

  gravarPedido(): void {
    const usuarioLogado = localStorage.getItem('usuarioLogado');

    if (!usuarioLogado) {
      // Redireciona para login com a URL atual como estado
      this.router.navigate(['/cadastro-login'], { 
        state: { 
          redirectUrl: '/cesta',
          message: 'Faça login para finalizar seu pedido' 
        } 
      });
      return;
    }

    const usuario = JSON.parse(usuarioLogado);

    const pedidoDTO = {
      clienteId: usuario.id,
      itens: this.cestaService.itens.map(item => ({
        produtoId: item.produto.id,
        quantidade: item.quantidade,
        precoUnitario: item.produto.preco
      }))
    };

    this.pedidoService.criarPedido(pedidoDTO).subscribe({
      next: (res) => {
        this.cestaService.limpar();
        this.router.navigate(['/pedido'], { state: { pedido: res } });
      },
      error: (err) => {
        console.error('Erro detalhado:', err);
        alert(`Erro: ${err.error?.message || 'Erro ao gravar pedido'}`);
      }
    });
  }
}