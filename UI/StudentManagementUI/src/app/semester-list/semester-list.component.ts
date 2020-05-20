import { SemesterService } from './../semester.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Semester } from '../semester';
import { Router } from '@angular/router';

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
    this.router.navigate(['update', id]);
  }

  semesterDetails(id : number){
    this.router.navigate(['details', id]);
  }
}


