package tpudlc.api;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import tpudlc.negocio.Buscador;


@Path("/buscar")
public class BuscadorEndpoint {
    
    @Inject private Buscador buscador;
    
    @GET
    @Path("/{query}")
    public Response buscar(@PathParam("query") String query){
        return Response.ok().build();
    }
    
}
