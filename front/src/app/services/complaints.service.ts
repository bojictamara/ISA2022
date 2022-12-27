import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import ComplaintRequest from '../payload/requests/complaint.request';
import ComplaintResponse from '../payload/responses/complaint.response';

@Injectable({
  providedIn: 'root'
})
export class ComplaintsService {

  constructor(private http: HttpClient) { }

  saveComplaint(requestBody: ComplaintRequest) {
    console.log(requestBody);
    return this.http.post(environment.backendBaseUrl + "complaints", requestBody, {responseType: 'text'});
  }

  getMyComplaints() {
    return this.http.get<ComplaintResponse[]>(environment.backendBaseUrl + "my-complaints");
  }

  getNotAnsweredComplaints() {
    return this.http.get<ComplaintResponse[]>(environment.backendBaseUrl + "not-answered-complaints");
  }

  saveAnswer(requestBody: any) {
    return this.http.put(environment.backendBaseUrl + "complaints/answer", requestBody, {responseType: 'text'});
  }

  getAdminComplaints() {
    return this.http.get<ComplaintResponse[]>(environment.backendBaseUrl + "complaints/admin");
  }
}
