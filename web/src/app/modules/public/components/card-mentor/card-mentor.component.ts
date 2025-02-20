import { Component, Input } from '@angular/core';

import { UtilService } from '@core/services/util.service';
import { MentorBusca } from '../../models/mentor-public.model';

@Component({
  selector: 'app-card-mentor',
  templateUrl: './card-mentor.component.html',
  styleUrls: ['./card-mentor.component.scss']
})
export class CardMentorComponent {

  @Input() mentor!: MentorBusca;

  constructor(private utilService: UtilService) { }

  verMentor(apelido: string): void {
    this.utilService.redirecionar(`/mentores/${apelido}`);
  }
}
