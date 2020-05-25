import { Component, OnInit } from '@angular/core';
import { Student } from '../student';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentService } from '../student.service';
import { TestScoreService } from 'src/app/testscore/test-score.service';
import { Observable } from 'rxjs';
import { TestScore } from 'src/app/testscore/testscore';

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrls: ['./student-details.component.css']
})
export class StudentDetailsComponent implements OnInit {

  id: number;
  student: Student;
  scores: Observable<TestScore>;

  constructor(private route: ActivatedRoute, private router: Router, private testScoreService: TestScoreService,
    private studentService: StudentService) { }

  ngOnInit() {
   this.reloadData();
   this.findAllScore();
  }

  reloadData() {
    this.student = new Student();
    this.id = this.route.snapshot.params['id'];
    this.studentService.getStudent(this.id)
    .subscribe(data => {
      console.log(data)
      this.student = data;
    }, error => console.log(error));
  }

  findAllScore() {
    this.id = this.route.snapshot.params['id'];
    this.scores = this.testScoreService.getScoreByIdStudent(this.id);
  }

  list(){
    this.router.navigate(['students']);
  }
}
