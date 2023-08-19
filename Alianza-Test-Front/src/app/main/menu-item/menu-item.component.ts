import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'bor-menu-item',
  templateUrl: './menu-item.component.html',
  styleUrls: ['./menu-item.component.scss']
})
export class MenuItemComponent implements OnInit {

  navMenuItems = [{
    name: "Clients",
    icon: "assets/user_icon.png",
    children: [{
      name: "Clients",
      link: "/main/clientes",
      isDisabled: false
    },
    {
      name: "Client look history",
      link: "",
      isDisabled: true
    },
    {
      name: "Emergency PIN configuration",
      link: "",
      isDisabled: true
    },
    {
      name: "Emergency PIN configuration",
      link: "",
      isDisabled: true
    }]
  }];

  constructor() { }

  ngOnInit(): void {
  }

}
