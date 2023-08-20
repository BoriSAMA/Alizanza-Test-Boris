import { Injectable } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';

@Injectable({
  providedIn: 'root'
})
export class SpinnerService {

  constructor(private spinner: NgxSpinnerService) {}

  show(){
    this.spinner.show(undefined, {
      type: 'ball-atom',
      size: 'medium',
      bdColor: 'rgba(0,0,0, .8)',
      color: 'white',
      fullScreen: true
    });
  }

  hide(){
    this.spinner.hide();
  }
}
