package tpudlc.api;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import tpudlc.classes.Documento;
import tpudlc.negocio.Buscador;


@Path("/buscar")
public class BuscadorEndpoint {
    
    @Inject private Buscador buscador;
    
    @GET
    @Path("/{query}")
    @Produces("application/json")
    public Response buscar(@PathParam("query") String query){
        
        List<Documento> resp = buscador.buscar(query);

        return Response.ok(resp).build();
    }
    
}
