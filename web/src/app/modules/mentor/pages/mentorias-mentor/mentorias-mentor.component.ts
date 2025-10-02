import { Component, OnInit } from '@angular/core';

import { Loading } from '@core/models/loading.model';

@Component({
  selector: 'app-mentorias-mentor',
  templateUrl: './mentorias-mentor.component.html',
  styleUrls: ['./mentorias-mentor.component.scss']
})
export class MentoriasMentorComponent implements OnInit {

  carregar = new Loading();

  constructor() { }

  ngOnInit(): void {
  }

}
