require('ts-node/register');

const helpers = require('./helpers');

exports.config = {
  baseUrl: 'http://localhost:3000/',

  /**
   * Use `npm run e2e`
   */
  specs: [
    helpers.root('src/**/**.e2e.ts'),
    helpers.root('src/**/*.e2e.ts')
  ],

  framework: 'jasmine',

  allScriptsTimeout: 11000,

  jasmineNodeOpts: {
    showTiming: true,
    showColors: true,
    isVerbose: false,
    includeStackTrace: false,
    defaultTimeoutInterval: 40000
  },

  directConnect: true,
  capabilities: {
    browserName: 'chrome',
    chromeOptions: {
      args: [ "--headless", "--disable-gpu", "--window-size=800x600", "--no-sandbox" ]
    }
  },

  onPrepare: function() {
    browser.ignoreSynchronization = true;
    require("zone.js/dist/zone-node");
    require('ts-node').register({
      project: 'tsconfig.e2e.json'
    });
  },

   SELENIUM_PROMISE_MANAGER: false,
};
