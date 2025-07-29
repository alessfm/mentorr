import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';

import { UtilService } from '@core/services/util.service';
import { MentorBusca } from '../../models/mentor-public.model';

@Component({
  selector: 'app-card-mentor',
  templateUrl: './card-mentor.component.html',
  styleUrls: ['./card-mentor.component.scss']
})
export class CardMentorComponent implements OnChanges {

  @Input() mentor!: MentorBusca;
  estrelas: any[] = [];

  constructor(private utilService: UtilService) { }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes.mentor && this.mentor) {
      this.montarEstrelas();
    }
  }

  verMentor(apelido: string): void {
    this.utilService.redirecionar(`/mentores/${apelido}`);
  }

  private montarEstrelas(): void {
    const nota = this.mentor.nota;

    if (nota == null) {
      return;
    }

    for (let i = 0.5; i <= nota; i = i + 0.5) {
      const index = this.estrelas.findIndex(e => e.completo == false);
      (index != -1) ? this.estrelas[index].completo = true : this.estrelas.push({ completo: false });
    }
  }
}
