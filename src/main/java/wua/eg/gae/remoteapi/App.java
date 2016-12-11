package wua.eg.gae.remoteapi;

import java.io.IOException;
import com.google.appengine.api.modules.ModulesService;
import com.google.appengine.api.modules.ModulesServiceFactory;
import com.google.appengine.tools.remoteapi.RemoteApiInstaller;
import com.google.appengine.tools.remoteapi.RemoteApiOptions;

public class App {
  public static void main(String[] args) throws IOException {
    // Get GAE host
  	String serverString; // e.g. xxxx.appspot.com
  	if (args.length == 0) {
  		serverString ="localhost";
  		System.out.println("Running Remote API against localhost");
  	} else {
  		serverString = args[0];
  		System.out.println("Running Remote API against " + args[0]);
  	}
  	
    // Install Remote API and call a GAE service
    RemoteApiOptions options;
    if (serverString.equals("localhost")) {
      options = new RemoteApiOptions().server(serverString,
        8080).useDevelopmentServerCredential();
    } else {
      options = new RemoteApiOptions().server(serverString,
        443).useApplicationDefaultCredential();
    }
    RemoteApiInstaller installer = new RemoteApiInstaller();
    try {
      installer.install(options); // do this only once throughout the code
      ModulesService ms = ModulesServiceFactory.getModulesService();
      System.out.println("Current Module/Version = "
        + ms.getCurrentModule() + "/"
        + ms.getDefaultVersion(ms.getCurrentModule()));
      installer.uninstall(); // do this only once throughout the code
    } 
    catch (Exception e) {
    	System.out.println("Exception: " + e.getMessage());
    }
  }
}
