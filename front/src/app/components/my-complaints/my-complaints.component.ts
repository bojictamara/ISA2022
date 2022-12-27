import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import ComplaintResponse from 'src/app/payload/responses/complaint.response';
import { ComplaintsService } from 'src/app/services/complaints.service';

@Component({
  selector: 'app-my-complaints',
  templateUrl: './my-complaints.component.html',
  styleUrls: ['./my-complaints.component.css']
})
export class MyComplaintsComponent implements OnInit {

  constructor(private complaintsService: ComplaintsService) {}

  complaints: ComplaintResponse[] = [];

  ngOnInit(): void {
    this.complaintsService.getMyComplaints().subscribe({
      next: data => {
        this.complaints = data;
      },
      error: (err: HttpErrorResponse) => {

      }
    })
  }

}
