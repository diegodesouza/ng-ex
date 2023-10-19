// /**
//  * Look in ./config folder for webpack.dev.js
//  */
switch (process.env.NODE_ENV) {
  case 'local':
  case 'feature':
    module.exports = require('./config/webpack.dev');
    break;
  case 'sit':
  case 'perf':
  case 'uat':
  case 'int':
  case 'prod':
  case 'production':
    module.exports = require('./config/webpack.prod');
    break;
  case 'test':
  case 'testing':
    module.exports = require('./config/webpack.test');
    break;
  case 'localhost':
    module.exports = require('./config/webpack.local');
    break;
  case 'dev':
  case 'development':
  default:
    module.exports = require('./config/webpack.dev');
}
