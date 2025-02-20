import { HttpParams } from '@angular/common/http';

export class AddHttpParams<T> {
  constructor(private resource: T) { }

  createParams(): HttpParams {
    const paramsValue = <any>{};

    if (!this.resource) {
      return new HttpParams();
    }

    for (const prop of Object.keys(this.resource)) {
      const value = this.resource[prop as keyof T];
      if (
        value ||
        (typeof value === 'number' && Number(value) === Number(0)) ||
        typeof value === 'boolean'
      ) {
        paramsValue[`${prop}`] = String(value);
      }
    }

    return new HttpParams({ fromObject: { ...paramsValue } });
  }
}