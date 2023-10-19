///<reference path='../../common/models/payload-output/payload-pega.model.ts'/>

import {AfterViewInit, Component, OnInit} from '@angular/core';
import {SelectUpdatesModel} from '../../common/config/select-updates.model';
import {PayLoadService} from '../../common/services/payload-all.service';
import {Router} from '@angular/router';
import {CrossJSONMapper} from '../../common/models/payload-output/cross-json-mapper.service';
import {Http, RequestOptions, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import {ScrollService} from '../../common/services/scroll.service';

@Component({
  template: require('./body.html'),
  styles:  [require('./styles.scss')]
})

export class ReviewForSubmissionComponent implements OnInit, AfterViewInit {
  private submitStatus: string;
  private submitResponse: string;
  private isSubmitView: boolean;
  private ajaxLoad: string = 'LOADING';

  constructor(
    private scrollService: ScrollService,
    private updates: SelectUpdatesModel,
    private payLoadService: PayLoadService,
    private router: Router,
    private crossJSONMapper: CrossJSONMapper,
    private http: Http
  ) {}

  ngOnInit() {
    this.isSubmitView = false;
    this.payLoadService.isAcceptance = false;
    this.updates.updateRequiredAttachment();
  }

  ngAfterViewInit() {
    this.scrollService.scroll();
  }

  attest() {
    this.isSubmitView = true;
  }

  submitForm() {
    this.ajaxLoad = 'submit';
    this.crossJSONMapper.mapUIJSONxPEGA();
    let bodyString = JSON.stringify(this.crossJSONMapper.rootObject); // Stringify payload
    let headers = new Headers({ 'Content-Type': 'application/json' }); // ... Set content type to JSON
    headers.append('Accept', 'application/json, text/plain, text/html');
    let options = new RequestOptions({ headers: headers });
    options.withCredentials = true;

    this.http.post('/mwpmf/message/PDMControllerServlet', bodyString, options)
      .map((res) => {
        res.headers.set('Strict-Transport-Security','max-age=31536000; includeSubDomains');
        return res.json();
      })
      .catch((error) => Observable.throw(error))
      .subscribe(
        (data) => {
          this.submitResponse = data.message;
          this.submitStatus = data.status;
        },
        (error) => {
          this.ajaxLoad = 'error';
          if (error.status === 403) {
            alert('Selected file does not pass the virus scan.The file has not been uploaded.');
          }
        },
        () => {
          if(this.submitStatus) {
            this.ajaxLoad = 'success';
            this.payLoadService.responseStatus = this.submitStatus;
            this.payLoadService.responseMessage = this.submitResponse;
            this.router.navigate(['dashboard/submit'], {skipLocationChange: true});
          }
          else {
            this.ajaxLoad = 'error';
          }
        }
      );
  }
}
