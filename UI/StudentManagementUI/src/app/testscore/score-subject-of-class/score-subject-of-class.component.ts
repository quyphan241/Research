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
  enableEdit = false;
  enableEditIndex = null;
  scoreEditing = false;
  scoreEditingIndex = null;
  id_class: number;
  id_subject: number;
  scores: Observable<TestScore>;
  id_score=null;
  score: TestScore;
  id_student : any;
  firstScore : any;
  secondScore : any;
  finalScore : any;
  summaryScore : any;

  //add edit
  enableEditMethod(e: any, i: any, id_score: number, firstScore: number, secondScore: number, finalScore: number, summaryScore:number, id_student: number) {
    console.log(id_score, id_student);
    this.enableEdit = true;
    this.enableEditIndex = i;
    this.scoreEditing = true;
    this.scoreEditingIndex = i;
    this.id_score = id_score;
    this.id_student = id_student;
    this.score = new TestScore();
    this.testScoreService.getScore(this.id_score)
    .subscribe(data => {
      console.log(data)
      this.firstScore = data.firstScore;
      this.secondScore = data.secondScore;
      this.finalScore = data.finalScore;
      this.summaryScore = data.summaryScore;
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
  updateScore(){
    if(this.id_score!=null){
    this.score.id_subject = this.id_subject;
    this.score.id_student = this.id_student;
    this.score.firstScore = this.firstScore;
    this.score.secondScore = this.secondScore;
    this.score.finalScore = this.finalScore;
    this.score.summaryScore = this.summaryScore;
    this.testScoreService.updateTestScore(this.id_score, this.score)
      .subscribe(data => console.log(data), error => console.log(error));
    }
    else {
      this.score.id_student = this.id_student;
      this.score.id_subject = this.id_subject;
      this.score.firstScore = this.firstScore;
      this.score.secondScore = this.secondScore;
      this.score.finalScore = this.finalScore;
      this.score.summaryScore = this.summaryScore;
      this.testScoreService.createScore(this.score)
      .subscribe(data => console.log(data), error => console.log(error));
  }
  this.toList();
  }

  toList(){
    this.router.navigate(['scores/'+this.id_class+'/'+this.id_subject]);
  }
  
}
