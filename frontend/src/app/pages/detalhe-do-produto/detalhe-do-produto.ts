import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { BotaoAdicionarCestaComponent } from '../../shared/botao-adicionar-cesta/botao-adicionar-cesta.component';

@Component({
  selector: 'app-detalhe-do-produto',
  standalone: true,
  imports: [CommonModule, RouterModule, BotaoAdicionarCestaComponent],
  templateUrl: './detalhe-do-produto.html',
  styleUrls: ['./detalhe-do-produto.css']
})
export class DetalheDoProdutoComponent implements OnInit {
  vinil: any;

  constructor(private route: ActivatedRoute, private http: HttpClient) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.http.get<any>(`http://localhost:8080/vinil/${id}`).subscribe(data => {
      this.vinil = data;
    });
  }
}
