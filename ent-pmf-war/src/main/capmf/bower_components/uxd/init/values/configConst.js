'use strict';

/**
 * @author Lakmal Molligoda
 * @description injectable application configuration components
 * stores application config key/values as injectable angular constant
 * supports environment specific config values
 * you can't change this config through other components, only allows get (no set)
 */
  /*istanbul ignore next*/
define(['internal_packages/uxd/init/initModule'], function (mod) {

  /*global uxdConfig:false */
  /*global uxdConfigSub:false */
  angular.merge(uxdConfig, uxdConfigSub);

  //setup as angular constant, this allows the config to be injected into providers and module configure method.
  mod.constant('uxdConfigConst', uxdConfig);
});
