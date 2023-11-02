import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {environnement} from "../environnements/environnement";
import {Observable} from "rxjs";

@Injectable()
export class ApiUrlInterceptor implements HttpInterceptor {
    apiUrl = environnement.apiUrl;
    url = environnement.url;

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        req = req.clone({
            url: this.preparerUrl(req.url),
            withCredentials: true,
            setHeaders: {
                'Access-Control-Allow-Credentials': 'true',
                'Access-Control-Allow-Origin': this.apiUrl,
                'Access-Control-Expose-Headers': 'Authorization'
            }
        });

        return next.handle(req);
    }

    preparerUrl(url: string): string {
        if (url.includes('assets')) {
            return url;
        }

        url = this.isAbsoluteUrl(url) ? url : this.apiUrl + '/' + url;
        return url.replace(/([^:]\/)\/+/g, '$1');
    }

    private isAbsoluteUrl(url: string) {
        const absolutePattern = /^http?:\/\//i;
        return absolutePattern.test(url);
    }

}
