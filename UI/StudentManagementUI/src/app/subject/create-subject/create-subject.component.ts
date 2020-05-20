import { SubjectService } from '../subject.service';
import { Subject } from '../subject';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-subject',
  templateUrl: './create-subject.component.html',
  styleUrls: ['./create-subject.component.css']
})
export class CreateSubjectComponent implements OnInit {

  subject: Subject = new Subject();
  submitted = false;

  constructor(private subjectService: SubjectService,
    private router: Router) { }

  ngOnInit() {
  }

  newsubject(): void {
    this.submitted = false;
    this.subject = new Subject();
  }

  save() {
    this.subjectService.createSubject(this.subject)
      .subscribe(data => console.log(data), error => console.log(error));
    this.subject = new Subject();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(['/subjects']);
  }

}
