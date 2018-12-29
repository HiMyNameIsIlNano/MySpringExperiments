import {APP_BASE_HREF} from '@angular/common';
import {async, TestBed} from '@angular/core/testing';

import {BrowserModule} from '@angular/platform-browser';
import {HttpModule} from '@angular/http';
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
import {CloudTitleComponent} from './cloud-title/cloudtitle.component';
import {NonDoughsPipe} from './recents/non-doughs.pipe';
import {DoughPipe} from './recents/DoughPipe';
import {DesignComponent} from './design/design.component';
import {GroupBoxComponent} from './group-box/groupbox.component';
import {BigButtonComponent} from './big-button/bigbutton.component';
import {LittleButtonComponent} from './little-button/littlebutton.component';
import {LocationsComponent} from './locations/locations.component';
import {HttpClientModule} from '@angular/common/http';

import {CartComponent} from './cart/cart.component';
import {CartService} from './cart/cart-service';

import {routes} from './app.routes';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
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
        RouterModule.forRoot(routes),
        BrowserModule,
        HttpModule,
        HttpClientModule,
        FormsModule,
      ],
      providers: [
        {provide: APP_BASE_HREF, useValue: '/'},
        ApiService,
        CartService,
        RecentPizzasService,
      ]
    }).compileComponents();
  }));
  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
  it(`should have as title 'app'`, async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('Pizza Cloud App');
  }));
});
