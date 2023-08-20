import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_MOMENT_DATE_ADAPTER_OPTIONS, MomentDateAdapter } from '@angular/material-moment-adapter';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';

export const MY_FORMATS = {
  parse: {
    dateInput: 'DD/MM/YYYY'
  },
  display: {
    dateInput: 'DD/MM/YYYY',
    monthYearLabel: 'MMMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY'
  }
};

@Component({
  selector: 'bor-clients-form',
  templateUrl: './clients-form.component.html',
  styleUrls: ['./clients-form.component.scss'],
  providers: [
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS]
    },
    { provide: MAT_DATE_FORMATS, useValue: MY_FORMATS }
  ]
})
export class ClientsFormComponent implements OnInit {

  @Output() formEmitter: EventEmitter<any> = new EventEmitter<any>();

  @Input()
  set isFormValidation(isFormValidation: boolean) {
    this._isFormValidation = isFormValidation;
    if (isFormValidation) {
      Object.keys(this.clientsFilterForm.controls).forEach(key => {
        if (key != "endDate") {
          let validators = [Validators.required]
          if (key == "email") {
            validators.push(Validators.email);
          }
          if (key == "phone") {
            validators.push(Validators.pattern(/^[0-9]+$/));
          }
          this.clientsFilterForm.get(key)?.setValidators(validators);
        }
      });
      this.clientsFilterForm.updateValueAndValidity();
    }
  };

  _isFormValidation: boolean = false;
  minDate: Date;
  maxDate: Date;

  clientsFilterForm: FormGroup = this.formBuilder.group({
    name: [''],
    phone: [''],
    email: [''],
    startDate: [''],
    endDate: ['']
  });

  constructor(
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.form['startDate'].valueChanges.subscribe((startDate) => {
      if (startDate) {
        this.minDate = startDate
      }
    });

    this.form['endDate'].valueChanges.subscribe((endDate) => {
      if (endDate) {
        this.maxDate = endDate
      }
    });
  }

  getFormValues() {
    if (this._isFormValidation && !this.clientsFilterForm.valid) {
      alert("Existen campos mal diligenciados.");
    } else if (!this._isFormValidation && !this.clientsFilterForm.dirty) {
      this.formEmitter.emit(null);
    } else {
      this.formEmitter.emit(this.clientsFilterForm.getRawValue());
      this.clientsFilterForm.reset()
    }
  }

  get form() {
    return this.clientsFilterForm.controls;
  }
}
