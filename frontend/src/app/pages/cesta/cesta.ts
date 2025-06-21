import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CestaService } from '../../services/cesta.service';

@Component({
  selector: 'app-cesta',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cesta.html',
  styleUrls: ['./cesta.css']
})

export class CestaComponent {
  constructor(public cestaService: CestaService) {}

  limparCesta() {
    this.cestaService.limpar();
  }

  calcularTotal(): number {
    return this.cestaService.itens.reduce((total, item) =>
      total + item.produto.preco * item.quantidade, 0);
  }
}
