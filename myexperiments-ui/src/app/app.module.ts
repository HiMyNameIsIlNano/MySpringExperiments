import {BrowserModule} from '@angular/platform-browser';
import {HttpModule} from '@angular/http';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {HeaderComponent} from './header/header.component';
import {FooterComponent} from './footer/footer.component';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {RecentPizzasComponent} from './recents/recents.component';
import {ApiService} from './api/ApiService';
import {RecentPizzasService} from './recents/recent-pizzas.service';
import {SpecialsComponent} from './specials/specials.component';
import {routes} from './app.routes';
import {CloudTitleComponent} from './cloud-title/cloudtitle.component';
import {NonDoughsPipe} from './recents/non-doughs.pipe';
import {DoughPipe} from './recents/DoughPipe';
import {DesignComponent} from './design/design.component';
import {GroupBoxComponent} from './group-box/groupbox.component';
import {BigButtonComponent} from './big-button/bigbutton.component';
import {LittleButtonComponent} from './little-button/littlebutton.component';
import {LocationsComponent} from './locations/locations.component';
import {HttpClientModule} from '@angular/common/http';

import {OAuthModule} from 'angular-oauth2-oidc';
import {CartComponent} from './cart/cart.component';
import {CartService} from './cart/cart-service';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    LoginComponent,
    FooterComponent,
    RecentPizzasComponent,
    SpecialsComponent,
    LocationsComponent,
    CloudTitleComponent,
    DesignComponent,
    CartComponent,
    NonDoughsPipe,
    DoughPipe,
    GroupBoxComponent,
    BigButtonComponent,
    LittleButtonComponent,
  ],
  imports: [
    BrowserModule,
    HttpModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes),
    OAuthModule.forRoot()
  ],
  providers: [
    ApiService,
    CartService,
    RecentPizzasService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
