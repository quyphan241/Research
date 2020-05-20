import { SemesterService } from './../semester.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Semester } from '../semester';
import { error } from 'protractor';

@Component({
  selector: 'app-semester-details',
  templateUrl: './semester-details.component.html',
  styleUrls: ['./semester-details.component.css']
})
export class SemesterDetailsComponent implements OnInit {

  id: number;
  semester: Semester;

  constructor(private route: ActivatedRoute, private router: Router,
    private semesterService: SemesterService) { }

  ngOnInit() {
    this.semester = new Semester();
    this.id = this.route.snapshot.params['id'];
    this.semesterService.getSemester(this.id)
    .subscribe(data => {
      console.log(data)
      this.semester = data;
    }, error => console.log(error));
  }

  list(){
    this.router.navigate(['semesters']);
  }
}
