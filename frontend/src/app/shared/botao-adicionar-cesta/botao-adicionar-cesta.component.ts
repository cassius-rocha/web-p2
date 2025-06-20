import { Component, Input } from '@angular/core';
import { CestaService } from '../../services/cesta.service';
import { Vinil } from '../../models/vinil';

@Component({
  selector: 'app-botao-adicionar-cesta',
  standalone: true,
  templateUrl: './botao-adicionar-cesta.component.html',
  styleUrls: ['./botao-adicionar-cesta.component.css']
})
export class BotaoAdicionarCestaComponent {
  @Input() produto!: Vinil;
  foiAdicionado = false;

  constructor(private cestaService: CestaService) {}

  adicionar() {
    this.cestaService.adicionarProduto(this.produto);
    this.foiAdicionado = true;
    setTimeout(() => this.foiAdicionado = false, 1500); // reseta após 1,5s
  }
}