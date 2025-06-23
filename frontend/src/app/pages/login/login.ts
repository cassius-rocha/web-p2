import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ClienteService } from '../../services/cliente.service';
import { HeaderComponent } from '../../shared/header/header';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, HeaderComponent],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class LoginComponent {
  email = '';
  senha = '';
  mensagem = '';
  redirectUrl = '/vitrine'; // Valor padrão

  constructor(private clienteService: ClienteService, private router: Router) { }

  ngOnInit() {
    const nav = this.router.getCurrentNavigation();
    const state = nav?.extras?.state as {
      redirectUrl: string,
      message?: string
    };

    if (state) {
      if (state.redirectUrl) {
        this.redirectUrl = state.redirectUrl;
      }
      if (state.message) {
        this.mensagem = state.message;
      }
    }
  }

  login() {
    this.mensagem = '';

    if (!this.email || !this.senha) {
      this.mensagem = 'Preencha todos os campos!';
      return;
    }

    this.clienteService.loginCliente({ email: this.email, senha: this.senha }).subscribe({
      next: (res) => {
        if (res && res.id) {
          localStorage.setItem('usuarioLogado', JSON.stringify(res));
          this.router.navigate([this.redirectUrl]);
        } else {
          this.mensagem = 'Usuário ou senha inválidos.';
        }
      },
      error: () => {
        this.mensagem = 'Usuário ou senha inválidos.';
      }
    });
  }

  redefinirSenha() {
    if (!this.email) {
      this.mensagem = 'Informe seu e-mail para redefinir a senha.';
      return;
    }

    this.clienteService.redefinirSenha({ email: this.email }).subscribe({
      next: (res) => {
        this.mensagem = res;
      },
      error: (err) => {
        console.error(err);

        if (err.status === 404) {
          this.mensagem = 'E-mail não cadastrado, vá para a página de cadastro.';
        } else if (err.error && typeof err.error === 'string') {
          this.mensagem = err.error;
        } else {
          this.mensagem = 'Erro ao tentar redefinir senha.';
        }
      }
    });
  }
}