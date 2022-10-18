import { Component, OnInit } from '@angular/core';
import { interval } from 'rxjs';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../environments/environment";



@Component({
  selector: 'app-monitor-connections',
  templateUrl: './monitor-connections.component.html',
  styleUrls: ['./monitor-connections.component.css']
})
export class MonitorConnectionsComponent implements OnInit {

  result;
  isCopaalConnected: boolean;
  isFactcheckConnected: boolean;

  constructor(private http : HttpClient) { }
  sub;
  ngOnInit() {
    this.updateMonitorSituations();

    this.sub = interval(10000)
      .subscribe((val) => {
        // check if the list
        this.updateMonitorSituations();
      });
  }

  updateMonitorSituations(){
    this.checkIfFactcheck();
    this.checkIfCopaalConnected();
    console.log("coppal "+this.isCopaalConnected);
    console.log("Factcheck "+this.isFactcheckConnected);
    if(this.isCopaalConnected==true){
      document.getElementById('isCopaalConnected').textContent="COPAAL is Connected";
    }else{
      if(this.isCopaalConnected==false){
        document.getElementById('isCopaalConnected').textContent="COPAAL is disconnected";
      }else{
        document.getElementById('isCopaalConnected').textContent="COPAAL situation is unknown";
      }}


    if(this.isFactcheckConnected==true){
      document.getElementById('isFactcheckConnected').textContent="FactCheck is Connected";
    }else{
      if(this.isFactcheckConnected==false){
        document.getElementById('isFactcheckConnected').textContent="FactCheck is disconnected";
      }else{
        document.getElementById('isFactcheckConnected').textContent="FactCheck situation is unknown";
      }}

  }

  checkIfCopaalConnected(){
    this.http.get<any>(environment.apiBase+'/copaaltest').subscribe((response) => {
      this.isCopaalConnected = true;
    },(error : HttpErrorResponse) =>{
      if(error.status == 200){
        this.isCopaalConnected = true;
      }else{
        this.isCopaalConnected = false;
      }
    });
  }

  checkIfFactcheck(){
    var url = environment.apiBase+'/factchecktest';
    console.log('url is :'+url)
    this.http.get<any>(url).subscribe((response) => {
      this.isFactcheckConnected = true;
      console.log(response);
    },(error : HttpErrorResponse) =>{
      console.log(error.message);
      console.log(error.error);
      if(error.status == 200){
        this.isFactcheckConnected = true;
      }else{
        this.isFactcheckConnected = false;
      }
    });
  }
}
