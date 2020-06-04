import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import Swal from 'sweetalert2/dist/sweetalert2.js';


@Injectable({
  providedIn: 'root'
})

export class SemesterService {
  private ip = 'http://192.168.144.122';
  // private baseUrl = 'http://localhost:8080/semesters';
  private baseUrl = this.ip+':8080/semesters';


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
