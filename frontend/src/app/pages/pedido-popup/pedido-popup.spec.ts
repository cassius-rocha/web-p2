import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PedidoPopup } from './pedido-popup';

describe('PedidoPopup', () => {
  let component: PedidoPopup;
  let fixture: ComponentFixture<PedidoPopup>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PedidoPopup]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PedidoPopup);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
