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

  students: Observable<Student[]>
  
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
      title: 'Are you sure?',
      text: 'You will not be able to recover this imaginary file!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, delete it!',
      cancelButtonText: 'No, keep it'
    }).then((result) => {
      if (result.value) {
      this.deleteStudent(id);  
      Swal.fire(
        'Deleted!',
        'Your imaginary file has been deleted.',
        'success'
      )
      } else if (result.dismiss === Swal.DismissReason.cancel) {
      Swal.fire(
        'Cancelled',
        'Your imaginary file is safe :)',
        'error'
      )
      }
    })
  }

}
