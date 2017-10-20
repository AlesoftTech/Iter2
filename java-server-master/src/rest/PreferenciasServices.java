package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.RotondAndesTM;
import vos.PreferenciaUsuarioCategoria;
import vos.PreferenciaUsuarioPrecio;
import vos.PreferenciaUsuarioZona;

@Path("preferencias")
public class PreferenciasServices {
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
	@Path("/categorias")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPreferenciasCategorias(){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<PreferenciaUsuarioCategoria> preferencias;
		try
		{
			preferencias = tm.darPreferenciasCategoria();
			return Response.status( 200 ).entity( preferencias ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path("/precios")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPreferenciasPrecios(){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<PreferenciaUsuarioPrecio> preferencias;
		try
		{
			preferencias = tm.darPreferenciasPrecio();
			return Response.status( 200 ).entity( preferencias ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path("/zonas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPreferenciasZonas(){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<PreferenciaUsuarioZona> preferencias;
		try
		{
			preferencias = tm.darPreferenciasZona();
			return Response.status( 200 ).entity( preferencias ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
}
