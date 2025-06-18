import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { BotaoAdicionarCestaComponent } from '../../shared/botao-adicionar-cesta/botao-adicionar-cesta.component';

@Component({
  selector: 'app-busca',
  standalone: true,
  imports: [CommonModule, RouterModule, BotaoAdicionarCestaComponent],
  templateUrl: './busca.html',
  styleUrls: ['./busca.css']
})
export class BuscaComponent implements OnInit {
  termo: string = '';
  resultados: any[] = [];

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.termo = params['termo'] || '';
      if (this.termo) {
        this.buscarVinis(this.termo);
      }
    });
  }

  buscarVinis(termo: string) {
    this.http.get<any[]>(`http://localhost:8080/vinil/buscar?termo=${termo}`)
      .subscribe(data => {
        this.resultados = data;
      }, err => {
        this.resultados = [];
        console.error('Erro ao buscar vinis:', err);
      });
  }

  irParaDetalhes(id: number) {
    this.router.navigate(['/detalhe-do-produto', id]);
  }
}
