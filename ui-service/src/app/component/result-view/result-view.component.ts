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
  triple: any;
  veracityScore: any;
  explanation: any;
  stringObject: any;
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

      this.stringObject = JSON.parse(JSON.stringify(jsonVal));
      if ( this.stringObject.subject != null) {
        this.triple = this.stringObject.subject + ' ' + this.stringObject.predicate + ' ' + this.stringObject.object;
      } else {
        this.triple = 'waiting for data ...';
      }
      if ( this.stringObject.veracityScore != null) {
        this.veracityScore = this.stringObject.veracityScore;
      } else {
        this.veracityScore = 'waiting for data ...';
      }

      if ( this.stringObject.explanation != null ) {
        this.explanation = this.stringObject.explanation;
      } else {
        this.explanation = 'waiting for data ...';
      }
    });
    console.log('end retrieve');
  }

}
