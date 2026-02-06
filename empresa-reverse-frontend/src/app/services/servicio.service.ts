import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Servicio } from '../models/servicio.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ServicioService {

  private apiUrl = `${environment.apiUrl}/servicios`;

  constructor(private http: HttpClient) {
    console.log('ServicioService initialized with URL:', this.apiUrl);
  }

  getServicios(): Observable<Servicio[]> {
    console.log('Fetching servicios from:', this.apiUrl);
    return this.http.get<Servicio[]>(this.apiUrl).pipe(
      catchError(error => {
        console.error('Error fetching servicios:', error);
        return throwError(() => error);
      })
    );
  }

  getServicioById(id: number): Observable<Servicio> {
    return this.http.get<Servicio>(`${this.apiUrl}/${id}`).pipe(
      catchError(error => {
        console.error(`Error fetching servicio ${id}:`, error);
        return throwError(() => error);
      })
    );
  }
}