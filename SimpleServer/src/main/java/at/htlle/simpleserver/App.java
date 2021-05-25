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
	    }
	    else
	    {
	    	rm = new ClassPathResourceManager(App.class.getClassLoader(), "at/htlle/simpleserver/staticfiles");
	    }
	    
    	Undertow server = Undertow.builder()
                .addHttpListener(portnumber, "localhost")
                .setHandler(new ResourceHandler(rm))
                .build();
    	
        server.start();
    }
}
