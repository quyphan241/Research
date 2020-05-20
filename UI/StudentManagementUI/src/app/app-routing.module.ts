import { SemesterUpdateComponent } from './semester-update/semester-update.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SemesterListComponent } from './semester-list/semester-list.component';
import { CreateSemesterComponent } from './create-semester/create-semester.component';
import { SemesterDetailsComponent } from './semester-details/semester-details.component';


const routes: Routes = [
  { path: '', redirectTo:'semester', pathMatch:'full'},
  { path: 'semesters', component: SemesterListComponent },
  { path: 'add', component: CreateSemesterComponent },
  { path: 'update/:id', component: SemesterUpdateComponent },
  { path: 'details/:id', component: SemesterDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
