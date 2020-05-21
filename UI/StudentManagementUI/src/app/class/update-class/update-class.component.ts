import { Component, OnInit } from '@angular/core';
import { Class } from '../class';
import { ClassService } from '../class.service';
import { ActivatedRoute, Router } from '@angular/router';
import { SemesterService } from 'src/app/semester/semester.service';
import { Observable } from 'rxjs';
import { Semester } from 'src/app/semester/semester';

@Component({
  selector: 'app-update-class',
  templateUrl: './update-class.component.html',
  styleUrls: ['./update-class.component.css']
})
export class UpdateClassComponent implements OnInit {

  id: number;
  class: Class;
  semesters: Observable<Semester[]>


  constructor(private route: ActivatedRoute,private router: Router, private semesterService: SemesterService,
    private classService: ClassService) { }

 ngOnInit() {
    this.semesters = this.semesterService.getSemestersList();
    this.class = new Class();
    this.id = this.route.snapshot.params['id'];   
    this.classService.getClass(this.id)
      .subscribe(data => {
        console.log(data)
        this.class = data;
      }, error => console.log(error));
  }

  updateClass() {
    this.classService.updateClass(this.id, this.class)
      .subscribe(data => console.log(data), error => console.log(error));
    this.class = new Class();
    this.gotoList();
  }

  onSubmit() {
    this.updateClass();    
  }

  gotoList() {
    this.router.navigate(['/classes']);
  }

}
