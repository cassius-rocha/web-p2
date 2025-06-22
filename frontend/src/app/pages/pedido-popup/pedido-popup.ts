import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-pedido-popup',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pedido-popup.html',
  styleUrls: ['./pedido-popup.css']
})
export class PedidoPopupComponent {
  @Input() pedido: any;
  @Output() fechar = new EventEmitter<void>();

  fecharPopup() {
    this.fechar.emit();
  }
}