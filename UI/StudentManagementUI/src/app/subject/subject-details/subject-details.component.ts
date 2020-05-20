import { Subject } from './../subject';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SubjectService } from '../subject.service';

@Component({
  selector: 'app-subject-details',
  templateUrl: './subject-details.component.html',
  styleUrls: ['./subject-details.component.css']
})
export class SubjectDetailsComponent implements OnInit {

  id: number;
  subject: Subject;

  constructor(private route: ActivatedRoute, private router: Router,
    private subjectService: SubjectService) { }

  ngOnInit() {
    this.subject = new Subject();
    this.id = this.route.snapshot.params['id'];
    this.subjectService.getSubject(this.id)
    .subscribe(data => {
      console.log(data)
      this.subject = data;
    }, error => console.log(error));
  }

  list(){
    this.router.navigate(['subjects']);
  }

}
