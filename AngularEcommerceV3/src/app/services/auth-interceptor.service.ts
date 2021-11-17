import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { from, Observable } from 'rxjs';
import { OktaAuthStateService } from '@okta/okta-angular';

import { OktaAuth } from '@okta/okta-auth-js';




@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {

  constructor(private oktaAuthService: OktaAuthStateService,

    private oktaAuth: OktaAuth) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return from(this.handleAccess(req,next));
  }

  private async handleAccess(request:HttpRequest<any>,next:HttpHandler):Promise<HttpEvent<any>>{

    const securedEndpoints =['http://localhost:8080/api/orders'];
    if(securedEndpoints.some(url=>request.urlWithParams.includes(url))){
      const accessToken = await this.oktaAuth.getAccessToken();
      request =request.clone({
        setHeaders : {
          Authorization:"Bearer" + accessToken
        }
      });
    }
    return next.handle(request).toPromise();

  }
}
