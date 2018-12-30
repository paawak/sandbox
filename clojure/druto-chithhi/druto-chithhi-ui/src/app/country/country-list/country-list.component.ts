import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-country-list',
  templateUrl: './country-list.component.html',
  styleUrls: ['./country-list.component.scss']
})
export class CountryListComponent implements OnInit {

  countries: any[] = [
    {
      id: 1,
      code: 'IN',
      name: 'India'
    },
    {
      id: 2,
      code: 'US',
      name: 'United States Of America'
    },
    {
      id: 3,
      code: 'BN',
      name: 'Bangladesh'
    }
  ];

  constructor() { }

  ngOnInit() {
  }

}
