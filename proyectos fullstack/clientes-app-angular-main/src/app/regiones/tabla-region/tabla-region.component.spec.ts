import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TablaRegionComponent } from './tabla-region.component';

describe('TablaRegionComponent', () => {
  let component: TablaRegionComponent;
  let fixture: ComponentFixture<TablaRegionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TablaRegionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TablaRegionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
