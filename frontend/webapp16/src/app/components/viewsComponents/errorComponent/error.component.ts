import { Component, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ErrorComponent {
  get errorMessage(): string {
    return this._errorMessage;
  }

  set errorMessage(value: string) {
    this._errorMessage = value;
  }
  get errorCode(): number {
    return this._errorCode;
  }

  set errorCode(value: number) {
    this._errorCode = value;
  }
  private _errorCode: number = 0;
  private _errorMessage: string = "";
}
