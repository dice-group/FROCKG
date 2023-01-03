import {Component, EventEmitter, OnInit} from '@angular/core';
import {EventProviderService} from '../../service/event/event-provider.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {RestService} from '../../service/rest/rest.service';
import {CgTriple} from '../../model/cg-triple';
import {MatDialog, MatSelectChange} from '@angular/material';
import {HelpDescComponent} from '../help-desc/help-desc.component';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {
  complexForm: FormGroup;
  subjectFc: FormControl;
  propertyFc: FormControl;
  objectFc: FormControl;
  ticketIdFC: FormControl;

  public showBar = false;

  public exampleArr: CgTriple[];


  constructor(public eventService: EventProviderService, public restService: RestService, fb: FormBuilder, public dialog: MatDialog) {
    this.exampleArr = [];
    let exObj: CgTriple = new CgTriple('http://dbpedia.org/resource/Barack_Obama',
      'http://dbpedia.org/ontology/nationality', 'http://dbpedia.org/resource/United_States');
    this.exampleArr.push(exObj);
    exObj = new CgTriple('http://dbpedia.org/resource/Pori_(film)',
      'http://dbpedia.org/ontology/writer', 'http://dbpedia.org/resource/Subramaniam_Siva');
    this.exampleArr.push(exObj);

    exObj = new CgTriple('http://dbpedia.org/resource/Samuel_Dennis',
      'http://dbpedia.org/ontology/nationality', 'http://dbpedia.org/resource/Australia');
    this.exampleArr.push(exObj);

    this.subjectFc = new FormControl(this.exampleArr[0].subject, Validators.required);
    this.propertyFc = new FormControl(this.exampleArr[0].property, Validators.required);
    this.objectFc = new FormControl(this.exampleArr[0].object, Validators.required);
    this.complexForm = fb.group({
      'subject' : this.subjectFc,
      'predicate': this.propertyFc,
      'object' : this.objectFc
    });
    // tslint:disable-next-line:max-line-length
    this.complexForm.patchValue({'subject': this.exampleArr[0].subject , 'predicate': this.exampleArr[0].property , 'object': this.exampleArr[0].object});
  }


  ngOnInit() {
    this.restService.requestEvnt.subscribe(val => { this.toggleProgressBar(val); });
  }

  toggleProgressBar(showBar) {
    this.showBar = showBar;
  }


  submitForm(value: any): void {
    console.log('Start factchecking' + value);
    this.restService.getRequest('/requestFactChecking', value).subscribe((jsonVal) => {
      console.log('factchecking result is here ');
      console.log(jsonVal);
      this.eventService.submitFormEvent.emit( jsonVal.taskId);
    });
    console.log('end factchecking');
  }

  onOptionsSelected(value: string) {
    const curSel: CgTriple = this.exampleArr[value];
    this.subjectFc.setValue(curSel.subject);
    this.propertyFc.setValue(curSel.property);
    this.objectFc.setValue(curSel.object);
    // tslint:disable-next-line:max-line-length
    this.complexForm.patchValue({'subject': curSel.subject , 'predicate': curSel.property , 'object': curSel.object});
  }
  openHelpPopup() {
    this.dialog.open(HelpDescComponent);
  }
  retrieveResultByTicketId() {
    const ticketID = (document.getElementById('ticketIdForRetrieve') as HTMLInputElement).value;
    console.log(ticketID);
    this.eventService.submitTicketEvent.emit(ticketID);
  }
}
