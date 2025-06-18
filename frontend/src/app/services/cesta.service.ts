// src/app/services/cesta.service.ts
import { Injectable } from '@angular/core';
import { Vinil } from '../models/vinil';

export interface ItemCesta {
  produto: Vinil;
  quantidade: number;
}

@Injectable({ providedIn: 'root' })
export class CestaService {
  itens: ItemCesta[] = [];

  adicionarProduto(produto: Vinil) {
    const existente = this.itens.find(p => p.produto.id === produto.id);
    if (existente) {
      existente.quantidade++;
    } else {
      this.itens.push({ produto, quantidade: 1 });
    }
  }

  limpar() {
    this.itens = [];
  }
}
