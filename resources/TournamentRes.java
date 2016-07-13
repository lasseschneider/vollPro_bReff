package resources;
import Controller.TournamentController;
import Controller.UserController;
import Model.Tournament;
import Model.User;

import javax.print.attribute.standard.MediaPrintableArea;
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
@Path("tournamentres")
public class TournamentRes {
    @GET
    @Produces(value= MediaType.APPLICATION_JSON)
    public String getResourceName(){
        return "Tournaments";
    }
    @GET
    @Path("/allTournaments")
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<Tournament> getAllTournaments() {
        TournamentController tc = new TournamentController();
        List<Tournament> resultList = null;
        //ToDo: getALlTournaments implementieren

        //tc.getAllTournaments();
        return resultList;
    }

}
