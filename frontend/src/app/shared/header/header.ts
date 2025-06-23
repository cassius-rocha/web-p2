import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './header.html',
  styleUrls: ['./header.css']
})
export class HeaderComponent {
  termoBusca: string = '';

  constructor(private router: Router) { }

  goToVitrine() {
    this.router.navigate(['/vitrine']);
  }

  buscarProdutos() {
    const termo = this.termoBusca.trim();
    if (termo) {
      this.router.navigate(['/resultado'], { queryParams: { termo } });
    }
  }

  goToLogin() {
    this.router.navigate(['/cadastro-login']);
  }

  goToCadastro() {
    this.router.navigate(['/cadastro']);
  }

  goToCesta() {
    this.router.navigate(['/cesta']);
  }

  isLogado(): boolean {
    return !!localStorage.getItem('usuarioLogado');
  }

  logout() {
    localStorage.removeItem('usuarioLogado');
    this.router.navigate(['/']);
  }

}