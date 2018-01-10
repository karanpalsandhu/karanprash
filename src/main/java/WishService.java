/**
 * Created by kasandhu on 08/01/18.
 */

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/save")
public class WishService {

    public static Map<String, String> map = new HashMap<String, String>();

    @Path("/new")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createWish(@FormParam("name") String name,
                                  @FormParam("message") String message,
                                  @FormParam("email") String email,
                                  @FormParam("wish") String wish) {
        map.put(name + "%" + email, wish + "%" + message);
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @Path("/get")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getWish() {

        //create an HTML here...
        StringBuffer s = new StringBuffer();
        for(Map.Entry entry : map.entrySet()){
            String[] l1 = entry.getKey().toString().split("\\%");
            String[] l2 = entry.getValue().toString().split("\\%");
            s.append(" name: " + l1[0]);
            s.append(" email: " + l1[1]);

            s.append(" wish: " + l2[0]);
            s.append(" message: " + l2[1]);
        }
        return s.toString();
    }
}