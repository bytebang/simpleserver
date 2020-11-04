package at.htlle.simpleserver;

import io.undertow.Undertow;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.server.handlers.resource.ResourceHandler;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	ClassPathResourceManager crm = new ClassPathResourceManager(App.class.getClassLoader(), "at/htlle/simpleserver/staticfiles");
    	
    	Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(new ResourceHandler(crm))
                .build();
    	
        server.start();
    }
}
