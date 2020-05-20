package tpudlc.api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import tpudlc.classes.Documento;
import tpudlc.classes.daos.DocumentoDao;

@Path("/documentos")
public class DocumentosEndpoint {
    
    @Inject private DocumentoDao dao;
    
    @GET
    @Path("/todos")
    @Produces("application/json")
    public Response obtenerTodos() {
        
        List<Documento> lista = dao.findAll();
        
        return Response.ok(lista).build();
    }
    
    @GET
    @Path("/{codigo}")
    public Response obtenerUno(@PathParam("codigo") Integer codigo) {

        Documento resp = dao.retrieve(codigo);
        
        return Response.ok(resp).build();
    }
    
}
