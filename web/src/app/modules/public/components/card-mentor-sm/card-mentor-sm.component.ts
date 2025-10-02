import { Component, Input } from '@angular/core';

import { DOMService } from '@core/services/dom.service';
import { MentorBusca } from '../../models/mentor-public.model';

@Component({
  selector: 'app-card-mentor-sm',
  templateUrl: './card-mentor-sm.component.html',
  styleUrls: ['./card-mentor-sm.component.scss']
})
export class CardMentorSmComponent {

  @Input() mentor!: MentorBusca;

  constructor(private domService: DOMService) { }

  verMentor(apelido: string): void {
    this.domService.redirecionar(`/mentores/${apelido}`);
  }
}
