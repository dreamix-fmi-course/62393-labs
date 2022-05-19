import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title: string = 'to-do-app';
  name: string = 'name';
  test: number = 1;
  showItem: boolean = false;

  public onClick() {
    console.log('clicked');
    console.log(this.name);
  }

  public inputEvent(event: any) {
      console.log(event.target.value);
      this.name = event.target.value;
  }
}
