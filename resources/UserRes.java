package resources;
import Controller.UserController;
import Model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.sql.SQLException;
import java.util.List;

import static jdk.nashorn.internal.runtime.PropertyDescriptor.GET;


/**
 * Created by Lasse on 27.06.2016.
 */


@Path("/userres")
public class UserRes {


        @GET
        @Produces(value = MediaType.APPLICATION_JSON)
        public User getUserAsJSON() {
            UserController uc = new UserController();
            User us = null;
            try{
                us = uc.getUserByID(-2147483648);
            }catch(SQLException e)
            {
                e.printStackTrace();
            }
            return us;
        }
    }

