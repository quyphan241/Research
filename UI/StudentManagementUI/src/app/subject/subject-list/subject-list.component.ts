import Swal from 'sweetalert2/dist/sweetalert2.js';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Subject } from '../subject';
import { SubjectService } from '../subject.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-subject-list',
  templateUrl: './subject-list.component.html',
  styleUrls: ['./subject-list.component.css']
})
export class SubjectListComponent implements OnInit {

  subjects: Observable<Subject[]>


  constructor(private subjectService: SubjectService,
    private router: Router ) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.subjects = this.subjectService.getSubjectsList();
    
  }

  deleteSubject(id: number) {
    this.subjectService.deleteSubject(id)
    .subscribe(
      data=> {
        console.log(data);
        this.reloadData();
      },
      error => console.log(error));
  }

  updateSubject(id: number) {
    this.router.navigate(['subjects/update', id]);
  }
  
  addSubject() {
    this.router.navigate(['subjects/add']);
  }
  
  

  subjectDetails(id : number){
    this.router.navigate(['subjects/details', id]);
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
      this.deleteSubject(id);  
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
