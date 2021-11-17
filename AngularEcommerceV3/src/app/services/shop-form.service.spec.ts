import { TestBed } from '@angular/core/testing';

import { ShopFormService } from './shop-form.service';

describe('ShopFormService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ShopFormService = TestBed.get(ShopFormService);
    expect(service).toBeTruthy();
  });
});
