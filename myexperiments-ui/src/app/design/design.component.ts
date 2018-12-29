import {Component, Injectable, Input, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router/';
import {CartService} from '../cart/cart-service';

@Component({
  selector: 'pizza-design',
  templateUrl: 'design.component.html',
  styleUrls: ['./design.component.css']
})

@Injectable()
export class DesignComponent implements OnInit {

  model = {
    name: '',
    ingredients: []
  };

  allIngredients: any;
  doughs = [];
  toppings = [];
  cheeses = [];
  sauces = [];

  constructor(private httpClient: HttpClient, private router: Router, private cart: CartService) {
  }

  // tag::ngOnInit[]
  ngOnInit() {
    this.httpClient.get('http://localhost:8080/ingredientsx')
        .subscribe(data => {
          this.allIngredients = data;
          this.doughs = this.allIngredients.filter(w => w.type === 'DOUGH');
          this.toppings = this.allIngredients.filter(p => p.type === 'TOPPINGS');
          this.cheeses = this.allIngredients.filter(c => c.type === 'CHEESE');
          this.sauces = this.allIngredients.filter(s => s.type === 'SAUCE');
        });
  }
  // end::ngOnInit[]

  updateIngredients(ingredient, event) {
    if (event.target.checked) {
      this.model.ingredients.push(ingredient);
    } else {
      this.model.ingredients.splice(this.model.ingredients.findIndex(i => i === ingredient), 1);
    }
  }

  // tag::onSubmit[]
  onSubmit() {
    this.httpClient.post(
        'http://localhost:8080/design',
        this.model, {
            headers: new HttpHeaders().set('Content-type', 'application/json'),
        }).subscribe(pizza => this.cart.addToCart(pizza));

    this.router.navigate(['/cart']);
  }
  // end::onSubmit[]

}
