import { Component, OnInit } from '@angular/core';

import { Loading } from '@core/models/loading.model';

@Component({
  selector: 'app-mentorias-aluno',
  templateUrl: './mentorias-aluno.component.html',
  styleUrls: ['./mentorias-aluno.component.scss']
})
export class MentoriasAlunoComponent implements OnInit {

  carregar = new Loading();

  constructor() { }

  ngOnInit(): void {
  }

}
