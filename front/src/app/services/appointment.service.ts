import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import AppointmentResponse from '../payload/responses/appointment-response';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  constructor(private http: HttpClient) { }

  getMyAppointments() {
    return this.http.get<AppointmentResponse[]>(environment.backendBaseUrl + "appointments/my-list");
  }

  cancelAppointment(id: string) {
    return this.http.put(environment.backendBaseUrl + "appointments/cancel/" + id, {}, {responseType: 'text'});
  }

}
