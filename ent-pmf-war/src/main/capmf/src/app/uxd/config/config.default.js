'use strict';

//istanbul ignore next
/*jshint unused:false*/
var uxdConfig = {
    environment: 'local',
    web: {
        tabUrlPrefix: '',
        getTabUrl: function (prefix, tabUrl) {
            return prefix + tabUrl;
        }
    }
};
