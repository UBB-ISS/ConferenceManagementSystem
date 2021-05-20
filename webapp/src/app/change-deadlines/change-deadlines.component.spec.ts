import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeDeadlinesComponent } from './change-deadlines.component';

describe('ChangeDeadlinesComponent', () => {
  let component: ChangeDeadlinesComponent;
  let fixture: ComponentFixture<ChangeDeadlinesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeDeadlinesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeDeadlinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
