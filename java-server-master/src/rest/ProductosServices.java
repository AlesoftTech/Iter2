package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.ProductosTM;
import tm.RotondAndesTM;
import vos.Producto;

@Path("productos")
public class ProductosServices {
	/**
	 * Atributo que usa la anotacion @Context para tener el ServletContext de la conexion actual.
	 */
	@Context
	private ServletContext context;

	/**
	 * Metodo que retorna el path de la carpeta WEB-INF/ConnectionData en el deploy actual dentro del servidor.
	 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
	 */
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}


	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getProductos() {
		ProductosTM tm = new ProductosTM(getPath());
		List<Producto> productos;
		try {
			productos = tm.darProductos();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(productos).build();
	}
	
	@GET
	@Path("/restaurantes/{id: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getProductosRestaurante(@PathParam("id") Long id) {
		ProductosTM tm = new ProductosTM(getPath());
		List<Producto> productos;
		try {
			productos = tm.darProductosRestaurante(id);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(productos).build();
	}
	@GET
	@Path("/categorias/{id: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getProductosCategoria(@PathParam("id") Long id) {
		ProductosTM tm = new ProductosTM(getPath());
		List<Producto> productos;
		try {
			productos = tm.darProductosCategoria(id);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(productos).build();
	}
	@GET
	@Path("/precio/{precioMenor: \\d+}/{precioMayor: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getProductosPrecios(@PathParam("precioMenor") Long pMenor, @PathParam("precioMayor") Long pMayor) {
		ProductosTM tm = new ProductosTM(getPath());
		List<Producto> productos;
		try {
			productos = tm.darProductosPrecio(pMenor, pMayor);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(productos).build();
	}
	@GET
	@Path("/mas-ofrecidos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getProductosMasOfrecidos(){
		ProductosTM tm = new ProductosTM(getPath());
		List<Producto> ofrecidos;
		try
		{
			ofrecidos = tm.darPoductosMasOfrecidos();
			return Response.status( 200 ).entity( ofrecidos ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
}
