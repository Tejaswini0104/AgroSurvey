package server.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path(ResourcesPath.LOGIN)
public class Login {
  public Login() {}

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String badGet() throws ServletException, IOException {
    return "Unsupported";
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response handleRequest(String jsonRequest) throws Exception {

    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(jsonRequest);
    Iterator<String> iterator = jsonNode.fieldNames();
    while (iterator.hasNext()) {
      System.out.println("fieldName:  " + iterator.next());
    }
    String uname = "100";
    Response.ResponseBuilder responseBuilder = Response.ok();
    responseBuilder.cookie(getUserCookie(uname));
    return responseBuilder.build();
  }

  public NewCookie getUserCookie(String uname) throws Exception {
    return new NewCookie("AuthToken", "HelloWorld");
  }
}
