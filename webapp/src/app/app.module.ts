import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from "@angular/common/http";

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { ConferencesComponent } from './conferences/conferences.component';
import { SubmitProposalComponent } from './submit-proposal/submit-proposal.component';
import { ViewProposalsComponent } from './view-proposals/view-proposals.component';
import { RolesForSelectedConferenceComponent } from './roles-for-selected-conference/roles-for-selected-conference.component';
import { UpdateProposalComponent } from './update-proposal/update-proposal.component';

import { UserService } from "./shared/user.service";
import { ConferenceService } from "./shared/conference.service";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CreateAccountComponent,
    ConferencesComponent,
    SubmitProposalComponent,
    ViewProposalsComponent,
    RolesForSelectedConferenceComponent,
    UpdateProposalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [UserService,
              ConferenceService
             ],
  bootstrap: [AppComponent]
})
export class AppModule { }
