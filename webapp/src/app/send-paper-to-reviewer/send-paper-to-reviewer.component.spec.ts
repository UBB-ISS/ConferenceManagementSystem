import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SendPaperToReviewerComponent } from './send-paper-to-reviewer.component';

describe('SendPaperToReviewerComponent', () => {
  let component: SendPaperToReviewerComponent;
  let fixture: ComponentFixture<SendPaperToReviewerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SendPaperToReviewerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SendPaperToReviewerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
