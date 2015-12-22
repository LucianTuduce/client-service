package ro.fortech.rest;

import javax.ejb.Stateless;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Class used to ensure communication between the GUI and the back end.
 *
 */
@Stateless
@ApplicationPath("/rest")
public class ClientRest extends Application{

}
