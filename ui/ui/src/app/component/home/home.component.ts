import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {CgTriple} from "../../model/cg-triple";
import {RestService} from "../../service/rest/rest.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  complexForm: FormGroup;
  subjectFc: FormControl;
  propertyFc: FormControl;
  objectFc: FormControl;

  public exampleArr: CgTriple[];

  public predicates: String[];
  constructor(public restService: RestService, fb: FormBuilder, private router: Router) {
    this.predicates = [];
    this.predicates.push("starring");
    this.predicates.push("birthPlace");
    this.predicates.push("award");
    this.predicates.push("deathPlace");
    this.predicates.push("subsidiary");
    this.predicates.push("publication");
    this.predicates.push("spouse");
    this.predicates.push("foundation");
    this.predicates.push("affiliation");
    this.predicates.push("chancellor");
    this.predicates.push("city");
    this.predicates.push("director");
    this.predicates.push("producer");
    this.predicates.push("productionCompany");
    this.predicates.push("academicDiscipline");
    this.predicates.push("writer");
    this.predicates.push("nationality");

    this.exampleArr = this.insertSamples();

    this.subjectFc = new FormControl(this.exampleArr[0].subject, Validators.required);
    this.propertyFc = new FormControl(this.exampleArr[0].property, Validators.required);
    this.objectFc = new FormControl(this.exampleArr[0].object, Validators.required);

    this.complexForm = fb.group({
      'subject' : this.subjectFc,
      'predicate': this.propertyFc,
      'object' : this.objectFc
    });
    this.complexForm.patchValue({'subject': this.exampleArr[0].subject , 'predicate': 0 , 'object': this.exampleArr[0].object});
  }

  ngOnInit(): void {
  }

  insertSamples() {
    let temp: CgTriple[]=[];
    let exObj: CgTriple = new CgTriple('Kill_Bill',
      'starring', 'David_Carradine');
    temp.push(exObj);

    exObj = new CgTriple('Predator_(film)',
      'starring', 'Carl_Weathers');
    temp.push(exObj);

    exObj = new CgTriple('Frank_Zappa',
      'birthPlace', 'Baltimore');
    temp.push(exObj);

    exObj = new CgTriple('Robert_Andrews_Millikan',
      'award', 'Nobel_Prize_in_Physics');
    temp.push(exObj);

    exObj = new CgTriple('Richard_Rodgers',
      'deathPlace', 'New_York_City');
    temp.push(exObj);

    exObj = new CgTriple('BGI_Group',
      'subsidiary', 'Complete_Genomics');
    temp.push(exObj);

    exObj = new CgTriple('Krista_Allen',
      'spouse', 'Mams_Taylor');
    temp.push(exObj);

    exObj = new CgTriple('University_of_Queensland',
      'affiliation', 'Washington_University_in_St._Louis');
    temp.push(exObj);


    exObj = new CgTriple('Pori_(film)',
      'writer', 'Subramaniam_Siva');
    temp.push(exObj);

    return temp;
  }

  submitForm(value: any): void {
    value.subject = "http://dbpedia.org/resource/"+value.subject;
    value.predicate = "http://dbpedia.org/ontology/"+this.predicates[parseInt(value.predicate)];
    value.object = "http://dbpedia.org/resource/"+value.object;
    console.log('Start factchecking' + value.predicate);
    this.restService.getRequest('/requestFactChecking', value).subscribe((jsonVal) => {
      console.log('factchecking result is here ');
      console.log(jsonVal);
      // redirect to
      this.router.navigateByUrl('/ticket?id='+jsonVal.taskId);
    });
    console.log('end factchecking');
  }

  onOptionsSelected(value: string) {
    const curSel: CgTriple = this.exampleArr[value];
    this.subjectFc.setValue(curSel.subject);
    this.propertyFc.setValue(curSel.property);
    this.objectFc.setValue(curSel.object);
    // tslint:disable-next-line:max-line-length
    this.complexForm.patchValue({'subject': curSel.subject , 'predicate': this.predicates.findIndex(p => p==curSel.property) , 'object': curSel.object});
  }
}
