
var argscheck = require('cordova/argscheck'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec');

var AudioSettings = function() {

};

AudioSettings.get = function() {
    exec(null, null, "AudioSettings", "get", []);
};

AudioSettings.set = function(obj) {
    exec(null, null, "AudioSettings", "set", [obj]);
};

module.exports = AudioSettings;