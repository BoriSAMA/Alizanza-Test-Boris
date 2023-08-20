export default class ListResponse<T> {
	count: number;
	data: Array<T>;
	message: string;
	statusCode: number;
	success: boolean;
}
