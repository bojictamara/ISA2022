import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { QuestionnaireService } from 'src/app/services/questionnaire.service';

@Component({
  selector: 'app-questionnaire',
  templateUrl: './questionnaire.component.html',
  styleUrls: ['./questionnaire.component.css']
})
export class QuestionnaireComponent {

  constructor(private questionnaireService: QuestionnaireService,
    private toastrService: ToastrService, private router: Router) {}

  answer1 = false;
  answer2 = false;
  answer3 = false;
  answer4 = false;
  answer5 = false;
  answer6 = false;
  answer7 = false;
  answer8 = false;
  answer9 = false;
  answer10 = false;
  answer11 = false;
  answer12 = false;
  answer13 = false;
  answer14 = false;
  answer15 = false;
  answer16 = false;
  answer17 = false;
  answer18 = false;
  answer19 = false;
  answer20 = false;
  answer21 = false;
  answer22 = false;
  answer23 = false;
  answer24: boolean | null = null;
  answer25: boolean | null = null;
  answer26: boolean | null = null;

  submitForm() {
    this.questionnaireService.saveQuestionnaire({
      answer1: this.answer1,
      answer2: this.answer2,
      answer3: this.answer3,
      answer4: this.answer4,
      answer5: this.answer5,
      answer6: this.answer6,
      answer7: this.answer7,
      answer8: this.answer8,
      answer9: this.answer9,
      answer10: this.answer10,
      answer11: this.answer11,
      answer12: this.answer12,
      answer13: this.answer13,
      answer14: this.answer14,
      answer15: this.answer15,
      answer16: this.answer16,
      answer17: this.answer17,
      answer18: this.answer18,
      answer19: this.answer19,
      answer20: this.answer20,
      answer21: this.answer21,
      answer22: this.answer22,
      answer23: this.answer23,
      answer24: this.answer24,
      answer25: this.answer25,
      answer26: this.answer26,
    }).subscribe({
      next: data => {
        this.toastrService.success("Uspesno sacuvano", "Upitnik je uspesno sacuvan");
        this.router.navigate(['/']);
      },
      error: err => {
        this.toastrService.error("Upitnik nije sacuvan", "Upitnik nije uspesno sacuvan");
      }
    })
  }

}
