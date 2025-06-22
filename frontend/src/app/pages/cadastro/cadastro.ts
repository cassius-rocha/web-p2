import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ClienteService } from '../../services/cliente.service';
import { HeaderComponent } from '../../shared/header/header';

@Component({
  selector: 'app-cadastro',
  standalone: true,
  imports: [CommonModule, FormsModule, HeaderComponent],
  templateUrl: './cadastro.html',
  styleUrls: ['./cadastro.css']
})
export class CadastroComponent {
  nome = '';
  cpf = '';
  email = '';
  senha = '';
  mensagem = '';

  constructor(private clienteService: ClienteService) {}

  cadastrar() {
    if (!this.nome || !this.cpf || !this.email || !this.senha) {
      this.mensagem = 'Preencha todos os campos!';
      return;
    }

    const novoCliente = { nome: this.nome, cpf: this.cpf, email: this.email, senha: this.senha };

    this.clienteService.criarCliente(novoCliente).subscribe({
      next: () => {
        this.mensagem = 'Cadastro feito com sucesso! Verifique seu e-mail.';
        this.nome = this.cpf = this.email = this.senha = '';
      },
      error: (err) => {
        if (err.error?.includes('cpf')) {
          this.mensagem = 'CPF já cadastrado, vá para a página de login.';
        } else if (err.error?.includes('email')) {
          this.mensagem = 'E-mail já cadastrado, vá para a página de login.';
        } else {
          this.mensagem = 'Erro ao cadastrar. Tente novamente.';
        }
      }
    });
  }
}
