import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Plan } from '../models/plan.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PlanService {

  // ✅ USAR ENVIRONMENT - NO hardcodear la URL
  private apiUrl = `${environment.apiUrl}/planes`;

  constructor(private http: HttpClient) { 
    console.log('PlanService URL:', this.apiUrl);
  }

  getPlanes(): Observable<Plan[]> {
    console.log('Fetching planes from:', this.apiUrl);
    return this.http.get<Plan[]>(this.apiUrl);
  }

  getPlanById(id: number): Observable<Plan> {
    return this.http.get<Plan>(`${this.apiUrl}/${id}`);
  }
}