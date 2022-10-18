import {Component, Input, OnInit} from '@angular/core';
import {CgData} from '../../model/cg-data';
import {EventProviderService} from '../../service/event/event-provider.service';
import {MatDialog} from '@angular/material';
import {RestService} from '../../service/rest/rest.service';

@Component({
  selector: 'app-result-view',
  templateUrl: './result-view.component.html',
  styleUrls: ['./result-view.component.css']
})
export class ResultViewComponent implements OnInit {
  @Input()
  ticketId: string;
  facadeResult: any;
  constructor(public eventService: EventProviderService, public restService: RestService) { }

  ngOnInit() {
    console.log('Start retrieve the result');
    this.getResult();
  }

  showForm() {
    this.eventService.btnBackEvent.emit( );
  }

  retry() {
    this.getResult();
  }

  getResult() {
    console.log('this.ticketId is ' + this.ticketId);
    this.restService.getRequest('/retrieveResult', {taskId: this.ticketId}).subscribe((jsonVal) => {
      console.log('factchecking result is here ');
      console.log(jsonVal);
      this.facadeResult = jsonVal;
      // JSON.stringify(jsonVal);
    });
    console.log('end retrieve');
  }

}
