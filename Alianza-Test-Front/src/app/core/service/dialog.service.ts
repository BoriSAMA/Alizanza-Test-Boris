import { MAT_DIALOG_DATA, MatDialogRef, MatDialog } from '@angular/material/dialog';
import { TitleCasePipe } from '@angular/common';
import { Injectable } from '@angular/core';

@Injectable({
	providedIn: 'root'
})
export class DialogService {

	constructor(private titlePipe: TitleCasePipe, private matDialog: MatDialog) {}

	showWinnerDialog(ganador: any) {
		let dialogRef: MatDialogRef<ModalWinnerComponent>;
		dialogRef = this.matDialog.open(ModalWinnerComponent, {
			data: ganador,
			panelClass: 'custom-modal-class'
		});
		return dialogRef.afterClosed();
	}

	showParticipantesSorteo(idSorteo, fechaSorteo) {
		let dialogRef: MatDialogRef<ParticipantsListComponent>;
		dialogRef = this.matDialog.open(ParticipantsListComponent, {
			data: { idSorteo, fechaSorteo }
		});
		return dialogRef.afterClosed();
	}

	modalCloseLoading() {
		Swal.close();
	}
}
