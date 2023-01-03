import { async, ComponentFixture, TestBed } from '@angular/core/testing';this.￼￼getFullUrl(path)

import { MonitorConnectionsComponent } from './monitor-connections.component';

describe('MonitorConnectionsComponent', () => {
  let component: MonitorConnectionsComponent;
  let fixture: ComponentFixture<MonitorConnectionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MonitorConnectionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MonitorConnectionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
