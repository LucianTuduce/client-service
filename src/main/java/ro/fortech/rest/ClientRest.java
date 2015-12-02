package ro.fortech.rest;

import javax.ejb.Stateless;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@Stateless
@ApplicationPath("/rest")
public class ClientRest extends Application{

}
