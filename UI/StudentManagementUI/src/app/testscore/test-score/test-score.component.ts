import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, Validators } from '@angular/forms';
import { TestScoreService } from '../test-score.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-test-score',
  templateUrl: './test-score.component.html',
  styleUrls: ['./test-score.component.css']
})
export class TestScoreComponent implements OnInit {

  testScoreForms: FormArray = this.fb.array([]);
  testScoreList = [];
  notification = null;
  id_class: number;
  id_subject: number;

  constructor(private route: ActivatedRoute,private router: Router,private fb: FormBuilder,
    private testScoreService: TestScoreService) { }

  ngOnInit() {
    this.id_class = this.route.snapshot.params['id_class'];   
    this.id_subject = this.route.snapshot.params['id_number'];   
    this.testScoreService.getScoreByIdClassAndIdSubject(this.id_class, this.id_subject)
      .subscribe(res => this.testScoreList = res as []);
    this.testScoreService.getScoreByIdClassAndIdSubject(this.id_class, this.id_subject).subscribe(
      res => {
        if (res == [])
          this.addBankAccountForm();
        else {
          //generate formarray as per the data received from BankAccont table
          (res as []).forEach((bankAccount: any) => {
            this.testScoreForms.push(this.fb.group({
              bankAccountID: [bankAccount.bankAccountID],
              accountNumber: [bankAccount.accountNumber, Validators.required],
              accountHolder: [bankAccount.accountHolder, Validators.required],
              bankID: [bankAccount.bankID, Validators.min(1)],
              IFSC: [bankAccount.ifsc, Validators.required]
            }));
          });
        }
      }
    );
  }

  addBankAccountForm() {
    this.bankAccountForms.push(this.fb.group({
      bankAccountID: [0],
      accountNumber: ['', Validators.required],
      accountHolder: ['', Validators.required],
      bankID: [0, Validators.min(1)],
      IFSC: ['', Validators.required]
    }));
  }

  recordSubmit(fg: FormGroup) {
    if (fg.value.bankAccountID == 0)
      this.service.postBankAccount(fg.value).subscribe(
        (res: any) => {
          fg.patchValue({ bankAccountID: res.bankAccountID });
          this.showNotification('insert');
        });
    else
      this.service.putBankAccount(fg.value).subscribe(
        (res: any) => {
          this.showNotification('update');
        });
  }

  onDelete(bankAccountID, i) {
    if (bankAccountID == 0)
      this.bankAccountForms.removeAt(i);
    else if (confirm('Are you sure to delete this record ?'))
      this.service.deleteBankAccount(bankAccountID).subscribe(
        res => {
          this.bankAccountForms.removeAt(i);
          this.showNotification('delete');
        });
  }

  showNotification(category) {
    switch (category) {
      case 'insert':
        this.notification = { class: 'text-success', message: 'saved!' };
        break;
      case 'update':
        this.notification = { class: 'text-primary', message: 'updated!' };
        break;
      case 'delete':
        this.notification = { class: 'text-danger', message: 'deleted!' };
        break;

      default:
        break;
    }
    setTimeout(() => {
      this.notification = null;
    }, 3000);
  }


}
