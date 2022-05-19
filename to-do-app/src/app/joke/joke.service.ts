import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Joke} from './joke';

@Injectable({
  providedIn: 'root'
})
export class JokeService {

  constructor(private http: HttpClient) { }

  public getJoke() {
    this.http.get<Joke>("https://v2.jokeapi.dev/joke/Any")
      .subscribe((response: Joke) => {
        console.log(response);
      })
  }
}
