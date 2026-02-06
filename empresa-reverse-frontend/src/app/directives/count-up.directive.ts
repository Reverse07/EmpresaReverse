import { Directive, ElementRef, Input, OnInit } from '@angular/core';

@Directive({
  selector: '[countUp]',
  standalone: true
})
export class CountUpDirective implements OnInit {
  @Input() countUp: number = 0;
  @Input() duration: number = 2000;
  @Input() suffix: string = '';

  constructor(private el: ElementRef) {}

  ngOnInit(): void {
    this.animateCount();
  }

  private animateCount(): void {
    const element = this.el.nativeElement;
    const start = 0;
    const end = this.countUp;
    const increment = end / (this.duration / 16); // 60 FPS
    let current = start;

    const timer = setInterval(() => {
      current += increment;
      if (current >= end) {
        current = end;
        clearInterval(timer);
      }
      element.textContent = Math.floor(current).toString();
    }, 16);
  }
}