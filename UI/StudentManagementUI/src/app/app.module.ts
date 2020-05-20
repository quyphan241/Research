import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateSemesterComponent } from './create-semester/create-semester.component';
import { SemesterDetailsComponent } from './semester-details/semester-details.component';
import { SemesterListComponent } from './semester-list/semester-list.component';
import { SemesterUpdateComponent } from './semester-update/semester-update.component';
import { HttpClientModule } from '@angular/common/http';
import { MenuComponent } from './menu/menu.component';

@NgModule({
  declarations: [
    AppComponent,
    CreateSemesterComponent,
    SemesterDetailsComponent,
    SemesterListComponent,
    SemesterUpdateComponent,
    MenuComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
