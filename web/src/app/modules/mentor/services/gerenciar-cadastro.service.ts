import { Injectable } from '@angular/core';

import { forkJoin } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { HorariosMentorService } from './horarios-mentor.service';
import { MentorService } from './mentor.service';
import { PlanosMentorService } from './planos-mentor.service';

import { Mentor } from '../models/mentor.model';
import { Loading } from '@core/models/loading.model';

@Injectable()
export class GerenciarCadastroService {

  carregar = new Loading(true);
  private _etapa = 'INICIAL';
  private _mentor: Mentor | null = null;

  constructor(
    private horariosService: HorariosMentorService,
    private mentorService: MentorService,
    private planosService: PlanosMentorService
  ) { }

  get etapa(): string {
    return this._etapa;
  }

  set etapa(valor: string) {
    this._etapa = valor;
  }

  get mentor(): Mentor {
    return this._mentor!;
  }

  set mentor(valor: Mentor) {
    this._mentor = valor;
  }

  buscarMentor(): void {
    this.carregar.load = true;
    this.mentorService.buscarDados().subscribe({
      next: _ => this._mentor = _,
      error: () => {
        this._etapa = 'INICIAL';
        this.carregar.load = false;
      },
      complete: () => this.buscarPlanosHorarios()
    });
  }

  private buscarPlanosHorarios(): void {
    const planos$ = this.planosService.getList();
    const horarios$ = this.horariosService.getList();

    forkJoin([planos$, horarios$])
      .pipe(finalize(() => this.carregar.load = false))
      .subscribe(([planos, horarios]) => {
        this._mentor!.planos = planos;
        this._mentor!.horarios = horarios;

        if (!planos.length) {
          this._etapa = 'PLANOS';
          return;
        }

        if (!horarios.length) {
          this._etapa = 'HORARIOS';
          return;
        }

        this._etapa = 'FINAL';
      })
  }

  resetarTudo(): void {
    this._etapa = 'INICIAL';
    this._mentor = null;
  }

}
