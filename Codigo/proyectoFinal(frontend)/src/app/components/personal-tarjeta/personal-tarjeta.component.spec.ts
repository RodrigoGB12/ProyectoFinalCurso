import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonalTarjetaComponent } from './personal-tarjeta.component';

describe('PersonalTarjetaComponent', () => {
  let component: PersonalTarjetaComponent;
  let fixture: ComponentFixture<PersonalTarjetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PersonalTarjetaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PersonalTarjetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
