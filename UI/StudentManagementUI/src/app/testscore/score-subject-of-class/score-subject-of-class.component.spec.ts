import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ScoreSubjectOfClassComponent } from './score-subject-of-class.component';

describe('ScoreSubjectOfClassComponent', () => {
  let component: ScoreSubjectOfClassComponent;
  let fixture: ComponentFixture<ScoreSubjectOfClassComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ScoreSubjectOfClassComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ScoreSubjectOfClassComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
