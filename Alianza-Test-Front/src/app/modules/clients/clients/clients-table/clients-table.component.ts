import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/core/model/clients.model';

@Component({
  selector: 'bor-clients-table',
  templateUrl: './clients-table.component.html',
  styleUrls: ['./clients-table.component.scss']
})
export class ClientsTableComponent implements OnInit {

  @Input() tableContent: Array<Client>;
  tableHeaders: Array<any>;
  currentPage: number;
  maxPage: number;

  constructor() { }

  ngOnInit(): void {
    this.tableHeaders = [
      {
        type: "text",
        description: "Shared Key",
        width:"18%"
      },
      {
        type: "text",
        description: "Bussiness ID",
        width:"18%"
      },
      {
        type: "text",
        description: "E-mail",
        width:"18%"
      },
      {
        type: "text",
        description: "Phone",
        width:"18%"
      },
      {
        type: "text",
        description: "Data Added",
        width:"18%"
      },
      {
        type: "icon",
        description: "border_color",
        width:"10%"
      }
    ];
    this.tableContent = [{
      id: 1,
      sharedKey: "jgutierrez",
      bussinessId: "Juliana Gutierrez",
      email: "jgutierrez@gmail.com",
      phone: "3200000000",
      createdDate: "18/08/2023",
      endDate: null
    }];
  }

}
