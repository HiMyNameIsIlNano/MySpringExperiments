import { Injectable } from '@angular/core';
import { ApiService } from '../api/ApiService';

@Injectable()
export class RecentPizzasService {

  constructor(private apiService: ApiService) {
  }

  getRecentPizzas() {
    return this.apiService.get('/design/recent');
  }

}
