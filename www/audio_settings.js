
var argscheck = require('cordova/argscheck'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec');

var AudioSettings = function() {

};

AudioSettings.get = function(success, fail) {
    exec(success, fail, "AudioSettings", "get", []);
};

AudioSettings.set = function(obj, success, fail) {
    exec(success, fail, "AudioSettings", "set", [obj]);
};

module.exports = AudioSettings;