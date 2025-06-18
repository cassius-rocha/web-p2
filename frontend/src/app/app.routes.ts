import { Routes } from '@angular/router';
import { VitrineComponent } from './pages/vitrine/vitrine'; // ajuste o caminho se necessário
import { BuscaComponent } from './pages/busca/busca';

export const routes: Routes = [
    {
        path: '',
        component: VitrineComponent
    },
    {
        path: 'detalhe-do-produto/:id',
        loadComponent: () =>
            import('./pages/detalhe-do-produto/detalhe-do-produto').then(m => m.DetalheDoProdutoComponent)
    },
    {
        path: 'resultado',
        component: BuscaComponent
    }
];
