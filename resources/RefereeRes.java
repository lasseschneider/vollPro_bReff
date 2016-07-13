package resources;
import Controller.UserController;
import Model.Referee;
import Model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.sql.SQLException;
import java.util.List;

import static jdk.nashorn.internal.runtime.PropertyDescriptor.GET;

/**
 * Created by Lasse on 13.07.2016.
 */
@Path("/refereeres")
public class RefereeRes{

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public String getResourceName(){
        return "RefereeRes";
    }



    @GET
    @Path("/allRefs")
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<Referee> getAllReferees(){
    List<Referee> result = null;
        //ToDo: RefereeController implementieren.
        //RefereeController rc = new RefereeController();
    return result;
    }


}
