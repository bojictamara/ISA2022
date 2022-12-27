import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import ComplaintResponse from 'src/app/payload/responses/complaint.response';
import { ComplaintsService } from 'src/app/services/complaints.service';

@Component({
  selector: 'app-complaint-answer',
  templateUrl: './complaint-answer.component.html',
  styleUrls: ['./complaint-answer.component.css']
})
export class ComplaintAnswerComponent implements OnInit {

  constructor(private complaintsService: ComplaintsService, private toastr: ToastrService) {}

  complaints: ComplaintResponse[] = [];

  ngOnInit(): void {
    this.complaintsService.getNotAnsweredComplaints().subscribe({
      next: data => {
        this.complaints = data;
      },
      error: (err: HttpErrorResponse) => {

      }
    })
  }

  saveAnswer(complaintId: number) {
    const complaint = this.complaints.find(c => c.id === complaintId);

    if (!complaint || !complaint.answer) {
      this.toastr.error("Morate da unesete tekst", "Greska");
      return;
    }

    this.complaintsService.saveAnswer({
      complaintId,
      answer: complaint?.answer
    }).subscribe({
      next: data => {
        this.toastr.success("Uspesno sacuvano", "Odgovor na zalbu je sacuvan");
        this.complaints = this.complaints.filter(c => c.id !== complaintId);
      },
      error: err => {
        this.toastr.error("Neispravni podaci", "Greska");
      }
    })
  }

}
