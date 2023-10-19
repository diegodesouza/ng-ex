/**
 * Created by AC56833 on 6/7/2017.
 */
import { Component, ViewChild, ElementRef } from '@angular/core';
import { PayLoadService } from '../../../common/services/payload-all.service';
import { Headers, RequestOptions, Http } from '@angular/http';
import { Attachment } from '../../../common/models/attachment.model';
import { SelectUpdatesModel } from '../../../common/config/select-updates.model';

@Component({
  selector: 'form-attachments',
  template: require('./body.html'),
  styles: [require('./styles.css')]
})

export class AttachmentComponent {
  @ViewChild('textArea') textArea: ElementRef;
  @ViewChild('fileInput') fileInput: ElementRef;

  constructor(private http: Http, private payloadService: PayLoadService, private updates: SelectUpdatesModel) {
  }

  fileChange() {
    let fileList: FileList = this.fileInput.nativeElement.files;
    if (fileList.length > 0) {
      let file: File = fileList[0];
      if (this.getTotalSize(file.size) > 10) {
        alert('Your attachments exceed the specified limit of 10 MB. You can manage your attachments by using the add or remove.');
        return;
      }
      if (this.fileExists(file.name)) {
        alert('The File Name chosen is already Uploaded.');
        return;
      }
      let attachment: Attachment = new Attachment();
      attachment.fileName = file.name;
      attachment.fileSize = this.convertBytesToMB(file.size);
      attachment.fileComments = this.textArea.nativeElement.value;
      attachment.fileType = file.type.toString();
      let formData: FormData = new FormData();
      formData.append('fileContentData', file, file.name);
      formData.append('formUpdateAction', 'uploadFile');
      let headers = new Headers();
      headers.append('Accept', 'application/json, text/plain, text/html');
      let options = new RequestOptions({ headers: headers });
      options.withCredentials = true;
      this.http.post('/mwpmf/message/PDMControllerServlet', formData, options)
        .subscribe(
          (data) => {
            this.payloadService.attachments.push(attachment);
            if (this.payloadService.attachments.length > 0) {
              this.payloadService.isAttachment = true;
            }
            this.textArea.nativeElement.value = '';
            this.fileInput.nativeElement.value = null;
          },
          (error) => {
            if (error.status === 403) {
              alert('Selected file does not pass the virus scan.The file has not been uploaded.');
            }
            else {
              alert('The filename includes invalid characters or an invalid extension.');
            }
          }
        );
    }
  }

  fileRemove(attachment: Attachment) {
    let index: number = this.payloadService.attachments.indexOf(attachment);
    if (index > -1) {
      this.payloadService.attachments.splice(index, 1);
      if (this.payloadService.isAttachmentRequired && this.payloadService.attachments.length === 0) {
        this.payloadService.isAttachment = false;
      }
    }
  }

  getTotalSize(fileSize: number) {
    let totalSize: number = 0;
    for (let attachment of this.payloadService.attachments) {
      totalSize += attachment.fileSize;
    }
    return totalSize + this.convertBytesToMB(fileSize);
  }

  convertBytesToMB(fileSize: number) {
    let mb: number = (fileSize / 1024) / 1024;
    return (Math.round(mb * 100) / 100);
  }

  fileExists(fileName: string) {
    for (let attachment of this.payloadService.attachments) {
      if (attachment.fileName === fileName) {
        return true;
      } else {
        return false;
      }
    }
  }
}
