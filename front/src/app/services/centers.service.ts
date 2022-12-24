import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import AppointmentResponse from '../payload/responses/appointment-response';
import CenterResponse from '../payload/responses/center-response';

@Injectable({
  providedIn: 'root'
})
export class CentersService {

  constructor(private http: HttpClient) { }

  getCenters() {
    return this.http.get<CenterResponse[]>(environment.backendBaseUrl + "centers");
  }

  getCenterById(id: string) {
    return this.http.get<CenterResponse>(environment.backendBaseUrl + "centers/" + id);
  }

  getFreeAppointmentsByCentre(id: string) {
    return this.http.get<AppointmentResponse[]>(environment.backendBaseUrl + "centers/" + id + "/free-appointments");
  }

}
