import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CookiesService {

  salvarToken(token: string) {
    sessionStorage.setItem('token', token);
  }

  get token(): string {
    return sessionStorage.getItem('token')!;
  }
}