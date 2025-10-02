import { Component, Input } from '@angular/core';

import { DOMService } from '@core/services/dom.service';
import { MentorBusca } from '../../models/mentor-public.model';

@Component({
  selector: 'app-card-mentor-lg',
  templateUrl: './card-mentor-lg.component.html',
  styleUrls: ['./card-mentor-lg.component.scss']
})
export class CardMentorLgComponent {

  @Input() mentor!: MentorBusca;

  constructor(private domService: DOMService) { }

  verMentor(apelido: string): void {
    this.domService.redirecionar(`/mentores/${apelido}`);
  }
}
