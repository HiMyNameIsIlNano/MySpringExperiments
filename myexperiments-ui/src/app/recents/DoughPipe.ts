import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'doughs'
})

export class DoughPipe implements PipeTransform {
  transform(ingredients: any, ...args: any[]): any {
    const doughs = [];
    for (const ingredient of ingredients) {
      if (ingredient.type === 'WRAP') {
        const dough: any = {};
        dough.id = ingredient.id;
        dough.name = 'a ' + ingredient.name;
        dough.type = ingredient.type;
        doughs.push(dough);
      }
    }
    return doughs;
  }
}
