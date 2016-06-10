import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;

import java.util.List;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.TrafficStats;

import android.util.Log;
import android.provider.Settings;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
public class SendReceiveData extends CordovaPlugin {
 	 
	public SendReceiveData() {}
	 
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);		
	}
	 
	public boolean execute(final String action, JSONArray args,final CallbackContext callbackContext) throws JSONException {
	      	
		String requeststr = action;
		this.getPakagesInfoUsingHashMap(requeststr,callbackContext);
		return true;
	}


	public void getPakagesInfoUsingHashMap(String pkgname,final CallbackContext callbackContext) {
	    final PackageManager pm = cordova.getActivity().getPackageManager();
	    List<ApplicationInfo> packages = pm.getInstalledApplications(0);
	    boolean check=false;	    

	    JSONObject itfitem = new JSONObject();

	    for (ApplicationInfo packageInfo : packages) {
	        
	        int UID = packageInfo.uid;
	        String package_name = packageInfo.packageName;
	        ApplicationInfo app = null;
	        try {
	            app = pm.getApplicationInfo(package_name, 0);
	        } catch (NameNotFoundException e) {
	            e.printStackTrace();
	        }
	        String name = (String) pm.getApplicationLabel(app);
	        double received = (double) TrafficStats.getUidRxBytes(UID)/ (1024 * 1024);
	        double send = (double) TrafficStats.getUidTxBytes(UID) / (1024 * 1024);
	        double total = received + send;
	        
	        if(package_name.matches("(.*)"+pkgname+"(.*)")){
	        	check=true;
	        	
	        	try{
	        		itfitem.put("package_uid",UID);
	        		itfitem.put("application_label",name);
	        		itfitem.put("packagename",package_name);
	        		itfitem.put("received",String.format( "%.2f",received));
	        		itfitem.put("send",String.format( "%.2f",send));
	        		itfitem.put("total",String.format( "%.2f",total));
	        	}catch(Exception e){}	        	        	
	        	break;	        	
	        }
	    }
	    
	    if(check==true){
	    	callbackContext.success(itfitem);
	    }else{
	    	callbackContext.error("Package name invilid.");
	    }
	}
}