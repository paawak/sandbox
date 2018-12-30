import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-country-list-item',
  templateUrl: './country-list-item.component.html',
  styleUrls: ['./country-list-item.component.scss']
})
export class CountryListItemComponent implements OnInit {

  @Input() currentCountry: any;

  constructor() { }

  ngOnInit() {
  }

}
