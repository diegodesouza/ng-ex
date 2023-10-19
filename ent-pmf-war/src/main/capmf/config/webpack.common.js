const helpers = require('./helpers');
const buildUtils = require('./build-utils');

/**
 * Webpack Plugins
 *
 * problem with copy-webpack-plugin
 */
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CommonsChunkPlugin = require('webpack/lib/optimize/CommonsChunkPlugin');
const LoaderOptionsPlugin = require('webpack/lib/LoaderOptionsPlugin');
const ScriptExtHtmlWebpackPlugin = require('script-ext-html-webpack-plugin');
const ContextReplacementPlugin = require('webpack/lib/ContextReplacementPlugin');
const ProvidePlugin = require('webpack/lib/ProvidePlugin');
const MomentLocalesPlugin = require('moment-locales-webpack-plugin');

const isVendor = ({ resource }) => /node_modules/.test(resource);

/**
 * Webpack configuration
 *
 * See: http://webpack.github.io/docs/configuration.html#cli
 */
module.exports = {
  /**
   * The entry point for the bundle
   * Our Angular.js app
   *
   * See: http://webpack.github.io/docs/configuration.html#entry
   */
  entry: {
    polyfills: './src/polyfills.browser.ts',
    main:      './src/main.ts'
  },

  /**
   * Options affecting the resolving of modules.
   *
   * See: http://webpack.github.io/docs/configuration.html#resolve
   */
  resolve: {
    /**
     * An array of extensions that should be used to resolve modules.
     *
     * See: http://webpack.github.io/docs/configuration.html#resolve-extensions
     */
    extensions: ['.ts', '.js', '.json'],

    /**
     * An array of directory names to be resolved to the current directory
     */
    modules: [helpers.root('src'), helpers.root('node_modules')],
  },

  /**
   * Options affecting the normal modules.
   *
   * See: http://webpack.github.io/docs/configuration.html#module
   */
  module: {

    rules: [
      /**
       * Raw loader support for *.html
       * Returns file content as string
       *
       * See: https://github.com/webpack/raw-loader
       */
      {
        test: /\.html$/,
        use: 'raw-loader',
        exclude: [helpers.root('src/index.html')]
      },

      /**
       * Css Loaders
       *
       * Style-loader injects the styling through a style element.
       *
       * See: https://github.com/webpack-contrib/style-loader
       *
       * Css-loader goes through possible @import and url()
       * lookups within the matched files and treats them as a regular ES2015 import.
       *
       * See: https://github.com/webpack-contrib/css-loader
       *
       * PostCSS allows you to perform transformations over CSS through JavaScript plugins
       *
       * See: http://postcss.org/
       *
       * Autoprefixer adds vendor prefixes to CSS using values from Can I Use.
       *
       * See: https://github.com/postcss/autoprefixer
       *
       * Sass-loader Loads a SASS/SCSS file and compiles it to CSS.
       *
       * See: https://github.com/webpack-contrib/sass-loader
       */
      {
        test: /\.s?css$/,
        use: [
          'to-string-loader', 'style-loader', 'css-loader',
          {
            loader: 'postcss-loader',
            options: {
              ident: 'postcss',
              plugins: [require('autoprefixer')]
            }
          }, {
            loader: 'sass-loader'
          }
        ]
      },
      {
        test: /\.json$/,
        loader: 'json-loader'
      },
      {
        test: /\.ts$/,
        loader: 'ts-loader'
      },
      {
        test: /\.ts$/,
        exclude: /node_modules/,
        loader: 'string-replace-loader',
        query: { search: 'moduleId: module.id,', replace: '' },
        enforce: 'pre'
      },
      {
        test: /\.ts$/,
        exclude: /(node_modules)|(uxd)/,
        loader: 'tslint-loader',
        enforce: 'pre'
      },
      /**
       * File loader for supporting images, for example, in CSS files.
       */
      {
        test: /\.(otf|ttf|woff2?|eot|svg)(\?.*)?$/,
        use: [ 'file-loader?name=fonts/[name].[ext]' ]
      },
      {
        test: /\.(jpe?g|png|gif|svg)$/i,
        use: [ 'file-loader?name=images/[name].[ext]&limit=10000' ]
      }

    ],

  },

  /**
   * Add additional plugins to the compiler.
   *
   * See: http://webpack.github.io/docs/configuration.html#plugins
   */
  plugins: [
    /**
     * Plugin: HtmlWebpackPlugin
     * Description: Simplifies creation of HTML files to serve your webpack bundles.
     * This is especially useful for webpack bundles that include a hash in the filename
     * which changes every compilation.
     *
     * See: https://github.com/ampedandwired/html-webpack-plugin
     */
    new HtmlWebpackPlugin({
      template: 'src/index.html',
      title: buildUtils.DEFAULT_METADATA.title,
      inject: 'body',
      xhtml: true,
      chunksSortMode: function (a, b) {
        const entryPoints = ["inline","polyfills","vendor","main"];
        return entryPoints.indexOf(a.names[0]) - entryPoints.indexOf(b.names[0]);
      }
    }),

    /**
     * Plugin: CommonsChunkPlugin
     * Description: Shares common code between the pages.
     * It identifies common modules and put them into a commons chunk.
     *
     * See: https://webpack.github.io/docs/list-of-plugins.html#commonschunkplugin
     * See: https://github.com/webpack/docs/wiki/optimization#multi-page-app
     */
    new CommonsChunkPlugin({
      name: 'polyfills',
      minChunks: function (module) {
        return module.context && module.context.indexOf('node_modules') !== -1;
      }
    }),

    new CommonsChunkPlugin({
      name: 'main',
      async: true,
      children: true,
      deepChildren: true,
      minChunks: (module, count) => count >= 2 && isVendor(module)
    }),

    new ProvidePlugin({
      jQuery: 'jquery',
      $: 'jquery',
      jquery: 'jquery',
      jqueryui: 'jqueryui'
    }),

    /**
     * Plugin: ScriptExtHtmlWebpackPlugin
     * Description: Enhances html-webpack-plugin functionality
     * with different deployment options for your scripts including:
     *
     * See: https://github.com/numical/script-ext-html-webpack-plugin
     */
    new ScriptExtHtmlWebpackPlugin({
      sync: /inline|polyfills|vendor/,
      defaultAttribute: 'async',
      preload: [/polyfills|vendor|main/],
      prefetch: [/chunk/]
    }),

    /**
     * Plugin LoaderOptionsPlugin (experimental)
     *
     * See: https://gist.github.com/sokra/27b24881210b56bbaff7
     */
    new LoaderOptionsPlugin({
      options: {
        resolve: {},
        ts: {
          configFile: 'tsconfig.json'
        },
        tslint: {
          configuration: require('../tslint.json')
        }
      },
      debug: true
    }),

    new ContextReplacementPlugin(
      /angular(\\|\/)core(\\|\/)@angular/,
      helpers.root('./src'),
      {}
    ),

    // To strip all locales except “en”
    new MomentLocalesPlugin({localesToKeep: ['es-us']})
  ],

  /**
   * Include polyfills or mocks for various node stuff
   * Description: Node configuration
   *
   * See: https://webpack.github.io/docs/configuration.html#node
   */
  node: {
    global: true,
    crypto: 'empty',
    process: true,
    module: false,
    clearImmediate: false,
    setImmediate: false
  }
};
