import { CreateStudentComponent } from './student/create-student/create-student.component';
import { UpdateClassComponent } from './class/update-class/update-class.component';
import { CreateClassComponent } from './class/create-class/create-class.component';
import { ClassListComponent } from './class/class-list/class-list.component';
import { UpdateSubjectComponent } from './subject/update-subject/update-subject.component';
import { CreateSubjectComponent } from './subject/create-subject/create-subject.component';
import { SubjectListComponent } from './subject/subject-list/subject-list.component';
import { SemesterUpdateComponent } from './semester/semester-update/semester-update.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SemesterListComponent } from './semester/semester-list/semester-list.component';
import { CreateSemesterComponent } from './semester/create-semester/create-semester.component';
import { SemesterDetailsComponent } from './semester/semester-details/semester-details.component';
import { SubjectDetailsComponent } from './subject/subject-details/subject-details.component';
import { ClassDetailsComponent } from './class/class-details/class-details.component';
import { StudentListComponent } from './student/student-list/student-list.component';
import { UpdateStudentComponent } from './student/update-student/update-student.component';


const routes: Routes = [
  { path: '', redirectTo:'semesters', pathMatch:'full'},
  { path: 'semesters', component: SemesterListComponent },
  { path: 'semesters/add', component: CreateSemesterComponent },
  { path: 'semesters/update/:id', component: SemesterUpdateComponent },
  { path: 'semesters/details/:id', component: SemesterDetailsComponent },
  { path: 'subjects', component: SubjectListComponent},
  { path: 'subjects/add', component: CreateSubjectComponent},
  { path: 'subjects/update/:id', component: UpdateSubjectComponent },
  { path: 'subjects/details/:id', component: SubjectDetailsComponent },
  { path: 'classes', component: ClassListComponent},
  { path: 'classes/add', component: CreateClassComponent},
  { path: 'classes/update/:id', component: UpdateClassComponent },
  { path: 'classes/details/:id', component: ClassDetailsComponent },
  { path: 'students', component: StudentListComponent },
  { path: 'students/add', component: CreateStudentComponent },  
  { path: 'students/update/:id', component: UpdateStudentComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
