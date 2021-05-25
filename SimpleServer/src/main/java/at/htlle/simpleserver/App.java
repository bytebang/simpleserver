package at.htlle.simpleserver;

import java.nio.file.Path;
import java.nio.file.Paths;

import io.undertow.Undertow;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.server.handlers.resource.PathResourceManager;
import io.undertow.server.handlers.resource.ResourceHandler;
import io.undertow.server.handlers.resource.ResourceManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
	    // Get the port where we should serve on
	    String port = System.getProperty("port");
	    int portnumber = (port == null) ? 8080 : Integer.parseInt(port);
	    
	    ResourceManager rm = null;
	    
	    // Add the ability to add a environmentvariable based configuration
	    String base = System.getProperty("wwwroot");

	    
	    if(base != null)
	    {
		    Path p = Paths.get(base);
	    	rm = new PathResourceManager(p);
	    	
	    	System.out.println("Will serve content of directory " + base + " on port " + port);
	    }
	    else
	    {
	    	rm = new ClassPathResourceManager(App.class.getClassLoader(), "at/htlle/simpleserver/staticfiles");
	    	System.out.println("Will serve compiled content on port " + port);
	    }
	    
	    ResourceHandler handler = new ResourceHandler(rm);
	    
	    // Directorylisting anyone ?
	    String allowDirectoryListing = System.getProperty("allowDirectoryListing");
	    if(Boolean.parseBoolean(allowDirectoryListing) == true)
	    {
		    handler.setDirectoryListingEnabled(true);
		    System.out.println("Will allow directory listing");
	    }
	    

	    // Start the server
    	Undertow server = Undertow.builder()
                .addHttpListener(portnumber, "localhost")
                .setHandler(handler)
                .build();
    	
        server.start();
    }
}
