import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {MaterialModule} from './material/material.module';
import {FormsModule} from '@angular/forms';
import {ItemComponent} from './item/item.component';
import {HttpClientModule} from '@angular/common/http';
import { JokeComponent } from './joke/joke.component';

@NgModule({
    declarations: [
        AppComponent,
        ItemComponent,
        JokeComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        MaterialModule,
        HttpClientModule,
        FormsModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {}
