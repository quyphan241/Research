import { Component, OnInit } from '@angular/core';
import { Class } from '../class';
import { ActivatedRoute, Router } from '@angular/router';
import { ClassService } from '../class.service';
import { Student } from 'src/app/student/student';
import { Observable } from 'rxjs';
import { StudentService } from 'src/app/student/student.service';

@Component({
  selector: 'app-class-details',
  templateUrl: './class-details.component.html',
  styleUrls: ['./class-details.component.css']
})
export class ClassDetailsComponent implements OnInit {

  id: number;
  class: Class;
  students: Observable<Student[]>;

  constructor(private route: ActivatedRoute, private router: Router, private studentService: StudentService,
    private classService: ClassService) { }

  ngOnInit() {
    this.class = new Class();
    this.id = this.route.snapshot.params['id'];
    this.students = this.studentService.getStudenstByIdClass(this.id);
    this.classService.getClass(this.id)
    .subscribe(data => {
      console.log(data)
      this.class = data;
    }, error => console.log(error));
  }

  list(){
    this.router.navigate(['classes']);
  }

  studentDetails(id : number){
    this.router.navigate(['students/details', id]);
  }

  addStudent() {
    this.router.navigate(['students/add']);
  }

}
