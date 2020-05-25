import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { TestScore } from '../testscore';
import { ActivatedRoute, Router } from '@angular/router';
import { TestScoreService } from '../test-score.service';

@Component({
  selector: 'app-score-subject-of-class',
  templateUrl: './score-subject-of-class.component.html',
  styleUrls: ['./score-subject-of-class.component.css']
})
export class ScoreSubjectOfClassComponent implements OnInit {

  id_class: number;
  id_subject: number;
  scores: Observable<TestScore>;

  constructor(private route: ActivatedRoute, private router: Router, private testScoreService: TestScoreService) { 
  }

  ngOnInit() {
   this.reloadData();
  }

  reloadData() {
    this.id_class = this.route.snapshot.params['id_class'];
    this.id_subject = this.route.snapshot.params['id_subject'];
    this.scores = this.testScoreService.getScoreByIdClassAndIdSubject(this.id_class, this.id_subject);
  }

  list(){
    this.router.navigate(['students']);
  }
}
