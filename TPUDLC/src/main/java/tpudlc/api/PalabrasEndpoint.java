package tpudlc.api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import tpudlc.classes.Palabra;
import tpudlc.classes.daos.PalabraDao;

@Path("/palabras")
public class PalabrasEndpoint {
    
    @Inject private PalabraDao dao;
    
    @GET
    @Path("/todas")
    @Produces("application/json")
    public Response obtenerTodos() {
        
        List<Palabra> lista = dao.findAll();
        
        return Response.ok(lista).build();
    }
    
    @GET
    @Path("/{codigo}")
    public Response obtenerUno(@PathParam("codigo") Integer codigo) {

        Palabra resp = dao.retrieve(codigo);
        
        return Response.ok(resp).build();
    }
    
}