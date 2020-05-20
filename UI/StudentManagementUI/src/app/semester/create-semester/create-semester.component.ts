import { SemesterService } from '../semester.service';
import { Component, OnInit } from '@angular/core';
import { Semester } from '../semester';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-semester',
  templateUrl: './create-semester.component.html',
  styleUrls: ['./create-semester.component.css']
})
export class CreateSemesterComponent implements OnInit {

  semester: Semester = new Semester();
  submitted = false;

  constructor(private semesterService: SemesterService,
    private router: Router) { }

  ngOnInit() {
  }

  newSemester(): void {
    this.submitted = false;
    this.semester = new Semester();
  }

  save() {
    this.semesterService.createSemester(this.semester)
      .subscribe(data => console.log(data), error => console.log(error));
    this.semester = new Semester();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(['/semesters']);
  }
}
