import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { debounceTime, filter, distinctUntilChanged } from 'rxjs/operators';

import { MentorPublicService } from '../../services/mentor-public.service';
import { TagService } from '@app/shared/services/tag.service';

import { Loading } from '@core/models/loading.model';
import { MentorBusca } from '../../models/mentor-public.model';
import { Paginacao } from '@shared/models/paginacao.model';
import { Tag } from '@shared/models/tag.model';

@Component({
  selector: 'app-lista-mentores',
  templateUrl: './lista-mentores.component.html',
  styleUrls: ['./lista-mentores.component.scss']
})
export class ListaMentoresComponent implements OnInit {

  carregar = new Loading();
  carregarTags = new Loading();
  form: FormGroup;

  tags: Paginacao<Tag> = {
    lista: [],
    pagina: 1,
    totalPaginas: 0,
    totalRegistros: 0
  };

  mentores: Paginacao<MentorBusca> = {
    lista: [],
    pagina: 1,
    totalPaginas: 0,
    totalRegistros: 0
  };

  constructor(
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private mentorPublicService: MentorPublicService,
    private router: Router,
    private tagService: TagService
  ) {
    this.form = this.formBuilder.group({
      texto: [null],
      cargo: [null],
      empresa: [null],
      tags: [null],
      pagina: [1, Validators.required],
      totalPorPagina: [6, Validators.required]
    });

    this.form.get('texto')?.valueChanges
      .pipe(
        debounceTime(1500),
        filter((_: string) => _ != null),
        filter((_: string) => _.length == 0 || _.length >= 3),
        distinctUntilChanged()
      )
      .subscribe(texto => {
        this.form.reset({ texto: texto, pagina: 1, totalPorPagina: 6 });
        this.atualizarParams(texto);
      })
  }

  ngOnInit(): void {
    this.buscarTags();
    this.activatedRoute.queryParams.subscribe(params => {
      const tags: string[] = params.tags ? params.tags.split(',') : [];

      this.form.patchValue({
        ...params,
        tags: tags.length ? tags.map(t => Number(t)) : null
      });

      this.buscarMentores();
    })
  }

  trocarPagina(numero: number): void {
    this.form.get('pagina')?.setValue(numero);
    this.buscarMentores();
  }

  private buscarTags(): void {
    this.tagService.getWithParams({ pagina: 1, totalPorPagina: 999 }, this.carregarTags).subscribe(_ => this.tags = _);
  }

  private buscarMentores(): void {
    this.mentorPublicService.getWithParams(this.form.value, this.carregar).subscribe(_ => this.mentores = _);
  }

  atualizarParams(texto?: string): void {
    if (!texto) {
      this.form.get('texto')?.reset();
    }

    const params = {
      texto: texto || null,
      cargo: !texto ? this.form.value.cargo : null,
      empresa: !texto ? this.form.value.empresa : null,
      tags: !texto ? this.tagsFormValue : null
    };

    this.router.navigate([],
      {
        relativeTo: this.activatedRoute,
        queryParams: params,
        queryParamsHandling: 'merge'
      }
    );
  }

  get tagsFormValue(): string {
    const tags = this.form.get('tags')?.value;
    return tags ? tags.toString() : null;
  }

  get totalPorPagina() {
    return this.form.get('totalPorPagina')?.value;
  }
}
