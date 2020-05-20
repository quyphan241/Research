import { SemesterService } from './../semester.service';
import { Component, OnInit } from '@angular/core';
import { Semester } from '../semester';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-semester-update',
  templateUrl: './semester-update.component.html',
  styleUrls: ['./semester-update.component.css']
})
export class SemesterUpdateComponent implements OnInit {
  id: number;
  semester: Semester;

  constructor(private route: ActivatedRoute,private router: Router,
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

  updateSemester() {
    this.semesterService.updateSemester(this.id, this.semester)
      .subscribe(data => console.log(data), error => console.log(error));
    this.semester = new Semester();
    this.gotoList();
  }

  onSubmit() {
    this.updateSemester();    
  }

  gotoList() {
    this.router.navigate(['/semesters']);
  }

}
