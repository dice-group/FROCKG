import {EventEmitter, Injectable} from '@angular/core';
import {CgPath} from '../../model/cg-path';
import {CgData} from '../../model/cg-data';

@Injectable({
  providedIn: 'root'
})
export class EventProviderService {
  submitFormEvent = new EventEmitter<string>();
  submitTicketEvent = new EventEmitter<string>();
  btnBackEvent = new EventEmitter<void>();
  //updateDataEvent = new EventEmitter<CgData>();
  constructor() { }
}
