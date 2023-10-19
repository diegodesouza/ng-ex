import {browser, by, element} from 'protractor';

describe('General Info Component End to End', () => {
  beforeEach(async() => {
    await browser.get('http://localhost:3000/dashboard/generalinfo');
  });

  it('should return page title', async() => {
    // Arrange.
    const subject = await browser.getTitle();

    // Act.

    // Assert.
    expect(subject).toEqual('PMF');
  });

  it('should get a field', async() => {
    // Arrange.
    const subject = await element(by.id('effective-date'));
    const result = '01/01/2018';

    // Act.
    subject.sendKeys(result);

    // Assert.
    expect(await element(by.id('effective-date')).getAttribute('value')).toContain(result);
  });
});
