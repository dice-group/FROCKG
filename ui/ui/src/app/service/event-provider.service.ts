import {EventEmitter, Injectable} from '@angular/core';
import {CgPath} from "../model/cg-path";

@Injectable({
  providedIn: 'root'
})
export class EventProviderService {
  pathClickEvent = new EventEmitter<number>();
  sendDetailEvent = new EventEmitter<CgPath[]>();
  constructor() { }
}
