import { Routes } from '@angular/router';
import { VitrineComponent } from './pages/vitrine/vitrine'; // ajuste o caminho se necessário

export const routes: Routes = [
    {
        path: '',
        component: VitrineComponent
    },
    {
        path: 'detalhe-do-produto/:id',
        loadComponent: () => 
            import('./pages/detalhe-produto/detalhe-produto').then(m => m.DetalheProdutoComponent)
    }

];
