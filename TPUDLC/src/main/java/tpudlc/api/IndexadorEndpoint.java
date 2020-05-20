package tpudlc.api;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import tpudlc.commons.exceptions.TechnicalException;
import tpudlc.negocio.Indexador;

@Path("/indexar")
public class IndexadorEndpoint {
    
    @Inject private Indexador indexador;
    
    @GET
    @Path("/")
    public Response lanzarIndexacion() {
        try {
        /*
        Thread thread = new Thread(new Runnable() {
            public void run() {
                indexador.indexar();
            }
        });
        thread.start();*/
            indexador.indexar();
        }catch (Exception ex) {
            throw new TechnicalException(ex);
        }
        return Response.ok("Proceso de indexacion finalizado. (200)").build();
    }
    
}
