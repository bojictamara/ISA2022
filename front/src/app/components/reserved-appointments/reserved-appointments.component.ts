import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import AppointmentResponse from 'src/app/payload/responses/appointment-response';
import { AppointmentService } from 'src/app/services/appointment.service';

@Component({
  selector: 'app-reserved-appointments',
  templateUrl: './reserved-appointments.component.html',
  styleUrls: ['./reserved-appointments.component.css']
})
export class ReservedAppointmentsComponent implements OnInit {

    displayedColumns: string[] = ['id', 'start'];
    appointments: AppointmentResponse[] = [];
    dataSource = new MatTableDataSource(this.appointments);

    constructor(private appointmentService: AppointmentService,
      private toastr: ToastrService) {}

    ngOnInit(): void {
      this.appointmentService.getMyAppointments()
      .subscribe({
        next: data => {
          this.appointments = data;
          this.dataSource.data = data;
        }
      })
    }

    sort(sort: Sort) {
      const data = this.appointments.slice();
      if (!sort.active || sort.direction === '') {
        this.dataSource.data = data;
        return;
      }

      this.dataSource.data = data.sort((a, b) => {
        const isAsc = sort.direction === 'asc';
        switch (sort.active) {
          case 'id':
            return compare(a.id, b.id, isAsc);
          case 'start':
            return compare(a.start, b.start, isAsc);
          default:
            return 0;
        }
      });
    }

    cancelAppointment(id: string) {
      this.appointmentService.cancelAppointment(id).subscribe({
        next: data => {
          this.toastr.success("Uspesno otkazano", "Termin uspesno otkazan");
          this.appointments = this.appointments.filter(a => a.id !== +id);
          this.dataSource.data = this.appointments;
        }, error: (err: HttpErrorResponse) => {
          this.toastr.error("Greska", err.error);
        }
      })
    }
  }

  function compare(a: number | string | Date, b: number | string | Date, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }



