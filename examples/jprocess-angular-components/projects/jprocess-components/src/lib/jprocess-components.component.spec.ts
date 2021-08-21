import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JprocessComponentsComponent } from './jprocess-components.component';

describe('JprocessComponentsComponent', () => {
  let component: JprocessComponentsComponent;
  let fixture: ComponentFixture<JprocessComponentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JprocessComponentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JprocessComponentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
