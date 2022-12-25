import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class QuestionnaireService {

  constructor(private http: HttpClient) { }

  saveQuestionnaire(body: any) {
    return this.http.post<any>(environment.backendBaseUrl + "questionnaire", body);
  }
}
