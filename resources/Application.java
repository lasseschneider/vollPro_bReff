package resources;

import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lasse on 04.07.2016.
 */
@ApplicationPath( "api" )
public class Application extends javax.ws.rs.core.Application {
    @Override
    public Set<Class<?>> getClasses()
    {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add( UserRes.class );
        return super.getClasses();
    }

}
