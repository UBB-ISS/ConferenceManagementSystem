import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RolesForSelectedConferenceComponent } from './roles-for-selected-conference.component';

describe('RolesForSelectedConferenceComponent', () => {
  let component: RolesForSelectedConferenceComponent;
  let fixture: ComponentFixture<RolesForSelectedConferenceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RolesForSelectedConferenceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RolesForSelectedConferenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
