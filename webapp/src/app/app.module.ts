import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { ConferencesComponent } from './conferences/conferences.component';
import { SubmitProposalComponent } from './submit-proposal/submit-proposal.component';
import { ViewProposalsComponent } from './view-proposals/view-proposals.component';
import { RolesForSelectedConferenceComponent } from './roles-for-selected-conference/roles-for-selected-conference.component';
import { UpdateProposalComponent } from './update-proposal/update-proposal.component';
import { RoleComponent } from './role/role.component';
import { BidProposalComponent } from './bid-proposal/bid-proposal.component';
import { AddConferenceComponent } from './add-conference/add-conference.component';
import { ChangeDeadlinesComponent } from './change-deadlines/change-deadlines.component';
import { SendPaperToReviewerComponent } from './send-paper-to-reviewer/send-paper-to-reviewer.component';

import { UserService } from "./shared/user.service";
import { ConferenceService } from "./shared/conference.service";
import { PaperService } from "./shared/paper.service";
import { UserConferenceService } from "./shared/user-conference.service";
import { ReviewerPaperService } from "./shared/reviewer-paper.service";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CreateAccountComponent,
    ConferencesComponent,
    SubmitProposalComponent,
    ViewProposalsComponent,
    RolesForSelectedConferenceComponent,
    UpdateProposalComponent,
    RoleComponent,
    BidProposalComponent,
    AddConferenceComponent,
    ChangeDeadlinesComponent,
    SendPaperToReviewerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [UserService,
              ConferenceService,
              PaperService,
              UserConferenceService,
              ReviewerPaperService
             ],
  bootstrap: [AppComponent]
})
export class AppModule { }
