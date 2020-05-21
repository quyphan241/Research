import { Component, OnInit } from '@angular/core';
import { Class } from '../class';
import { ClassService } from '../class.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Semester } from 'src/app/semester/semester';
import { SemesterService } from 'src/app/semester/semester.service';

@Component({
  selector: 'app-create-class',
  templateUrl: './create-class.component.html',
  styleUrls: ['./create-class.component.css']
})
export class CreateClassComponent implements OnInit {
  class: Class = new Class();
  semesters: Observable<Semester[]>
  submitted = false;

  constructor(private classService: ClassService,private semesterService: SemesterService,
    private router: Router) { }

  ngOnInit() {
    this.reloadData(); 
  }

  reloadData() {
    this.semesters = this.semesterService.getSemestersList();
  }

  newClass(): void {
    this.submitted = false;
    this.class = new Class();
  }

  save() {
    this.classService.createClass(this.class)
      .subscribe(data => console.log(data), error => console.log(error));
    this.class = new Class();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(['/classes']);
  }

}
