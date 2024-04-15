// image-search.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';

const httpOptions={
  headers: new HttpHeaders({
    'Authorization': "A5PzIQ5xwSVE656B2mKTLFpO724HnPo7FoXAheqI3GO9Maah4V40FyIq"
  })
}

@Injectable({
  providedIn: 'root'
})
export class ImageSearchService {

  constructor(private http: HttpClient) { }

  getProductImage(productName: string): Observable<any> {

    const apiUrl = `https://api.pexels.com/v1/search?query=${productName}&per_page=1`;
    return this.http.get<any>(apiUrl, httpOptions).pipe(catchError(this.handelError));
  }

  handelError(error: any){  
    return throwError(()=> "Pexel Image Server Error");  
  }
}
