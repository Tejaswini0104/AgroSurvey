package launch;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.*;
import servlet.filters.*;
import servlet.rest.*;
import utils.Resources;

public class App {

  // This is the place one needs to register the classes that need be instantiated.
  private static final String[] entryPoints = new String[] {};

  public static void main(String[] args) throws Exception {
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");

    Server jettyServer = new Server(8080);
    jettyServer.setHandler(context);

    ServletHolder jerseyServlet =
        context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
    jerseyServlet.setInitOrder(0);

    Resources.loadResources();
    //        Tells the Jersey Servlet which REST service/class to load.
    String services = "";
    for (int i = 0; i < entryPoints.length; i++) {
      String entryPoint = entryPoints[i];
      services = services + (i == 0 ? "" : ", ") + entryPoint;
      Logger.getAnonymousLogger().log(Level.SEVERE, entryPoint + "\n\n");
    }
    jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", services);
    Logger.getAnonymousLogger().log(Level.SEVERE, "\n\n ****** Initialized \n\n");

    try {
      jettyServer.start();
      jettyServer.join();
    } finally {
      jettyServer.destroy();
      Resources.cleanUp();
    }
  }
}
