import { Component, OnInit, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { ServiceCardComponent } from '../../shared/service-card/service-card.component';
import { VideoCardComponent } from '../../shared/video-card/video-card.component';
import { ServicioService } from '../../services/servicio.service';
import { Servicio } from '../../models/servicio.model';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, ServiceCardComponent, VideoCardComponent, RouterLink],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, AfterViewInit {
  @ViewChild('heroVideo') heroVideo!: ElementRef<HTMLVideoElement>;
  @ViewChild('servicesSection') servicesSection!: ElementRef<HTMLElement>;

  servicios: Servicio[] = [];
  isLoading = true;

  stats = [
    { value: '500+', label: 'Clientes Satisfechos' },
    { value: '15+', label: 'Años de Experiencia' },
    { value: '1000+', label: 'Proyectos Completados' },
    { value: '24/7', label: 'Soporte Técnico' }
  ];

  constructor(private servicioService: ServicioService) {
    console.log('🟢 HomeComponent constructor inicializado');
  }

  ngOnInit(): void {
    console.log('🟢 ngOnInit ejecutado');
    this.loadServicios();
    this.setupScrollAnimations();
  }

  ngAfterViewInit(): void {
    console.log('🟢 ngAfterViewInit ejecutado');
    this.initializeHeroVideo();
  }

  private loadServicios(): void {
    this.servicioService.getServicios().subscribe({
      next: (data) => {
        this.servicios = data || [];
        this.isLoading = false;
        console.log('✅ Servicios cargados:', this.servicios.length);
      },
      error: (err) => {
        console.error('❌ Error cargando servicios', err);
        this.servicios = [];
        this.isLoading = false;
      }
    });
  }

  private initializeHeroVideo(): void {
    if (this.heroVideo?.nativeElement) {
      const video = this.heroVideo.nativeElement;
      video.muted = true;
      
      video.play().catch((err) => {
        console.warn('⚠️ No se pudo reproducir el video automáticamente:', err);
        
        const playOnInteraction = () => {
          video.play();
          document.removeEventListener('click', playOnInteraction);
        };
        document.addEventListener('click', playOnInteraction, { once: true });
      });
    }
  }

  scrollToServices(): void {
    console.log('🔵 scrollToServices() llamado');
    if (this.servicesSection?.nativeElement) {
      this.servicesSection.nativeElement.scrollIntoView({
        behavior: 'smooth',
        block: 'start'
      });
    }
  }

  private setupScrollAnimations(): void {
    if (typeof IntersectionObserver !== 'undefined') {
      const observer = new IntersectionObserver(
        (entries) => {
          entries.forEach((entry) => {
            if (entry.isIntersecting) {
              entry.target.classList.add('animate-in');
            }
          });
        },
        {
          threshold: 0.1,
          rootMargin: '0px 0px -50px 0px'
        }
      );

      setTimeout(() => {
        const animatedElements = document.querySelectorAll(
          '.service-card, .video-card-wrapper, .stat-item'
        );
        animatedElements.forEach((el) => observer.observe(el));
      }, 100);
    }
  }

  trackByServicioId(index: number, servicio: Servicio): any {
    return servicio.id || index;
  }
}