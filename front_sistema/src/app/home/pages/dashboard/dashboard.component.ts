import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  visitaFormulario: FormGroup = this.fb.group({
    cedula: ['', [Validators.required]],
    nombre: ['', [Validators.required]],
    apellido: ['', [Validators.required]],
    razon: ['', [Validators.required]],
    contacto: ['', [Validators.required]]
  });

  visitante: any[] = [];
  editIndex: number | null = null; 

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.cargarVisitas();
  }

  guardar() {
    if (this.visitaFormulario.valid) {
      const data = this.visitaFormulario.value;

      if (this.editIndex !== null) {
        const localStorageVisitas = JSON.parse(localStorage.getItem('visitors') || '[]');
        localStorageVisitas[this.editIndex] = data;
        localStorage.setItem('visitors', JSON.stringify(localStorageVisitas));
        this.editIndex = null;
      } else {

        const localStorageVisitas = JSON.parse(localStorage.getItem('visitors') || '[]');
        localStorageVisitas.push(data);
        localStorage.setItem('visitors', JSON.stringify(localStorageVisitas));
      }

      this.visitante = JSON.parse(localStorage.getItem('visitors') || '[]');
      alert('Información guardada en local storage');
      this.visitaFormulario.reset();
    } else {
      alert('Por favor complete todos los campos correctamente');
    }
  }

  cargarVisitas() {
    this.visitante = JSON.parse(localStorage.getItem('visitors') || '[]');
  }

  editar(index: number) {
    const visitor = this.visitante[index];
    this.visitaFormulario.setValue({
      cedula: visitor.cedula,
      nombre: visitor.nombre,
      apellido: visitor.apellido,
      razon: visitor.razon,
      contacto: visitor.contacto
    });
    this.editIndex = index;
  }

  eliminar(index: number) {
    const localStorageVisitas = JSON.parse(localStorage.getItem('visitors') || '[]');
    localStorageVisitas.splice(index, 1);
    localStorage.setItem('visitors', JSON.stringify(localStorageVisitas));
    this.visitante = localStorageVisitas;
  }
}
