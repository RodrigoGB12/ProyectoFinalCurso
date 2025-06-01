import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlatoTarjetaComponent } from './plato-tarjeta.component';

describe('PlatoTarjetaComponent', () => {
  let component: PlatoTarjetaComponent;
  let fixture: ComponentFixture<PlatoTarjetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PlatoTarjetaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PlatoTarjetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
