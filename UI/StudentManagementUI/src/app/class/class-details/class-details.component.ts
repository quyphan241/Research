import { Component, OnInit } from '@angular/core';
import { Class } from '../class';
import { ActivatedRoute, Router } from '@angular/router';
import { ClassService } from '../class.service';
import { Student } from 'src/app/student/student';
import { Observable } from 'rxjs';
import { StudentService } from 'src/app/student/student.service';
import Swal from 'sweetalert2/dist/sweetalert2.js';

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

  reloadData() {
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

  studyResult(id: number){
    this.router.navigate(['scores/'+id+'/1'])
  }

  updateStudent(id: number) {
    this.router.navigate(['students/update', id]);
  }

  deleteStudent(id: number) {
    this.studentService.deleteStudent(id)
    .subscribe(
      data=> {
        console.log(data);
        this.reloadData();
      },
      error => console.log(error));
  }

  opensweetalert()
  {
    Swal.fire({
        text: 'Hello!',
        icon: 'success'
      });
  }

  opensweetalertdng()
  {
   Swal.fire('Oops...', 'Something went wrong!', 'error')
  }
  
  opensweetalertcst(id: number){
    Swal.fire({
      title: 'Bạn chắc chắn chưa?',
      text: 'Bạn sẽ không thể khôi phục hành động này',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Xác nhận',
      cancelButtonText: 'Hủy bỏ'
    }).then((result) => {
      if (result.value) {
      this.deleteStudent(id);  
      Swal.fire(
        'Đã xóa!',
        '',
        'success'
      )
      } else if (result.dismiss === Swal.DismissReason.cancel) {
      Swal.fire(
        'Đã hủy bỏ',
        '',
        'error'
      )
      }
    })
  }
}
