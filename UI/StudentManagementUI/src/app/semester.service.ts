import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class SemesterService {
  private baseUrl = 'http://localhost:8080/semesters';

  constructor(private http: HttpClient) { }

  getSemester(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`); 
  }

  createSemester(semester: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, semester);
  } 

  updateSemester(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteSemester(id: number): Observable<any> {
    return this.http.put(`${this.baseUrl}/delete/${id}`, { responseType: 'text' });
  }

  getSemestersList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
  

}
