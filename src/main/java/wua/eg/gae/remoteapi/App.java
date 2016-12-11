package wua.eg.gae.remoteapi;

import java.io.IOException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.tools.remoteapi.RemoteApiInstaller;
import com.google.appengine.tools.remoteapi.RemoteApiOptions;

public class App {
  public static void main(String[] args) throws IOException {
  	String serverString; // e.g. xxxx.appspot.com
  	if (args.length == 0) {
  		serverString ="localhost";
  		System.out.println("Running Remote API against localhost");
  	} else {
  		serverString = args[0];
  		System.out.println("Running Remote API against " + args[0]);
  	}
  	
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
    	installer.install(options);
      DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
      System.out.println("Key of new entity is " + ds.put(new Entity("Hello Remote API!")));
      installer.uninstall();
    } 
    catch (Exception e) {
    	System.out.println("Exception: " + e.getMessage());
    }	
  }
}
