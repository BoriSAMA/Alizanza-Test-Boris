import { Injectable } from '@angular/core';
import { Client } from '../../model/clients.model';
import { ServiceImplementation } from '../service.impl.service';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClientService extends ServiceImplementation<Client> {

  constructor(private http: HttpClient) {
    super();
		this.httpClient = http;
		this.endpoint = environment.endpointClient;
  }

}
