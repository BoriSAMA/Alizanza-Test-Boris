import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Client } from 'src/app/core/model/clients.model';
import ListResponse from 'src/app/core/model/utils/list-response.model';
import { ClientService } from 'src/app/core/service/implementations/client.service';

@Component({
  selector: 'bor-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.scss']
})
export class ClientsComponent implements OnInit {

  displayModal: boolean = true;
  sharedKeyControl: FormControl = new FormControl('', Validators.pattern(/^[a-zA-Z]+$/));
  clients: Array<Client> = [];
  isAdvancedSearch: boolean = false;

  constructor(
    private clientService: ClientService
  ) { }

  ngOnInit(): void {
    this.clientService.getAll().subscribe({
      next: (res: Array<Client>) => {
        this.clients = res;
      },
      error: (err) => {
        // alert("Ha ocurrido un error inesperado")
      }
    })
  }

  getFilterPage(filters: any) {
    this.clientService.filter(filters).subscribe({
      next: (res: ListResponse<Client>) => {
        this.clients = res.data;
      },
      error: (err) => {
        // alert("Ha ocurrido un error inesperado")
      }
    })
  }

  getBysharedKey() {
    let sharedKey = this.sharedKeyControl.value;
    if (sharedKey) {
      let filters = {
        sharedKey: sharedKey
      }
      this.getFilterPage(filters);
    }
  }

  addClient(client: any) {
    this.clientService.save(client).subscribe({
      next: (res: number) => {
        // alert("Se ha guardado el cliente exitosamente");
      },
      error: (err) => {
        // alert("Ha ocurrido un error inesperado");
      }
    })
    this.displayModal = false;
  }

}
