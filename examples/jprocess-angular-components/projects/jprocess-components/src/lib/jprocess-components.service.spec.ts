import { TestBed } from '@angular/core/testing';

import { JprocessComponentsService } from './jprocess-components.service';

describe('JprocessComponentsService', () => {
  let service: JprocessComponentsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JprocessComponentsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
