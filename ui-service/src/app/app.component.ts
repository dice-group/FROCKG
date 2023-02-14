import {AfterContentInit, Component, NgZone, OnInit} from '@angular/core';

import {EventProviderService} from './service/event/event-provider.service';
import {CgData} from './model/cg-data';
import {CgTriple} from './model/cg-triple';
/*import {GRAPHDATA} from './model/mock-data';*/
import * as d3 from 'd3';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'cg-ui';
  ticketId: string;

  public graphData: CgData;
  public showAppResultView = true;
  public showAppUserForm = true;
  public showAppTicketView = true;

  constructor(eventService: EventProviderService) {
    eventService.submitTicketEvent.subscribe((ticket: string) => {
      this.submitTicketEventRaised(ticket);
    });

    eventService.submitFormEvent.subscribe((taskId: string) => {
      this.submitFormEventRaised(taskId);
    });

    eventService.btnBackEvent.subscribe(() => {
      this.btnBackEventRaised();
    });

    eventService.updateDataEvent.subscribe((graphData: CgData) => {
        console.log('----------------graph Data-----------------');
        console.log(graphData);
        this.graphData = graphData;
    });

    eventService.refreshGraphEvent.subscribe(
      () => {
        console.log('refreshing the graph');
      }) ;
  }

  submitTicketEventRaised(ticketId: string) {
    this.showAppResultView = true;
    this.showAppUserForm = false;
    this.showAppTicketView = false;
    this.ticketId = ticketId;
  }
  submitFormEventRaised(ticketId: string) {
    this.showAppResultView = false;
    this.showAppUserForm = false;
    this.showAppTicketView = true;
    this.ticketId = ticketId;
  }

  btnBackEventRaised() {
    this.showAppResultView = false;
    this.showAppUserForm = true;
    this.showAppTicketView = false;
  }

  getTicketId() {
    return this.ticketId;
  }

  ngOnInit() {
    this.showAppResultView = false;
    this.showAppUserForm = true;
    this.showAppTicketView = false;
  }

  getGraphData() {
    return this.graphData;
  }
}
