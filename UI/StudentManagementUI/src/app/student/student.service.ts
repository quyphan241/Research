import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  private baseUrl = 'http://localhost:8080/students';
  private getStudentByIdUrl = 'http://localhost:8080/classes/';


  constructor(private http: HttpClient) { }

  getStudent(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`); 
  }

  createStudent(student: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, student);
  } 

  updateStudent(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteStudent(id: number): Observable<any> {
    return this.http.put(`${this.baseUrl}/delete/${id}`, { responseType: 'text' });
  }

  getStudentList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getStudenstByIdClass(id: number ): Observable<any> {
    return this.http.get(`${this.getStudentByIdUrl}/${id}/students/`);
  }

  getScoreByIdStudent(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}/scores/`);
  }

}
