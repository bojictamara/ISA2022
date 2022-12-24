import { LiveAnnouncer } from '@angular/cdk/a11y';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, ViewChild, OnInit } from '@angular/core';
import { MatSort, Sort } from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import CenterResponse from 'src/app/payload/responses/center-response';
import { CentersService } from 'src/app/services/centers.service';

@Component({
  selector: 'app-centers-list',
  templateUrl: './centers-list.component.html',
  styleUrls: ['./centers-list.component.css']
})
export class CentersListComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'description', 'averageRate'];
  centers: CenterResponse[] = [];
  dataSource = new MatTableDataSource(this.centers);

  constructor(private centersService: CentersService) {

  }

  ngOnInit(): void {
    this.centersService.getCenters().subscribe({
      next: data => {
        this.centers = data;
        this.dataSource.data = this.centers;
      },
      error: (err: HttpErrorResponse) => {

      }
    })
  }

  @ViewChild(MatSort) sort?: MatSort;


  announceSortChange(sort: Sort) {
    const data = this.centers.slice();
    if (!sort.active || sort.direction === '') {
      this.dataSource.data = data;
      return;
    }

    this.dataSource.data = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name':
          return compare(a.name, b.name, isAsc);
        case 'description':
          return compare(a.description, b.description, isAsc);
        case 'averageRate':
          return compare(a.averageRate, b.averageRate, isAsc);
        case 'id':
          return compare(a.id, b.id, isAsc);
        default:
          return 0;
      }
    });
  }
}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
