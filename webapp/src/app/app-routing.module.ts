import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { ConferencesComponent } from './conferences/conferences.component';
import { SubmitProposalComponent } from './submit-proposal/submit-proposal.component';
import { ViewProposalsComponent } from './view-proposals/view-proposals.component';
import { RolesForSelectedConferenceComponent } from './roles-for-selected-conference/roles-for-selected-conference.component';
import { UpdateProposalComponent } from './update-proposal/update-proposal.component';
import { RoleComponent } from "./role/role.component";
import { BidProposalComponent } from "./bid-proposal/bid-proposal.component";

const routes: Routes = [
  // { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'loginPage', component: LoginComponent },
  { path: 'createAccount', component: CreateAccountComponent },
  { path: 'conferences', component: ConferencesComponent },
  { path: 'submitProposal', component: SubmitProposalComponent },
  { path: 'viewProposals', component: ViewProposalsComponent },
  { path: 'roles', component: RolesForSelectedConferenceComponent },
  { path: 'updateProposal', component: UpdateProposalComponent },
  { path: 'role', component: RoleComponent },
  { path: 'updateProposal', component: UpdateProposalComponent },
  { path: 'role', component: RoleComponent },
  { path: 'bidProposal', component: BidProposalComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
