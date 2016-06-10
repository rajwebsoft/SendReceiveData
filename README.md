SendReceiveData Plugins
com.itfosters.SendReceiveData
This plugin provides information about the app used data with recieive and send, There is no guarantee that the plugin returns the send receive data

Cordova Version

This plugin is tested on cordova version: 3.5.0
Installation

cordova plugin add https://github.com/rajwebsoft/SendReceiveData.git
Supported Platforms

- Android

Example of Use

SendReceiveData.getUsedData("com.itfosters.localnews",onLocSuccess,onLocError);    

function onLocSuccess(res) { alert(res.received); }

function onLocError(error) { alert('App Error: ' + error); }

Results : 
{
  application_label: "LocalNews",
  package_uid: 10290,
  packagename: "com.itfosters.localnews",
  received: "112.88",
  send: "5.77",
  total: "118.65"
}
