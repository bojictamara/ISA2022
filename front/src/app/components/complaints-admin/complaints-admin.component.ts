import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit} from '@angular/core';
import ComplaintResponse from 'src/app/payload/responses/complaint.response';
import { ComplaintsService } from 'src/app/services/complaints.service';

@Component({
  selector: 'app-complaints-admin',
  templateUrl: './complaints-admin.component.html',
  styleUrls: ['./complaints-admin.component.css']
})
export class ComplaintsAdminComponent implements OnInit {

  constructor(private complaintsService: ComplaintsService) {}

  complaints: ComplaintResponse[] = [];

  ngOnInit(): void {
    this.complaintsService.getAdminComplaints().subscribe({
      next: data => {
        this.complaints = data;
      },
      error: (err: HttpErrorResponse) => {

      }
    })
  }
}
