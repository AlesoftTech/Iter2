package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.RotondAndesTM;
import vos.PreferenciaUsuarioCategoria;
import vos.PreferenciaUsuarioPrecio;
import vos.PreferenciaUsuarioZona;
import vos.Producto;
import vos.Restaurante;
import vos.Usuario;
import vos.UsuarioClientePref;
import vos.Zona;

@Path("usuarios")
public class UsuariosServices {
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
	
	@POST
	@Path("/{id: \\d+}/productos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProductos(@PathParam("id") Long id, Producto producto) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try 
		{
			tm.addProducto(id, producto);
		} 
		catch (Exception e)
		{	
			return Response.status(500).entity(doErrorMessage(e)).build();
		}

		return Response.status(200).entity(producto).build();
	}
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuarios(){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Usuario> usuarios;
		try
		{
			usuarios = tm.darUsuarios();
			return Response.status( 200 ).entity( usuarios ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUsuario(Usuario usuario){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try{
			tm.agregarUsuario(usuario);
		}catch(Exception e){
			Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(usuario).build();
	}
	
	@GET
	@Path("/administradores")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAdministradores(){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Usuario> usuarios;
		try
		{
			usuarios = tm.darAdministradores();
			return Response.status( 200 ).entity( usuarios ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path("/{id: \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuarioPorID(@PathParam("id") Long id){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		Usuario usuario;
		try
		{
			usuario = tm.buscarUsuarioPorID(id);
			return Response.status( 200 ).entity( usuario ).build( );	
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@POST
	@Path("/{id: \\d+}/zonas")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addZona(@PathParam("id") Long id, Zona zona) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try 
		{
			tm.agregarZona(id, zona);
		} 
		catch (Exception e)
		{	
			return Response.status(500).entity(doErrorMessage(e)).build();
		}

		return Response.status(200).entity(zona).build();
	}
	
	@Path("/administradores/{id: \\d+}/restaurantes")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRestaurante(@PathParam("id") Long id, Restaurante rest){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try{
			tm.agregarRestaurante(id,rest);
		}catch(Exception e){
			Response.status(500).entity(doErrorMessage(e)).build();
		}
		
		return Response.status(200).entity(rest).build();
	}
	@POST
	@Path("/{id: \\d+}/usuarios")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCliente(@PathParam("id") Long id, Usuario usuario){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try{
			tm.agregarCliente(id, usuario);
		}catch(Exception e){
			Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(usuario).build();
	}
	@POST
	@Path("/{id: \\d+}/preferencias/categorias")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPreferenciaCategoria(@PathParam("id") Long id, PreferenciaUsuarioCategoria preferencia){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try{
			tm.agregarPreferenciaCategoria(id, preferencia);
		}catch(Exception e){
			Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(preferencia).build();
	}
	
	@POST
	@Path("/{id: \\d+}/preferencias/precios")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPreferenciaPrecio(@PathParam("id") Long id, PreferenciaUsuarioPrecio preferencia){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try{
			tm.agregarPreferenciaPrecio(id, preferencia);
		}catch(Exception e){
			Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(preferencia).build();
	}
	
	@POST
	@Path("/{id: \\d+}/preferencias/zonas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPreferenciaZona(@PathParam("id") Long id, PreferenciaUsuarioZona preferencia){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try{
			tm.agregarPreferenciaZona(id, preferencia);
		}catch(Exception e){
			Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(preferencia).build();
	}
	
	@PUT
	@Path("/{id: \\d+}/preferencias/categorias/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePreferenciaCategoria(@PathParam("id") Long id, PreferenciaUsuarioCategoria preferencia){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try{
			tm.updatePreferenciaCategoria(id, preferencia);
		}catch(Exception e){
			Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(preferencia).build();
	}
	
	@PUT
	@Path("/{id: \\d+}/preferencias/precio/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePreferenciaPrecio(@PathParam("id") Long id, PreferenciaUsuarioPrecio preferencia){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try{
			tm.updatePreferenciaPrecio(id, preferencia);
		}catch(Exception e){
			Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(preferencia).build();
	}
	
	@PUT
	@Path("/{id: \\d+}/preferencias/zonas/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePreferenciaZona(@PathParam("id") Long id, PreferenciaUsuarioZona preferencia){
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try{
			tm.updatePreferenciaZona(id, preferencia);
		}catch(Exception e){
			Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(preferencia).build();
	}
	
}
