import { Component, OnInit } from '@angular/core';
import { Class } from '../class';
import { ActivatedRoute, Router } from '@angular/router';
import { ClassService } from '../class.service';

@Component({
  selector: 'app-class-details',
  templateUrl: './class-details.component.html',
  styleUrls: ['./class-details.component.css']
})
export class ClassDetailsComponent implements OnInit {

  id: number;
  class: Class;

  constructor(private route: ActivatedRoute, private router: Router,
    private classService: ClassService) { }

  ngOnInit() {
    this.class = new Class();
    this.id = this.route.snapshot.params['id'];
    this.classService.getClass(this.id)
    .subscribe(data => {
      console.log(data)
      this.class = data;
    }, error => console.log(error));
  }

  list(){
    this.router.navigate(['classes']);
  }

}
