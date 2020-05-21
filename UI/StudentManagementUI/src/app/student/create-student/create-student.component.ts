import { Component, OnInit } from '@angular/core';
import { Student } from '../student';
import { Observable } from 'rxjs';
import { Class } from 'src/app/class/class';
import { StudentService } from '../student.service';
import { ClassService } from 'src/app/class/class.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-student',
  templateUrl: './create-student.component.html',
  styleUrls: ['./create-student.component.css']
})
export class CreateStudentComponent implements OnInit {

  student: Student = new Student();
  classes: Observable<Class[]>
  submitted = false;

  constructor(private studentService: StudentService,private classService: ClassService,
    private router: Router) { }

  ngOnInit() {
    this.reloadData(); 
  }

  reloadData() {
    this.classes = this.classService.getClassList();
  }

  newStudent(): void {
    this.submitted = false;
    this.student = new Student();
  }

  save() {
    this.studentService.createStudent(this.student)
      .subscribe(data => console.log(data), error => console.log(error));
    this.student = new Student();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(['/students']);
  }
}
