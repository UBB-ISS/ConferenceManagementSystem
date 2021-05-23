import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewAssignedPapersComponent } from './review-assigned-papers.component';

describe('ReviewAssignedPapersComponent', () => {
  let component: ReviewAssignedPapersComponent;
  let fixture: ComponentFixture<ReviewAssignedPapersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReviewAssignedPapersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewAssignedPapersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
