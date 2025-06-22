import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CestaService } from '../../services/cesta.service';
import { PedidoService } from '../../services/pedido.service';
import { PedidoPopupComponent } from '../../pages/pedido-popup/pedido-popup';

@Component({
  selector: 'app-cesta',
  standalone: true,
  imports: [CommonModule, PedidoPopupComponent],
  templateUrl: './cesta.html',
  styleUrls: ['./cesta.css']
})
export class CestaComponent {
  mostrarPopup = false;
  pedidoGerado: any = null;

  constructor(
    public cestaService: CestaService,
    private pedidoService: PedidoService
  ) {}

  limparCesta() {
    this.cestaService.limpar();
  }

  calcularTotal(): number {
    return this.cestaService.itens.reduce((total, item) =>
      total + item.produto.preco * item.quantidade, 0);
  }

  gravarPedido() {
    const pedido = {
      cliente: { id: 1 }, // id fixo ou recuperado do login/autenticação
      itens: this.cestaService.itens.map(item => ({
        produto: item.produto,
        quantidade: item.quantidade,
        precoUnitario: item.produto.preco
      }))
    };

    this.pedidoService.criarPedido(pedido).subscribe({
      next: (res) => {
        this.pedidoGerado = res;
        this.mostrarPopup = true;
        this.limparCesta();
      },
      error: (err) => console.error('Erro ao criar pedido:', err)
    });
  }

  fecharPopup() {
    this.mostrarPopup = false;
    this.pedidoGerado = null;
  }
}