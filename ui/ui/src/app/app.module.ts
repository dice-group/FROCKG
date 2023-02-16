import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule , RoutingComponent} from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './component/home/home.component';
import { TicketComponent } from './component/ticket/ticket.component';
import { GraphComponent } from './component/graph/graph.component';
import { MonitorComponent } from './component/monitor/monitor.component';
import { ResultComponent } from './component/result/result.component';
import {ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    TicketComponent,
    RoutingComponent,
    GraphComponent,
    MonitorComponent,
    ResultComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
