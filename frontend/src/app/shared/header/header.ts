import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './header.html',
  styleUrls: ['./header.css']
})
export class HeaderComponent {
  termoBusca: string = '';

  constructor(private router: Router) {}

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
}