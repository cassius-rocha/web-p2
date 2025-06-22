import { Routes } from '@angular/router';
import { VitrineComponent } from './pages/vitrine/vitrine'; 
import { BuscaComponent } from './pages/busca/busca';

export const routes: Routes = [
  {
    path: '',
    component: VitrineComponent
  },
  {
    path: 'vitrine',
    component: VitrineComponent // ✅ necessário para permitir redirecionamento via router.navigate(['/vitrine'])
  },
  {
    path: 'detalhe-do-produto/:id',
    loadComponent: () =>
      import('./pages/detalhe-do-produto/detalhe-do-produto').then(m => m.DetalheDoProdutoComponent)
  },
  {
    path: 'resultado',
    component: BuscaComponent
  },
  {
    path: 'cesta',
    loadComponent: () => import('./pages/cesta/cesta').then(m => m.CestaComponent)
  },
  {
    path: 'cadastro',
    loadComponent: () => import('./pages/cadastro/cadastro').then(m => m.CadastroComponent)
  },
  {
    path: 'cadastro-login',
    loadComponent: () => import('./pages/login/login').then(m => m.LoginComponent)
  }
];