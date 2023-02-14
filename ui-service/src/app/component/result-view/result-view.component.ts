import {Component, Input, OnInit} from '@angular/core';
import {CgData} from '../../model/cg-data';
import {EventProviderService} from '../../service/event/event-provider.service';
import {MatDialog} from '@angular/material';
import {RestService} from '../../service/rest/rest.service';
import {CgPath} from '../../model/cg-path';
import {CgTriple} from '../../model/cg-triple';

@Component({
  selector: 'app-result-view',
  templateUrl: './result-view.component.html',
  styleUrls: ['./result-view.component.css']
})
export class ResultViewComponent implements OnInit {
  @Input()
  ticketId: string;
  @Input()
  graphData: CgData;
  facadeResult: any;
  triple: any;
  veracityScore: any;
  explanation: any;
  stringObject: any;
  piecesOfEvidence: CgPath[] = [];
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
    this.eventService.refreshGraphEvent.emit();
  }

  getResult() {
    console.log('this.ticketId is ' + this.ticketId);
    this.restService.getRequest('/retrieveResult', {taskId: this.ticketId}).subscribe((jsonVal) => {
      console.log('factchecking result is here ');
      console.log(jsonVal);
      this.stringObject = JSON.parse(JSON.stringify(jsonVal));
      // make graphdata
      this.graphData = new CgData();
      this.graphData.fact = new CgTriple(this.stringObject.subject , this.stringObject.predicate , this.stringObject.object);
      this.graphData.veracityValue = this.stringObject.veracityScore;

      console.log(' size is : ' + this.stringObject.piecesOfEvidence.length);
      for (let evidenceCounter = 0 ; evidenceCounter < this.stringObject.piecesOfEvidence.length ; evidenceCounter++ ) {
        const evidence = this.stringObject.piecesOfEvidence[evidenceCounter];
        if (evidence.source !== 'Copaal') {

        } else {
          console.log(evidence);
        const pathEvidences: CgTriple[] = [];
        const first = this.stringObject.subject;
        const last = this.stringObject.object;
        if (evidence.pathEvidences.length === 1) {
          if (evidence.pathEvidences[0].inverse === true) {
            pathEvidences.push(new CgTriple(first, evidence.pathEvidences[0].property, last));
            this.piecesOfEvidence.push(new CgPath(pathEvidences, evidence.score, evidence.verbalization, null));
          } else {
            // inverse
            pathEvidences.push(new CgTriple(last, evidence.pathEvidences[0].property, first));
            this.piecesOfEvidence.push(new CgPath(pathEvidences, evidence.score, evidence.verbalization, null));
          }
        } else {
          // add first
          if (evidence.pathEvidences[0].inverse === true) {
            pathEvidences.push(new CgTriple(first, evidence.pathEvidences[0].property, 'a1'));
          } else {
            // inverse
            pathEvidences.push(new CgTriple('a1', evidence.pathEvidences[0].property, first));
          }
          // add middles
          for (let i = 1; i < evidence.pathEvidences.length - 1; i++) {
            if (evidence.pathEvidences[i].inverse === true) {
              pathEvidences.push(new CgTriple('a' + i, evidence.pathEvidences[0].property, 'a' + i + 1));
            } else {
              // inverse
              pathEvidences.push(new CgTriple('a' + i + 1, evidence.pathEvidences[0].property, 'a' + i));
            }
          }
          // add last
          if (evidence.pathEvidences[evidence.pathEvidences.length - 1].inverse === true) {
            pathEvidences.push(new CgTriple('a' + (pathEvidences.length - 1), evidence.pathEvidences[0].property, last));
          } else {
            // inverse
            pathEvidences.push(new CgTriple(last, evidence.pathEvidences[0].property, 'a' + (pathEvidences.length - 1)));
          }
          this.piecesOfEvidence.push(new CgPath(pathEvidences, evidence.score, evidence.verbalization, null));
        }
      }
      }
      // this.graphData.finalJudgement = this.stringObject.vera;
      // this.piecesOfEvidence.push(new CgPath('dd', 0.2 , '' , 11 ));
      // this.piecesOfEvidence.push(new CgPath('cd', 0.9 , '' , 12 ));

      this.graphData.piecesOfEvidence = this.piecesOfEvidence;

      this.eventService.updateDataEvent.emit(this.graphData);

      this.facadeResult = jsonVal;
      // JSON.stringify(jsonVal);
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
  getGraphData() {
    return this.graphData;
  }
}
