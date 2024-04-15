import { AfterViewInit, Component, OnInit } from "@angular/core";
import { RouterLink, RouterLinkActive, RouterOutlet } from "@angular/router";
import { AppService } from "../../../../app.service";
import { UsersResponse } from "../../../../http response/users-response";
import { PhoneNumberPipe } from "../../../../phoneNumberPipe";

@Component({
    selector: "admin-users-read-all-component",
    templateUrl: "admin-users-read-all.component.html",
    standalone: true,
    imports: [RouterLink, RouterOutlet, RouterLinkActive, PhoneNumberPipe]
})
export class AdminUsersReadAllComponent implements OnInit{

    users: UsersResponse[] = [];

    constructor(private client: AppService){ }
    

    ngOnInit(): void {
        this.client.userReadAll().subscribe(data=>{
            this.users = data;
        });
    }

}