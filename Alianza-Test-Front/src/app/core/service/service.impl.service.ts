import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { IService } from './service.interface';
import ListResponse from '../model/utils/list-response.model';
import * as _ from 'lodash';

@Injectable({
	providedIn: 'root'
})
export class ServiceImplementation<T> implements IService<T> {

	httpClient!: HttpClient;
	endpoint!: string;
	apiUrl: string;

	constructor() {
		this.apiUrl = environment.apiUrl;
	}

	getAll(): Observable<Array<T>> {
		return this.httpClient.get<ListResponse<T>>(this.getFullPath()).pipe(map((res: ListResponse<T>) => res.data));
	}

	get(id: string): Observable<T> {
		return this.httpClient.get(this.getFullPath() + id).pipe(map((res: any) => res.data));
	}

	save(data: any): Observable<number> {
		return this.httpClient.post(this.getFullPath(), data).pipe(map((res: any) => res.data));
	}

	filter(filter: any): Observable<ListResponse<T>> {
		filter = _.omitBy(filter, [ _.isUndefined, _.isNull, _.isEmpty ]);
		return this.httpClient.get<ListResponse<T>>(this.getFullPath(), { params: filter }).pipe(map((res: ListResponse<T>) => res));
	}

	protected getFullPath() {
		return this.apiUrl + this.endpoint;
	}
}
