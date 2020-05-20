import { Subject } from '../subject';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SubjectService } from 'src/app/subject/subject.service';

@Component({
  selector: 'app-update-subject',
  templateUrl: './update-subject.component.html',
  styleUrls: ['./update-subject.component.css']
})
export class UpdateSubjectComponent implements OnInit {

  id: number;
  subject: Subject;

  constructor(private route: ActivatedRoute,private router: Router,
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

  updateSubject() {
    this.subjectService.updateSubject(this.id, this.subject)
      .subscribe(data => console.log(data), error => console.log(error));
    this.subject = new Subject();
    this.gotoList();
  }

  onSubmit() {
    this.updateSubject();    
  }

  gotoList() {
    this.router.navigate(['/subjects']);
  }

}
