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
  // add edit
  name = 'Angular';
  enableEdit = false;
  enableEditIndex = null;
  scoreEditing = false;
  scoreEditingIndex = null;
  id_class: number;
  id_subject: number;
  scores: Observable<TestScore>;
  id_score: number;
  score: TestScore;

  //add edit
  enableEditMethod(e: any, i: any, id_score: number) {
    console.log(id_score)
    this.enableEdit = true;
    this.enableEditIndex = i;
    this.scoreEditing = true;
    this.scoreEditingIndex = i;
    console.log(i, e);
    this.id_score = id_score;
    this.score = new TestScore();
    this.testScoreService.getScore(this.id_score)
    .subscribe(data => {
      console.log(data)
      this.score = data;
    }, error => console.log(error));
  }
  
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

  onSubmit() {
    this.updateScore();
  }

  updateScore() {

    this.testScoreService.updateTestScore(this.id_score, this.score)
    .subscribe(data => console.log(data), error => console.log(error));
  }

  list(){
    this.router.navigate(['students']);
  }
}
