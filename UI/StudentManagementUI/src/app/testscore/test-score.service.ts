import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TestScoreService {

  private ip = 'http://192.168.144.122';
  // private testScoreUrl = 'http://localhost:8080/testScores';
  // private baseUrl = 'http://localhost:8080/scores';
  // private studentUrl = 'http://localhost:8080/students/';
  // private reportExcelUrl='http://localhost:8080/scores/report/';
  private testScoreUrl = this.ip+ ':8080/testScores';
  private baseUrl = this.ip +':8080/scores';
  private studentUrl = this.ip +':8080/students/';
  private reportExcelUrl= this.ip +':8080/scores/report/';

  constructor(private http: HttpClient) { }

  getScoreByIdStudent(id: number): Observable<any> {
    return this.http.get(`${this.studentUrl}/${id}/scores/`);
  }

  getScoreByIdClassAndIdSubject(id_class: number, id_subject: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id_class}/${id_subject}`);
  }  

  updateTestScore(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.testScoreUrl}/${id}`, value);
  }

  getScore(id: number): Observable<any> {
    return this.http.get(`${this.testScoreUrl}/${id}`); 
  }

  createScore(score: Object): Observable<Object> {
    return this.http.post(`${this.testScoreUrl}`, score);
  } 

  
}