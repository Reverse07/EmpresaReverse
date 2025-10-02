import { Component } from '@angular/core';
import { Plan } from '../../../models/plan.model';
import { PlanService } from '../../../services/plan.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-planes-page',
  imports: [CommonModule],
  templateUrl: './planes-page.component.html',
  styleUrl: './planes-page.component.css'
})
export class PlanesPageComponent {

  planes: Plan[] = [];

  constructor(private planService: PlanService) { }

  ngOnInit(): void {
    this.planService.getPlanes().subscribe(data => {
      this.planes = data;
    });
  }


}
