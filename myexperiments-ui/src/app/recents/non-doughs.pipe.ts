import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'nondoughs'
})

export class NonDoughsPipe implements PipeTransform {
  transform(ingredients: any, ...args: any[]): any {
    const nondoughs = [];
    for (const ingredient of ingredients) {
      if (ingredient.type !== 'DOUGH') {
        nondoughs.push(ingredient);
      }
    }
    return nondoughs;
  }
}
