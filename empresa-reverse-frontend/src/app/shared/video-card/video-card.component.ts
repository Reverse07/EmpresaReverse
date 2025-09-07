import { Component, Input, OnChanges } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-video-card',
  standalone: true,
  templateUrl: './video-card.component.html',
  styleUrls: ['./video-card.component.css']
})
export class VideoCardComponent implements OnChanges {

  // 🏷️ Título del video (se muestra debajo del iframe)
  @Input() title: string = '';

  // 🔗 URL del video (YouTube, Vimeo, etc.)
  @Input() videoUrl: string = '';

  // 🔐 URL segura para incrustar en el iframe
  safeUrl: SafeResourceUrl = '';

  constructor(public sanitizer: DomSanitizer) {}

  // 🔄 Cada vez que cambia la URL, la sanitizamos
  ngOnChanges(): void {
    this.safeUrl = this.sanitizer.bypassSecurityTrustResourceUrl(this.videoUrl);
  }
}
