declare var bootstrap: any;

export class ActionToast{
    static action = {
        "errorColor": "red",
        "successColor": "green",
    }

    static apps = {
        "userApp": '<i class="bi bi-people-fill me-1 text-warning-emphasis"></i>User',
        "adminApp": '<i class="bi bi-person-fill-gear me-1 text-primary-emphasis"></i>Admins',
        "productsApp": '<i class="bi bi-globe-americas me-1 text-primary"></i>Products',
        "discountsApp": '<i class="bi bi-cash-coin me-1 text-success"></i>Discounts',
    }
    
    static showToast(app:string, action:any, msg:any, color:any): void {
        const toastEl = document.getElementById('liveToast');
        toastEl!.style.color = color;
        const toast = new bootstrap.Toast(toastEl); // Initialize the toast
        document.getElementById('toast-application')!.innerHTML = app;
        document.getElementById('toast-action')!.innerHTML = action;
        document.getElementById('toast-message')!.innerHTML = msg;
        toast.show(); // Show the toast
      }
}