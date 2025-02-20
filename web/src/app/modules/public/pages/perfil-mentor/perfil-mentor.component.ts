import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

import { MentorPublicService } from '../../services/mentor-public.service';

import { Loading } from '@core/models/loading.model';
import { MentorPublic } from '../../models/mentor-public.model';

@Component({
  selector: 'app-perfil-mentor',
  templateUrl: './perfil-mentor.component.html',
  styleUrls: ['./perfil-mentor.component.scss']
})
export class PerfilMentorComponent implements OnInit {

  carregar = new Loading();
  mentor!: MentorPublic;
  private apelido = '';

  constructor(
    private activatedRoute: ActivatedRoute,
    private mentorPublicService: MentorPublicService
  ) {
    this.apelido = this.activatedRoute.snapshot.params.apelido;
  }

  ngOnInit(): void {
    this.mentorPublicService.buscarPorApelido(this.apelido, this.carregar).subscribe(_ => this.mentor = _);
  }
}
