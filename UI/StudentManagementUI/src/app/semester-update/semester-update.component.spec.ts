import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SemesterUpdateComponent } from './semester-update.component';

describe('SemesterUpdateComponent', () => {
  let component: SemesterUpdateComponent;
  let fixture: ComponentFixture<SemesterUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SemesterUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SemesterUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
