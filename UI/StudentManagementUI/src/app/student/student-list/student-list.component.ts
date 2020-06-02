import Swal from 'sweetalert2/dist/sweetalert2.js';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from '../student';
import { StudentService } from '../student.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {
  
  students: Observable<Student[]>;
  p: number = 1;
  constructor(private studentService: StudentService,
    private router: Router ) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.students = this.studentService.getStudentList();
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

  updateStudent(id: number) {
    this.router.navigate(['students/update', id]);
  }
  addStudent() {
    this.router.navigate(['students/add']);
  }
  
  studentDetails(id : number){
    this.router.navigate(['students/details', id]);
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
