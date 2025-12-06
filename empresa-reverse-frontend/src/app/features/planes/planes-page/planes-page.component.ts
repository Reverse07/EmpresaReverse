import { Component, OnInit } from '@angular/core';
import { Plan } from '../../../models/plan.model';
import { PlanService } from '../../../services/plan.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-planes-page',
  imports: [CommonModule, FormsModule],
  templateUrl: './planes-page.component.html',
  styleUrl: './planes-page.component.css'
})
export class PlanesPageComponent implements OnInit {

  planes: Plan[] = [];
  loading: boolean = true;
  error: string | null = null;
  
  // Variables para el modal
  showModal: boolean = false;
  selectedPlan: Plan | null = null;
  
  // Datos del formulario
  formData = {
    nombre: '',
    email: '',
    telefono: '',
    empresa: '',
    mensaje: ''
  };

  constructor(private planService: PlanService) { }

  ngOnInit(): void {
    this.planService.getPlanes().subscribe({
      next: (data) => {
        console.log('Planes recibidos:', data);
        this.planes = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error al cargar planes:', err);
        this.error = 'No se pudieron cargar los planes.';
        this.loading = false;
      }
    });
  }

  // Abrir modal de contratación
  contratar(plan: Plan): void {
    this.selectedPlan = plan;
    this.showModal = true;
    document.body.style.overflow = 'hidden'; // Evitar scroll del body
  }

  // Cerrar modal
  closeModal(): void {
    this.showModal = false;
    this.selectedPlan = null;
    this.resetForm();
    document.body.style.overflow = 'auto';
  }

  // Enviar formulario
  submitForm(): void {
    if (!this.selectedPlan) return;

    // Validar que los campos requeridos estén completos
    if (!this.formData.nombre || !this.formData.email || !this.formData.telefono) {
      alert('Por favor completa todos los campos requeridos');
      return;
    }

    console.log('Formulario enviado:', {
      plan: this.selectedPlan.nombre,
      ...this.formData
    });

    // Crear mensaje para WhatsApp
    const mensaje = `*Solicitud de Contratación - ${this.selectedPlan.nombre}*

📋 *Datos del Cliente:*
- Nombre: ${this.formData.nombre}
- Email: ${this.formData.email}
- Teléfono: ${this.formData.telefono}
- Empresa: ${this.formData.empresa || 'No especificada'}

💰 *Plan seleccionado:*
- ${this.selectedPlan.nombre}
- Precio: $${this.selectedPlan.precioFinal.toFixed(2)}

📝 *Mensaje adicional:*
${this.formData.mensaje || 'Ninguno'}`;

    const whatsappUrl = `https://wa.me/51965391256?text=${encodeURIComponent(mensaje)}`;
    window.open(whatsappUrl, '_blank');

    // Aquí puedes también guardar en tu backend si lo deseas
    // this.http.post('http://localhost:8080/api/contrataciones', {
    //   plan: this.selectedPlan,
    //   cliente: this.formData
    // }).subscribe(...)

    alert('¡Solicitud enviada! Te contactaremos pronto.');
    this.closeModal();
  }

  // Resetear formulario
  resetForm(): void {
    this.formData = {
      nombre: '',
      email: '',
      telefono: '',
      empresa: '',
      mensaje: ''
    };
  }
}