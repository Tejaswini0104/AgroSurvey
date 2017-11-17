import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

// used to create fake backend
import {BaseRequestOptions} from '@angular/http';

import {AppComponent} from './app.component';
import {routing} from './app.routing';

import {AuthGuard} from './guards/index';
import {AuthenticationService} from './services/index';
import {HomeComponent} from './home/index';
import {LoginComponent} from './login/index';

@NgModule({
  imports: [BrowserModule, FormsModule, HttpModule, routing],
  declarations: [AppComponent, HomeComponent, LoginComponent],
  providers: [
    AuthGuard,
    AuthenticationService,

    // providers used to create fake backend
    BaseRequestOptions,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
