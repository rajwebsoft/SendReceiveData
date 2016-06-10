var exec = require('cordova/exec');

function SendReceiveData() {}
SendReceiveData.prototype.getUsedData = function(aString,onSuccess,onFailed){	 
	 exec(onSuccess,onFailed, "SendReceiveData",aString,[]);	 
}

var sendReceiveData = new SendReceiveData();
module.exports = sendReceiveData;