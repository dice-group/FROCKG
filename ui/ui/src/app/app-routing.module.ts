import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from "./component/home/home.component";
import {TicketComponent} from "./component/ticket/ticket.component";
import {ResultComponent} from "./component/result/result.component";

const routes: Routes = [
  {path:"home", component:HomeComponent} ,
  {path:"ticket", component:TicketComponent},
  {path:"result", component:ResultComponent},
  { path: '**', redirectTo: 'home'}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const RoutingComponent = [HomeComponent,TicketComponent,ResultComponent];
