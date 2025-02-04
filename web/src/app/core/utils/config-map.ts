import { pipe, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

export function configMap() {
  return pipe(
    map((res: any) => (res ? (res._body ? res.json() : res) : res)),
    catchError((err: any) => throwError(err._body ? err.json() : { message: err }))
  );
}
