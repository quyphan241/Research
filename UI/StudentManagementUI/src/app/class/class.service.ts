import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class ClassService {
  private ip = 'http://192.168.144.122';
  // private baseUrl = 'http://localhost:8080/classes';
  private baseUrl = this.ip+':8080/classes';


  constructor(private http: HttpClient) { }

  getClass(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`); 
  }

  createClass(_class: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, _class);
  } 

  updateClass(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteClass(id: number): Observable<any> {
    return this.http.put(`${this.baseUrl}/delete/${id}`, { responseType: 'text' });
  }

  getClassList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
