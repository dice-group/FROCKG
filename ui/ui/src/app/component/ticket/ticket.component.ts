import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.css']
})
export class TicketComponent implements OnInit {

  ticketId: string;
  ticketURL: string;
  constructor(private router: Router,private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParams
      .subscribe(params => {
        console.log(params);
        this.ticketId = params.id;
        console.log(this.ticketId);
        this.ticketURL = environment.currentHost+'/result?id='+this.ticketId;
        console.log(this.ticketURL);
      });
  }

  showForm() {
    this.router.navigateByUrl('/home');
  }

  showResult() {
    this.router.navigateByUrl('/result?id='+this.ticketId);
  }
}
