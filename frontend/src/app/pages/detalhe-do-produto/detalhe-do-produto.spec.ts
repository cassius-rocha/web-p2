import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalheDoProduto } from './detalhe-do-produto';

describe('DetalheDoProduto', () => {
  let component: DetalheDoProduto;
  let fixture: ComponentFixture<DetalheDoProduto>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetalheDoProduto]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetalheDoProduto);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
