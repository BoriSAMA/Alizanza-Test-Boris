export interface Client {
	id: number;
	sharedKey: string;
	bussinessId: string;
	email: string;
	phone: string;
	createdDate: string;
	endDate: string | null;
}