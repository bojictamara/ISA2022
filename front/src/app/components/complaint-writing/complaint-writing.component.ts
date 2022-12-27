import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import CenterResponse from 'src/app/payload/responses/center-response';
import UserBasicInfoResponse from 'src/app/payload/responses/user-basic-info.response';
import { CentersService } from 'src/app/services/centers.service';
import { ComplaintsService } from 'src/app/services/complaints.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-complaint-writing',
  templateUrl: './complaint-writing.component.html',
  styleUrls: ['./complaint-writing.component.css']
})
export class ComplaintWritingComponent implements OnInit {

  constructor(private centerService: CentersService, private userService: UserService, private complaintService: ComplaintsService,
    private router: Router, private tostr: ToastrService ) {}

  employees: UserBasicInfoResponse[] = [];
  centers: CenterResponse[] = [];

  selectedEmployee?: number;
  selectedCenter?: number;

  errorMessage = "";

  complaintText = "";

  ngOnInit(): void {

    this.centerService.getCenters().subscribe({
      next: data => {
        this.centers = data;
      },
      error: (err: HttpErrorResponse) => {
        this.errorMessage = err.error;
      }
    })

    this.userService.getEmployees().subscribe({
      next: data => {
        this.employees = data;
      },
      error: (err: HttpErrorResponse) => {
        this.errorMessage = err.error;
      }
    })
  }

  saveComplaint() {
    if (!this.selectedCenter && !this.selectedEmployee) {
      this.errorMessage = "Morate da izaberete ili centar ili ranika";
      return;
    }


    if (this.selectedCenter && this.selectedEmployee) {
      this.errorMessage = "Morate da izaberete ili centar ili ranika";
      return;
    }

    if (!this.complaintText) {
      this.errorMessage = "Tekst zalbe je obavezno polje";
      return;
    }

    this.complaintService.saveComplaint({
      centerId: this.selectedCenter,
      medicalWorkerId: this.selectedEmployee,
      text: this.complaintText
    }).subscribe({
      next: data => {
        this.router.navigate(['/my-complaints']);
        this.tostr.success("Uspenso sacuvano", "Vasa zalba je zabelezena. Hvala sto ste ostavili utisak!")
      },
      error: (err: HttpErrorResponse) => {
        this.errorMessage = err.error;
      }
    })
  }

}
