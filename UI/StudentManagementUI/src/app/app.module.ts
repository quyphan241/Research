import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, FormGroup } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateSemesterComponent } from './semester/create-semester/create-semester.component';
import { SemesterDetailsComponent } from './semester/semester-details/semester-details.component';
import { SemesterListComponent } from './semester/semester-list/semester-list.component';
import { SemesterUpdateComponent } from './semester/semester-update/semester-update.component';
import { HttpClientModule } from '@angular/common/http';
import { MenuComponent } from './menu/menu.component';
import { DataTablesModule } from 'angular-datatables';
import { CreateSubjectComponent } from './subject/create-subject/create-subject.component';
import { SubjectDetailsComponent } from './subject/subject-details/subject-details.component';
import { SubjectListComponent } from './subject/subject-list/subject-list.component';
import { UpdateSubjectComponent } from './subject/update-subject/update-subject.component';
import { CreateClassComponent } from './class/create-class/create-class.component';
import { ClassDetailsComponent } from './class/class-details/class-details.component';
import { ClassListComponent } from './class/class-list/class-list.component';
import { UpdateClassComponent } from './class/update-class/update-class.component';
import { CreateStudentComponent } from './student/create-student/create-student.component';
import { StudentDetailsComponent } from './student/student-details/student-details.component';
import { UpdateStudentComponent } from './student/update-student/update-student.component';
import { StudentListComponent } from './student/student-list/student-list.component';
import { ScoreSubjectOfClassComponent } from './testscore/score-subject-of-class/score-subject-of-class.component';
import { TestScoreComponent } from './testscore/test-score/test-score.component';



@NgModule({
  declarations: [
    AppComponent,
    CreateSemesterComponent,
    SemesterDetailsComponent,
    SemesterListComponent,
    SemesterUpdateComponent,

    MenuComponent,

    CreateSubjectComponent,
    SubjectDetailsComponent,
    SubjectListComponent,
    UpdateSubjectComponent,

    CreateClassComponent,
    ClassDetailsComponent,
    ClassListComponent,
    UpdateClassComponent,

    CreateStudentComponent,
    StudentDetailsComponent,
    UpdateStudentComponent,
    StudentListComponent,
    
    ScoreSubjectOfClassComponent,
    
    TestScoreComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    DataTablesModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
