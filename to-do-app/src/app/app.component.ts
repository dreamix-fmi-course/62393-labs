import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'to-do-app';
  name: string = 'name';
  test = 1;

  public onClick() {
    console.log('clicked');
    console.log(this.name);
  }
}
