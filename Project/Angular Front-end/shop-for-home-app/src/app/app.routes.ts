import { Routes } from '@angular/router';
import { UserHomeComponent } from './user home/user-home.component';
import { LoginComponent } from './login/login.component';
import { UserCart } from './user home/cart/cart.component';
import { UserDiscounts } from './user home/discounts/discounts.component';
import { UserProducts } from './user home/products/products.component';
import { UserWishlist } from './user home/wishlist/wishlist.component';
import { UserShellComponent } from './user home/user-shell.component';
import { AdminShellComponent } from './admin home/admin-shell.component';
import { AdminHomeComponent } from './admin home/admin-home.component';
import { AdminSalesComponent } from './admin home/sales/admin-sales.component';
import { AdminProductsComponent } from './admin home/products/admin-products.component';
import { AdminUsersComponent } from './admin home/users/admin-users.component';
import { AdminStocksComponent } from './admin home/stocks/admin-stocks.component';
import { AdminProductsCreateComponent } from './admin home/products/CRUD/create/admin-products-create.component';
import { AdminUsersReadComponent } from './admin home/users/CRUD/read/admin-users-read.component';
import { AdminUsersCreateComponent } from './admin home/users/CRUD/create/admin-users-create.component';
import { AdminUsersReadAllComponent } from './admin home/users/CRUD/read-all/admin-users-read-all.component';
import { AdminUsersUpdateComponent } from './admin home/users/CRUD/update/admin-users-update.component';
import { AdminUsersDeleteComponent } from './admin home/users/CRUD/delete/admin-users-delete.component';
import { AdminDiscountsCreateComponent } from './admin home/discounts/CRUD/create/admin-discounts-create.component';
import { AdminDiscountsReadAllComponent } from './admin home/discounts/CRUD/read-all/admin-discounts-read-all.component';
import { AdminProductsReadComponent } from './admin home/products/CRUD/read/admin-products-read.component';
import { AdminProductsReadAllComponent } from './admin home/products/CRUD/read-all/admin-products-read-all.component';
import { AdminProductsUpdateComponent } from './admin home/products/CRUD/update/admin-products-update.component';
import { AdminProductsDeleteComponent } from './admin home/products/CRUD/delete/admin-products-delete.component';
import { AdminAdminComponent } from './admin home/admin/admin-admin.component';
import { AdminAdminCreateComponent } from './admin home/admin/CRUD/create/admin-admin-create.component';
import { AdminAdminReadComponent } from './admin home/admin/CRUD/read/admin-admin-read.component';
import { AdminAdminReadAllComponent } from './admin home/admin/CRUD/read-all/admin-admin-read-all.component';
import { AdminAdminUpdateComponent } from './admin home/admin/CRUD/update/admin-admin-update.component';
import { AdminAdminDeleteComponent } from './admin home/admin/CRUD/delete/admin-admin-delete.component';
import { AdminDiscountsComponent } from './admin home/discounts/admin-discounts.component';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  {
    path: 'user',
    component: UserShellComponent, // This component acts as a shell for user routes
    children: [
      {
        path: 'home',
        component: UserHomeComponent,
        children: [
          { path: 'cart', component: UserCart },
          { path: 'discounts', component: UserDiscounts },
          { path: 'products', component: UserProducts },
          { path: 'wishlist', component: UserWishlist },
          { path: '', redirectTo: 'products', pathMatch: 'full' }
        ],
        
      },

      { path: '', redirectTo: 'home', pathMatch: 'full' }, // Default redirect to user-home
    ],
  },
  {
    path: 'admin',
    component: AdminShellComponent, // This component acts as a shell for user routes
    children: [
      {
        path: 'home',
        component: AdminHomeComponent,
        children: [
          { path: 'sales', component: AdminSalesComponent },
          { 
            path: 'discounts', 
            component: AdminDiscountsComponent,
            children: [
              { path: 'create', component: AdminDiscountsCreateComponent},
              { path: 'read-all', component: AdminDiscountsReadAllComponent},
              { path: '', redirectTo: 'read-all', pathMatch: 'full' }
            ] 
          },
          { 
            path: 'products', 
            component: AdminProductsComponent,
            children: [
              { path: 'create', component: AdminProductsCreateComponent},
              { path: 'read', component: AdminProductsReadComponent},
              { path: 'read-all', component: AdminProductsReadAllComponent},
              { path: 'update', component: AdminProductsUpdateComponent},
              { path: 'delete', component: AdminProductsDeleteComponent},
              { path: '', redirectTo: 'read-all', pathMatch: 'full' }
            ]
          },
          { 
            path: 'users', 
            component: AdminUsersComponent ,
            children: [
              { path: 'create', component: AdminUsersCreateComponent},
              { path: 'read', component: AdminUsersReadComponent},
              { path: 'read-all', component: AdminUsersReadAllComponent},
              { path: 'update', component: AdminUsersUpdateComponent},
              { path: 'delete', component: AdminUsersDeleteComponent},
              { path: '', redirectTo: 'read-all', pathMatch: 'full' }
            ]
          },
          { 
            path: 'admin', 
            component: AdminAdminComponent ,
            children: [
              { path: 'create', component: AdminAdminCreateComponent},
              { path: 'read', component: AdminAdminReadComponent},
              { path: 'read-all', component: AdminAdminReadAllComponent},
              { path: 'update', component: AdminAdminUpdateComponent},
              { path: 'delete', component: AdminAdminDeleteComponent},
              { path: '', redirectTo: 'read-all', pathMatch: 'full' }
            ]
          },
          { path: 'stocks', component: AdminStocksComponent },
          { path: '', redirectTo: 'sales', pathMatch: 'full' }
        ],
        
      },

      { path: '', redirectTo: 'home', pathMatch: 'full' }, // Default redirect to user-home
    ],
  },
];
