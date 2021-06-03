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
import { RolesForSelectedConferenceComponent } from './roles-for-selected-conference/roles-for-selected-conference.component';
import { UpdateProposalComponent } from './update-proposal/update-proposal.component';
import { RoleComponent } from './role/role.component';
import { BidProposalComponent } from './bid-proposal/bid-proposal.component';
import { AddConferenceComponent } from './add-conference/add-conference.component';
import { ChangeDeadlinesComponent } from './change-deadlines/change-deadlines.component';
import { SendPaperToReviewerComponent } from './send-paper-to-reviewer/send-paper-to-reviewer.component';
import { ReviewAssignedPapersComponent } from './review-assigned-papers/review-assigned-papers.component';
import { GiveReviewComponent } from './give-review/give-review.component';
import { SendResultsComponent } from './send-results/send-results.component';
import { ViewResultsComponent } from './view-results/view-results.component';

import { UserService } from "./shared/user.service";
import { ConferenceService } from "./shared/conference.service";
import { PaperService } from "./shared/paper.service";
import { UserConferenceService } from "./shared/user-conference.service";
import { ReviewerPaperService } from "./shared/reviewer-paper.service";
import { PaperReviewService } from "./shared/paper-review.service";
import { ResultService } from "./shared/result.service";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CreateAccountComponent,
    ConferencesComponent,
    SubmitProposalComponent,
    RolesForSelectedConferenceComponent,
    UpdateProposalComponent,
    RoleComponent,
    BidProposalComponent,
    AddConferenceComponent,
    ChangeDeadlinesComponent,
    SendPaperToReviewerComponent,
    ReviewAssignedPapersComponent,
    GiveReviewComponent,
    SendResultsComponent,
    ViewResultsComponent,
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
              ReviewerPaperService,
              PaperReviewService,
              ResultService
             ],
  bootstrap: [AppComponent]
})
export class AppModule { }
