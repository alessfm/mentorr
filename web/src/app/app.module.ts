import ptBr from '@angular/common/locales/pt';
import { registerLocaleData } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { CUSTOM_ELEMENTS_SCHEMA, DEFAULT_CURRENCY_CODE, LOCALE_ID, NgModule } from '@angular/core';

import { LogadoGuard } from '@core/guards/logado.guard';
import { RoleGuard } from '@core/guards/role.guard';

import { TokenInterceptor } from '@core/middleware/token.interceptor';
import { ErrorInterceptor } from '@core/middleware/error.interceptor';
import { RequestInterceptor } from '@core/middleware/request.interceptor';

import { AppRoutingModule } from './app.routing';
import { AppComponent } from './app.component';

registerLocaleData(ptBr);

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    LogadoGuard,
    RoleGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: RequestInterceptor,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorInterceptor,
      multi: true,
    },
    {
      provide: LOCALE_ID,
      useValue: 'pt-BR',
    },
    {
      provide: DEFAULT_CURRENCY_CODE,
      useValue: 'BRL'
    },
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
