import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClienteTarjetaComponent } from './cliente-tarjeta.component';

describe('ClienteTarjetaComponent', () => {
  let component: ClienteTarjetaComponent;
  let fixture: ComponentFixture<ClienteTarjetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ClienteTarjetaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClienteTarjetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
