import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {CgData} from "../../model/cg-data";
import {RestService} from "../../service/rest/rest.service";
import {CgTriple} from "../../model/cg-triple";
import {CgPath} from "../../model/cg-path";
import {combineAll} from "rxjs/operators";

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {
  ticketId: string;
  graphData?: CgData;
  triple: any;
  veracityScore: any;
  explanation: any;
  facadeResult: any;
  constructor(private router: Router,private route: ActivatedRoute,public restService: RestService) {
    this.route.queryParams
      .subscribe(params => {
        console.log(params);
        this.ticketId = params.id;
        console.log(this.ticketId);
      });
    this.getResult();
  }

  ngOnInit(): void {

  }
  showForm() {
    this.router.navigateByUrl('/home');
  }

  retry() {
    location.reload();
  }

  getResult() {
    console.log('this.ticketId is ' + this.ticketId);
    this.restService.getRequest('/retrieveResult', {taskId: this.ticketId}).subscribe((jsonVal) => {
      console.log('factchecking result is here ');
      console.log(jsonVal);
      let piecesOfEvidence: CgPath[] = [];
      let stringObject = JSON.parse(JSON.stringify(jsonVal));
      // make graphdata
      this.graphData = new CgData();
      this.graphData.fact = new CgTriple(stringObject.subject , stringObject.predicate , stringObject.object);
      this.graphData.veracityValue = stringObject.veracityScore;

      console.log(' size is : ' + stringObject.piecesOfEvidence.length);
      for (let evidenceCounter = 0 ; evidenceCounter < stringObject.piecesOfEvidence.length ; evidenceCounter++ ) {
        const evidence = stringObject.piecesOfEvidence[evidenceCounter];
        if (evidence.source !== 'Copaal') {

        } else {
          console.log('************');
          console.log(evidence);
          const pathEvidences: CgTriple[] = [];
          const first = stringObject.subject;
          const last = stringObject.object;
          let splitSamples = evidence.sample.split(/\<(.*?)\>/);
          let samples : string[]=[];
          for(let j = 0 ; j < splitSamples.length ; j++){
            if(splitSamples[j].startsWith("http")){
              samples.push(splitSamples[j]);
            }
          }
          if (evidence.pathEvidences.length === 1) {

              pathEvidences.push(new CgTriple(first, evidence.pathEvidences[0].property.replace('<','').replace('>',''), last));
            piecesOfEvidence.push(new CgPath(pathEvidences, evidence.score, evidence.verbalization,evidence.sample, null));
          }

          if (evidence.pathEvidences.length === 2) {
            let tempNode = samples[0];
            let latestNode = '';
              pathEvidences.push(new CgTriple(first, evidence.pathEvidences[0].property.replace('<','').replace('>',''), tempNode));
              latestNode = tempNode;
              pathEvidences.push(new CgTriple(latestNode, evidence.pathEvidences[1].property.replace('<','').replace('>',''), last));
            piecesOfEvidence.push(new CgPath(pathEvidences, evidence.score, evidence.verbalization,evidence.sample, null));
          }

          if (evidence.pathEvidences.length === 3){
            let tempNode = samples[0];
            let latestNode = '';
              pathEvidences.push(new CgTriple(first, evidence.pathEvidences[0].property.replace('<','').replace('>',''), tempNode));
              latestNode = tempNode;

            tempNode = samples[1];

              pathEvidences.push(new CgTriple(latestNode, evidence.pathEvidences[1].property.replace('<','').replace('>',''), tempNode));
              latestNode = tempNode;


              pathEvidences.push(new CgTriple(latestNode, evidence.pathEvidences[2].property.replace('<','').replace('>',''), last));

            piecesOfEvidence.push(new CgPath(pathEvidences, evidence.score, evidence.verbalization,evidence.sample, null));
          }

          console.log('converted path evedences is : '+ pathEvidences);
        }
      }
      // this.graphData.finalJudgement = this.stringObject.vera;
      // this.piecesOfEvidence.push(new CgPath('dd', 0.2 , '' , 11 ));
      // this.piecesOfEvidence.push(new CgPath('cd', 0.9 , '' , 12 ));

      this.graphData.piecesOfEvidence = piecesOfEvidence;

      console.log('here the graph data is');
      console.log(this.graphData);

       this.facadeResult = jsonVal;
      // JSON.stringify(jsonVal);
      if ( stringObject.subject != null) {
        this.triple = stringObject.subject + ' ' + stringObject.predicate + ' ' + stringObject.object;
      } else {
        this.triple = 'waiting for data ...';
      }
      if ( stringObject.veracityScore != null) {
        this.veracityScore = stringObject.veracityScore;
      } else {
        this.veracityScore = 'waiting for data ...';
      }

      if ( stringObject.explanation != null ) {
        this.explanation = stringObject.explanation;
      } else {
        this.explanation = 'waiting for data ...';
      }
    });
    console.log('end retrieve');
  }
}
