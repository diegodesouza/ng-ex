import {MaskPhonePipe} from './mask-phone.pipe';

describe('Mask Phone Pipe', () => {
  let pipe: MaskPhonePipe;
  let testData: string;
  let piped: string;

  beforeEach(() => {
    pipe = new MaskPhonePipe();
  });

  it('should return a masked phone number', () => {
    // Arrange.
    let pipe = new MaskPhonePipe();
    testData = '0000000000';

    // Act.
    piped = pipe.transform(testData);

    // Assert.
    expect(piped).toEqual('(000) 000-0000');
  });
});
