const testWebpackConfig = require('./webpack.test.js');
const autowatch = process.env.npm_lifecycle_script.indexOf('--auto-watch') !== -1;

module.exports = function (config) {

  const configuration = {

    /**
     * Base path that will be used to resolve all patterns (e.g. files, exclude).
    */
    basePath: '',

    /**
     * Frameworks to use
     *
     * available frameworks: https://npmjs.org/browse/keyword/karma-adapter
     */
    frameworks: ['jasmine'],

    /**
     * List of files to exclude.
    */
    exclude: [],

    client: {
      captureConsole: false
    },

    /**
     * List of files / patterns to load in the browser
     *
     * we are building the test environment in ./spec-bundle.js
     */
    files: [
      { pattern: './config/spec-bundle.js', watched: false }
    ],

    /**
     * By default all assets are served at http://localhost:[PORT]/base/
     */
    // proxies: {
    //   "/common/": "/base/src/common/"
    // },

    /**
     * Preprocess matching files before serving them to the browser
     * available preprocessors: https://npmjs.org/browse/keyword/karma-preprocessor
     */
    // skip coverage in watch mode
    preprocessors: { './config/spec-bundle.js': autowatch ? ['webpack', 'sourcemap'] : ['coverage', 'webpack', 'sourcemap'] },

    // Webpack please don't spam the console when running in karma!
    webpackServer: { noInfo: true },

    typescriptPreprocessor: {
      // options passed to the typescript compiler
      options: {
        sourceMap: true, // (optional) Generates corresponding .map file.
        noResolve: false, // (optional) Skip resolution and preprocessing.
      },

      // transforming the filenames
      transformPath: function(path) {
        return path.replace(/\.ts$/, '.js');
      }
    },
    /**
     * Webpack Config at ./webpack.test.js
     */
    webpack: testWebpackConfig,

    coverageReporter: {
      type: 'in-memory'
    },

    remapCoverageReporter: {
      'text-summary': null,
      json: './coverage/coverage.json',
      html: './coverage/html'
    },

    /**
     * Webpack please don't spam the console when running in karma!
     */
    webpackMiddleware: {
      /**
       * webpack-dev-middleware configuration
       * i.e.
       */
      noInfo: true,
      /**
       * and use stats to turn off verbose output
       */
      stats: {
        /**
         * options i.e.
         */
        chunks: false
      }
    },

    /**
     * Test results reporter to use
     *
     * possible values: 'dots', 'progress'
     * available reporters: https://npmjs.org/browse/keyword/karma-reporter
     */
    reporters: ['mocha', 'progress', 'coverage', 'remap-coverage'],

    /**
     * Web server port.
     */
    port: 9876,

    /**
     * enable / disable colors in the output (reporters and logs)
     */
    colors: true,

    /**
     * Level of logging
     * possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
     */
    logLevel: 'INFO',

    /**
     * enable / disable watching file and executing tests whenever any file changes
     */
    autoWatch: false,

    junitReporter: {
      outputDir: 'test-reports'
    },

    /**
     * start these browsers
     * available browser launchers: https://npmjs.org/browse/keyword/karma-launcher
     */
    browsers: [
      'Chrome'
    ],

    customLaunchers: {
      ChromeTravisCi: {
        base: 'Chrome',
        flags: ['--no-sandbox']
      }
    },

    /**
     * Continuous Integration mode
     * if true, Karma captures browsers, runs the tests and exits
     */
    singleRun: true
  };

  /**
   * skip coverage in watch mode
   */
  if (!autowatch) {
    configuration.reporters.push('coverage');
    configuration.coverageReporter = {
      dir : 'coverage/',
      reporters: [
        { type: 'text-summary' },
        { type: 'json' },
        { type: 'html' }
      ]
    };
  }

  config.set(configuration);
};
