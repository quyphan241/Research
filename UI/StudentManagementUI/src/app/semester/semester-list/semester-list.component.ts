import { SemesterService } from '../semester.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Observable } from 'rxjs';
import { Semester } from '../semester';
import { Router } from '@angular/router';
import Swal from 'sweetalert2/dist/sweetalert2.js';


@Component({
  selector: 'app-semester-list',
  templateUrl: './semester-list.component.html',
  styleUrls: ['./semester-list.component.css']
})
export class SemesterListComponent implements OnInit {

  semesters: Observable<Semester[]>


  constructor(private semesterService: SemesterService,
    private router: Router ) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.semesters = this.semesterService.getSemestersList();
  }

  deleteSemester(id: number) {
    this.semesterService.deleteSemester(id)
    .subscribe(
      data=> {
        console.log(data);
        this.reloadData();
      },
      error => console.log(error));
  }

  updateSemester(id: number) {
    this.router.navigate(['semesters/update', id]);
  }
  
  addSemester() {
    this.router.navigate(['semesters/add']);
  }
  
  

  semesterDetails(id : number){
    this.router.navigate(['semesters/details', id]);
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
      this.deleteSemester(id);  
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


