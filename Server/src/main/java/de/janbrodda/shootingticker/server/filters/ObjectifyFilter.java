package de.janbrodda.shootingticker.server.filters;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.googlecode.objectify.ObjectifyService;

import de.janbrodda.shootingticker.server.data.Competition;

/**
 * ObjectifyFilter, a ServletContextListener, is setup in web.xml to run before a JSP is run.  This is
 * required to let JSP's access Ofy.
 **/
public class ObjectifyFilter implements ServletContextListener {
  @Override
public void contextInitialized(ServletContextEvent event) {
    ObjectifyService.register(Competition.class);
  }

  @Override
public void contextDestroyed(ServletContextEvent event) {
    // App Engine does not currently invoke this method.
  }
}
