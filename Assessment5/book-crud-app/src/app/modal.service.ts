// modal.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';
import { Book } from './book';

@Injectable({
  providedIn: 'root'
})
export class ModalService {
  private showModalSubject = new BehaviorSubject<{showRead: boolean, showUpdate: boolean, id: string | null}>({showRead: false, showUpdate: false, id: null});
  public showModal$ = this.showModalSubject.asObservable();

  private bookUpdateSource = new Subject<Book>();
  bookUpdated$ = this.bookUpdateSource.asObservable();

  constructor() { }

  openReadModal(id: string) {
    this.showModalSubject.next({showRead: true, showUpdate: false, id});
  }

  openUpdateModal(id: string) {
    
    this.showModalSubject.next({showUpdate: true, showRead: false, id});
  }

  closeModal() {
    this.showModalSubject.next({showRead: false, showUpdate: false, id: null});
  }

  notifyBookUpdate(book: Book){
    this.bookUpdateSource.next(book);
  }

}
