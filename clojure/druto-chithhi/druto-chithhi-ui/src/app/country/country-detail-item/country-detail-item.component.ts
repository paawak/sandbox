import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-country-detail-item',
  templateUrl: './country-detail-item.component.html',
  styleUrls: ['./country-detail-item.component.scss']
})
export class CountryDetailItemComponent implements OnInit {

  private  countryId: string;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(
      (params) => {
        this.countryId = params['countryId'];
      }
    );
  }

}
