import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs";
import {DataimportService} from "./shared/dataimport.service";
import {HttpEventType, HttpResponse} from "@angular/common/http";
// import {Observable} from "rxjs";
// import {HttpEventType, HttpResponse} from "@angular/common/http";

@Component({
  selector: 'app-dataimport',
  templateUrl: './dataimport.component.html',
  styleUrls: ['./dataimport.component.css']
})
export class DataimportComponent implements OnInit {
  selectedFiles: FileList;
  currentFile: File;
  file1: FileList;
  file2: FileList;
  file3: FileList
  progress = 0;
  message = '';
  fileInfos: Observable<any>;

  constructor(private uploadService: DataimportService) { }

  ngOnInit(): void {
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  selectFile1(event) {
    this.file1 = event.target.files;
  }
  selectFile2(event) {
    this.file2 = event.target.files;
  }
  selectFile3(event) {
    this.file3 = event.target.files;
  }

  uploadAll(){
    this.upload(this.file1);
    this.upload(this.file2);
    this.upload(this.file3);
  }

  upload(file: FileList) {
    this.progress = 0;
    this.uploadService.upload(file.item(0)).subscribe(
      event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          this.message = event.body.message;
        }
      },
      err => {
        this.progress = 0;
        this.message = 'Could not upload the file!';
        this.currentFile = undefined;
      });
    this.selectedFiles = undefined;
  }


}
