import { TestBed } from '@angular/core/testing';

import { TestScoreService } from './test-score.service';

describe('TestScoreService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TestScoreService = TestBed.get(TestScoreService);
    expect(service).toBeTruthy();
  });
});
