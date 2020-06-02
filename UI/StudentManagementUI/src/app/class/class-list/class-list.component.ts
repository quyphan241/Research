import Swal from 'sweetalert2/dist/sweetalert2.js';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Class } from '../class';
import { Router } from '@angular/router';
import { ClassService } from '../class.service';

@Component({
  selector: 'app-class-list',
  templateUrl: './class-list.component.html',
  styleUrls: ['./class-list.component.css']
})
export class ClassListComponent implements OnInit {

  p:number = 1;

  classes: Observable<Class[]>
  
  constructor(private classService: ClassService,
    private router: Router ) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.classes = this.classService.getClassList();
  }

  deleteClass(id: number) {
    this.classService.deleteClass(id)
    .subscribe(
      data=> {
        console.log(data);
        this.reloadData();
      },
      error => console.log(error));
  }

  updateClass(id: number) {
    this.router.navigate(['classes/update', id]);
  }

  addClass() {
    this.router.navigate(['classes/add']);
  }
  
  classDetails(id : number){
    this.router.navigate(['classes/details', id]);
  }

  studyResult(id: number){
    this.router.navigate(['scores/'+id+'/1'])
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
      this.deleteClass(id);  
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
