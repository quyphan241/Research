import { Class } from './../../class/class';
import { Component, OnInit } from '@angular/core';
import { Student } from '../student';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { SemesterService } from 'src/app/semester/semester.service';
import { StudentService } from '../student.service';
import { ClassService } from 'src/app/class/class.service';

@Component({
  selector: 'app-update-student',
  templateUrl: './update-student.component.html',
  styleUrls: ['./update-student.component.css']
})
export class UpdateStudentComponent implements OnInit {

  id: number;
  student: Student;
  classes: Observable<Class[]>


  constructor(private route: ActivatedRoute,private router: Router, private classService: ClassService,
    private studentService: StudentService) { }

 ngOnInit() {
    this.classes = this.classService.getClassList();
    this.student = new Student();
    this.id = this.route.snapshot.params['id'];   
    this.studentService.getStudent(this.id)
      .subscribe(data => {
        console.log(data)
        this.student = data;
      }, error => console.log(error));
  }

  updateStudent() {
    this.studentService.updateStudent(this.id, this.student)
      .subscribe(data => console.log(data), error => console.log(error));
    this.student = new Student();
    this.gotoList();
  }

  onSubmit() {
    this.updateStudent();    
  }

  gotoList() {
    this.router.navigate(['/students']);
  }

}
