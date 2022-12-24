import { Component, OnInit } from '@angular/core';
import { Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import AppointmentResponse from 'src/app/payload/responses/appointment-response';
import CenterResponse from 'src/app/payload/responses/center-response';
import { CentersService } from 'src/app/services/centers.service';

@Component({
  selector: 'app-center-details',
  templateUrl: './center-details.component.html',
  styleUrls: ['./center-details.component.css']
})
export class CenterDetailsComponent implements OnInit {

  centerId: string = "";
  center?: CenterResponse;

  displayedColumns: string[] = ['id', 'start'];
  appointments: AppointmentResponse[] = [];
  dataSource = new MatTableDataSource(this.appointments);

  constructor(private centerService: CentersService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService) {
      this.activatedRoute.params.subscribe({
        next: data => {
          this.centerId = data['id'];
        }
      });
    }

  ngOnInit(): void {
    this.centerService.getCenterById(this.centerId)
    .subscribe({
      next: data => {
        this.center = data;
      }
    })


    this.centerService.getFreeAppointmentsByCentre(this.centerId)
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

  bookAppointment(id: string) {
    this.toastr.success("naslov", "telst")
  }
}

function compare(a: number | string | Date, b: number | string | Date, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}

