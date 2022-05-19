import { Component, OnInit } from '@angular/core';
import {JokeService} from './joke.service';

@Component({
  selector: 'app-joke',
  templateUrl: './joke.component.html',
  styleUrls: ['./joke.component.scss']
})
export class JokeComponent implements OnInit {

  constructor(private jokeService: JokeService) { }

  ngOnInit(): void {
  }

  public getJoke() {
    this.jokeService.getJoke();
  }

}
