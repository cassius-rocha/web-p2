import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { BotaoAdicionarCestaComponent } from '../../shared/botao-adicionar-cesta/botao-adicionar-cesta.component';

@Component({
  selector: 'app-vitrine',
  standalone: true,
  imports: [CommonModule, RouterModule, BotaoAdicionarCestaComponent],
  templateUrl: './vitrine.html',
  styleUrls: ['./vitrine.css']
})
export class VitrineComponent implements OnInit {
  vinis: any[] = [];

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    const usuarioLogado = localStorage.getItem('usuarioLogado');
    if (usuarioLogado) {
      const cliente = JSON.parse(usuarioLogado);
      console.log('Usuário logado:', cliente.nome); // ou usar como quiser
    }

    this.http.get<any[]>('http://localhost:8080/vinil')
      .subscribe(data => {
        this.vinis = data.slice(0, 9);
      });
  }

  irParaDetalhes(id: number) {
    this.router.navigate(['/detalhe-do-produto', id]);
  }
}