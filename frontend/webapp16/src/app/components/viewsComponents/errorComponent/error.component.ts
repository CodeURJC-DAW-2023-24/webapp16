import { Component, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ErrorComponent {
  private _errorCode: number = 0;
  private _errorMessage: string = "";


private errorMessages: { [key: number]: string } = {
  400: 'Bad Request',
  401: 'Unauthorized',
  403: 'Forbidden',
  404: 'Page Not Found',
  408: 'Request Timeout',
  500: 'Internal Server Error',
  502: 'Bad Gateway',
  503: 'Service Unavailable',
  504: 'Gateway Timeout',

};

  get errorCode(): number {
    return this._errorCode;
  }

  set errorCode(value: number) {
    this._errorCode = value;
    this._errorMessage = this.getErrorMessage(value);
  }

  get errorMessage(): string {
    return this._errorMessage;
  }


  private getErrorMessage(errorCode: number): string {
    return this.errorMessages[errorCode] || 'Unknown Error';
  }
}
