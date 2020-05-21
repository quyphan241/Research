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
      this.deleteClass(id);  
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
