package de.janbrodda.shootingticker.server.filters;

import com.googlecode.objectify.ObjectifyService;

import de.janbrodda.shootingticker.server.data.Competition;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

/**
 * ObjectifyFilter, a ServletContextListener, is setup in web.xml to run before a JSP is run.  This is
 * required to let JSP's access Ofy.
 **/
public class ObjectifyFilter implements ServletContextListener {
  public void contextInitialized(ServletContextEvent event) {
    // This will be invoked as part of a warmup request, or the first user request if no warmup
    // request.
    ObjectifyService.register(Competition.class);
    //ObjectifyService.register(Team.class);
    //ObjectifyService.register(Shooter.class);
    //ObjectifyService.register(Shot.class);
  }

  public void contextDestroyed(ServletContextEvent event) {
    // App Engine does not currently invoke this method.
  }
}
