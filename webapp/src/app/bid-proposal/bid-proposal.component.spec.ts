import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BidProposalComponent } from './bid-proposal.component';

describe('BidProposalComponent', () => {
  let component: BidProposalComponent;
  let fixture: ComponentFixture<BidProposalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BidProposalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BidProposalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
