import {EventEmitter, Injectable} from '@angular/core';
import {CgPath} from '../../model/cg-path';
import {CgData} from '../../model/cg-data';

@Injectable({
  providedIn: 'root'
})
export class EventProviderService {
  submitFormEvent = new EventEmitter<string>();
  submitTicketEvent = new EventEmitter<string>();
  pathClickEvent = new EventEmitter<number>();
  btnBackEvent = new EventEmitter<void>();
  refreshGraphEvent = new EventEmitter<void>();
  sendDetailEvent = new EventEmitter<CgPath[]>();
  updateDataEvent = new EventEmitter<CgData>();
  constructor() { }
}
