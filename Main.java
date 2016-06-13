/**
 * Created by Lasse on 18.05.2016.
 */
import Controller.*;
import Model.*;
import java.util.Scanner;
import java.sql.SQLException;

public class Main {
    EventController controllEvent = new EventController();
    Event testEvent = controllEvent.getEventByID(9);

}
