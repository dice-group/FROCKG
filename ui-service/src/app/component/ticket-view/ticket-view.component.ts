import {Component, Input, OnInit} from '@angular/core';
import {EventProviderService} from "../../service/event/event-provider.service";

@Component({
  selector: 'app-ticket-view',
  templateUrl: './ticket-view.component.html',
  styleUrls: ['./ticket-view.component.css']
})


export class TicketViewComponent implements OnInit {

  @Input()
  ticketId: string;

  constructor(public eventService: EventProviderService) { }

  ngOnInit() {
  }

  showForm() {
    this.eventService.btnBackEvent.emit( );
  }

  showResult(){
    this.eventService.submitTicketEvent.emit(this.ticketId);
  }

}
