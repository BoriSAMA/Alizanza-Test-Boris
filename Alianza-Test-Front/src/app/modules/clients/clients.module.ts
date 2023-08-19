import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientsRoutingModule } from './clients-routing.module';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ClientsComponent } from './clients/clients.component';
import { ClientsTableComponent } from './clients/clients-table/clients-table.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ClientsFormComponent } from './clients/clients-form/clients-form.component';

@NgModule({
  declarations: [ClientsComponent, ClientsTableComponent, ClientsFormComponent],
  imports: [
    CommonModule,
    ClientsRoutingModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatDatepickerModule
  ]
})
export class ClientsModule { }
