package practica.quarkus;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.*;

@Path("/temp")
public class TempResource {

    private TempService ts;

    @Inject
    public TempResource(TempService ts) {
        this.ts = ts;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Temp newTemp(Temp temp) {
        ts.agregar(temp);
        return temp;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Temp> list(){
//        return Arrays.asList(
//                new Temp("Munich", 5, 15),
//                new Temp("Amsterdam", 3, 11),
//                new Temp("Brucelas", 16, 19),
//                new Temp("Cordoba", 30, 33)
//        );
        //return Collections.unmodifiableList(nuevas);
        return  ts.obtenerTemp();
    }

    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/single")
    public Temp medicion(){
       return new Temp("Sevilla", 8, 19);
    }*/

    @DELETE
    @Path("/delete")
    public String deleteTemp(@QueryParam("i") int i) {
        ts.borrarTemp(i);
        return "Se eliminó con exito";
    }

    @GET
    @Path("/max")
    @Produces(MediaType.TEXT_PLAIN)
    public Response maxima() {
        //ts.obtenerMaxima();
        //return Integer.toString(ts.obtenerMaxima());
        if (ts.isEmpty()){
            return Response.status(404).entity("No hay temperaturas").build();
        }else {
            int maxima = ts.obtenerMaxima();
            return Response.ok(Integer.toString(ts.obtenerMaxima())).build();
        }
    }

    @GET
    @Path("/cd/{ciudad}")
    @Produces(MediaType.APPLICATION_JSON)
    public Temp findCiudad(@PathParam("ciudad") String ciudad) {
        return ts.sacarTemp(ciudad).orElseThrow(()->new NoSuchElementException("No hay registros para la ciudad " + ciudad));
    }
}
