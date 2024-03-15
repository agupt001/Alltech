import { Routes } from '@angular/router';
import { CreateComponent } from './create/create.component';
import { ReadByIdComponent } from './readById/readbyid.component';
import { ReadAllComponent } from './readAll/readall.component';
import { UpdateComponent } from './update/update.component';

export const routes: Routes = [
    { path: '', redirectTo: '/read-all-component', pathMatch: 'full' },
    {
        path: 'create-component',
        component: CreateComponent
    },
    {
        path: 'read-by-id-component/:id',
        component: ReadByIdComponent
    },
    {
        path: 'read-all-component',
        component: ReadAllComponent
    },
    {
        path: 'update-component',
        component: UpdateComponent
    }
];
