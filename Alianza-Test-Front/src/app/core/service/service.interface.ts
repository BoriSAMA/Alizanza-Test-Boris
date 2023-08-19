
import { Observable } from 'rxjs';
import ListResponse from '../model/utils/list-response.model';

export interface IService<T> {

	getAll(): Observable<Array<T>>;

	get(id: string): Observable<T>;

	save(data: any): Observable<number>;

	filter(filter: any): Observable<ListResponse<T>>;

}
