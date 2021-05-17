import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateProposalComponent } from './update-proposal.component';

describe('UpdateProposalComponent', () => {
  let component: UpdateProposalComponent;
  let fixture: ComponentFixture<UpdateProposalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateProposalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateProposalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
