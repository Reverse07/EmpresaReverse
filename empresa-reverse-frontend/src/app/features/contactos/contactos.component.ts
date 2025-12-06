import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-contactos',
  imports: [FormsModule] ,
  templateUrl: './contactos.component.html',
  styleUrls: ['./contactos.component.css']
})
export class ContactosComponent {

  datos = {
    nombre: '',
    apellidos: '',
    correo: '',
    genero: '',
    comentario: ''
  };

  enviarFormulario() {
  if (
    !this.datos.nombre ||
    !this.datos.apellidos ||
    !this.datos.genero ||
    !this.datos.correo ||
    !this.datos.comentario
  ) {
    alert("Completa todos los campos antes de enviar.");
    return;
  }

  console.log("Datos enviados:", this.datos);
  alert("Formulario enviado correctamente");
}
  borrar() {
    this.datos = {
      nombre: '',
      apellidos: '',
      correo: '',
      genero: '',
      comentario: ''
    };
  }

}