import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-vitrine',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './vitrine.html',
  styleUrls: ['./vitrine.css']
})
export class VitrineComponent implements OnInit {
  vinis: any[] = [];

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.http.get<any[]>('http://localhost:8080/vinil')
      .subscribe(data => {
        this.vinis = data.slice(0, 9); // pega os 9 primeiros
      });
  }

  irParaDetalhes(id: number) {
    this.router.navigate(['/detalhe-do-produto', id]);
  }
}