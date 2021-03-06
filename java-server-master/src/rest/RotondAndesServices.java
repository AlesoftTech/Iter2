/**-------------------------------------------------------------------
 * $Id$


 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 *
 * Materia: Sistemas Transaccionales
 * Ejercicio: VideoAndes
 * Autor: Juan Felipe García - jf.garcia268@uniandes.edu.co
 * -------------------------------------------------------------------
 */
package rest;


import java.util.List;



import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import tm.RotondAndesTM;
import vos.Ingrediente;
import vos.PreferenciaUsuarioCategoria;
import vos.PreferenciaUsuarioPrecio;
import vos.PreferenciaUsuarioZona;
import vos.Producto;
import vos.Usuario;
import vos.UsuarioClientePref;
import vos.Video;
import vos.Zona;
import vos.Restaurante;

/**
 * Clase que expone servicios REST con ruta base: http://"ip o nombre de host":8080/VideoAndes/rest/videos/...
 * @author Monitores 2017-20
 */
@Path("servicios")
public class RotondAndesServices {

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


//	/**
//	 * Metodo que expone servicio REST usando GET que da todos los videos de la base de datos.
//	 * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos
//	 * @return Json con todos los videos de la base de datos o json con 
//	 * el error que se produjo
//	 */
//	@GET
//	@Path("/ingredientes")
//	@Produces({ MediaType.APPLICATION_JSON })
//	public Response getIngredientes() {
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		List<Ingrediente> ingredientes;
//		try {
//			ingredientes = tm.darIngredientes();
//		} catch (Exception e) {
//			return Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(ingredientes).build();
//	}

//	@GET
//	@Path("/productos")
//	@Produces({ MediaType.APPLICATION_JSON })
//	public Response getProductos() {
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		List<Producto> productos;
//		try {
//			productos = tm.darProductos();
//		} catch (Exception e) {
//			return Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(productos).build();
//	}

//	@GET
//	@Path("/productos/restaurantes/{id: \\d+}")
//	@Produces({ MediaType.APPLICATION_JSON })
//	public Response getProductosRestaurante(@PathParam("id") Long id) {
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		List<Producto> productos;
//		try {
//			productos = tm.darProductosRestaurante(id);
//		} catch (Exception e) {
//			return Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(productos).build();
//	}

//	@GET
//	@Path("/productos/categorias/{id: \\d+}")
//	@Produces({ MediaType.APPLICATION_JSON })
//	public Response getProductosCategoria(@PathParam("id") Long id) {
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		List<Producto> productos;
//		try {
//			productos = tm.darProductosCategoria(id);
//		} catch (Exception e) {
//			return Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(productos).build();
//	}

//	@GET
//	@Path("/productos/precio/{precioMenor: \\d+}/{precioMayor: \\d+}")
//	@Produces({ MediaType.APPLICATION_JSON })
//	public Response getProductosPrecios(@PathParam("precioMenor") Long pMenor, @PathParam("precioMayor") Long pMayor) {
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		List<Producto> productos;
//		try {
//			productos = tm.darProductosPrecio(pMenor, pMayor);
//		} catch (Exception e) {
//			return Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(productos).build();
//	}


//
//	@POST
//	@Path("/usuarios/{id: \\d+}/ingredientes")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response addIngrediente(@PathParam("id") Long id, Ingrediente ingrediente) {
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		try 
//		{
//			tm.addIngrediente(id, ingrediente);
//		} 
//		catch (Exception e)
//		{	
//			return Response.status(500).entity(doErrorMessage(e)).build();
//		}
//
//		return Response.status(200).entity(ingrediente).build();
//	}

//	@POST
//	@Path("/usuarios/{id: \\d+}/productos")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response addProductos(@PathParam("id") Long id, Producto producto) {
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		try 
//		{
////			tm.addProducto(id, producto);
//		} 
//		catch (Exception e)
//		{	
//			return Response.status(500).entity(doErrorMessage(e)).build();
//		}
//
//		return Response.status(200).entity(producto).build();
//	}

	/*---------------------------------------------------------------------------------------------------------------------*/
//	@GET
//	@Path("zonas/{id: \\d+}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getZona( @PathParam( "id" ) Long id )
//	{
//		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
//		try
//		{
//			Zona z = tm.buscarZonaPorId(id);
//			return Response.status( 200 ).entity( z ).build( );			
//		}
//		catch( Exception e )
//		{
//			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
//		}
//	}
//
//	@GET
//	@Path("/usuarios")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getUsuarios(){
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		List<Usuario> usuarios;
//		try
//		{
//			usuarios = tm.darUsuarios();
//			return Response.status( 200 ).entity( usuarios ).build( );			
//		}
//		catch( Exception e )
//		{
//			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
//		}
//	}
//	@GET
//	@Path("/usuariosPref")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getUsuariosPreferencias(){
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		List<UsuarioClientePref> usuarios;
//		try
//		{
//			usuarios = tm.darUsuarioPreferencias();
//			return Response.status( 200 ).entity( usuarios ).build( );			
//		}
//		catch( Exception e )
//		{
//			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
//		}
//	}

//	@POST
//	@Path("/usuarios")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response addUsuario(Usuario usuario){
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		try{
//			tm.agregarUsuario(usuario);
//		}catch(Exception e){
//			Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(usuario).build();
//	}

//	/**
//	 * Metodo que expone servicio REST usando GET que da todos los videos de la base de datos.
//	 * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos
//	 * @return Json con todos los videos de la base de datos o json con 
//	 * el error que se produjo
//	 */
//	@GET
//	@Path("/restaurantes")
//	@Produces({ MediaType.APPLICATION_JSON })
//	public Response getRestaurantes() {
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		List<Restaurante> restaurantes;
//		try
//		{
//			restaurantes = tm.darRestaurantes();
//			return Response.status( 200 ).entity( restaurantes ).build( );			
//		}catch( Exception e )
//		{
//			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
//		}
//	}
//
//	@GET
//	@Path("/usuarios/administradores")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getAdministradores(){
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		List<Usuario> usuarios;
//		try
//		{
//			usuarios = tm.darAdministradores();
//			return Response.status( 200 ).entity( usuarios ).build( );			
//		}
//		catch( Exception e )
//		{
//			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
//		}
//	}

//	@GET
//	@Path("/usuarios/{id: \\d+}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getUsuarioPorID(@PathParam("id") Long id){
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		Usuario usuario;
//		try
//		{
//			usuario = tm.buscarUsuarioPorID(id);
//			return Response.status( 200 ).entity( usuario ).build( );	
//		}
//		catch( Exception e )
//		{
//			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
//		}
//	}


//	@POST
//	@Path("/usuarios/{id: \\d+}/zonas")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response addZona(@PathParam("id") Long id, Zona zona) {
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		try 
//		{
//			tm.agregarZona(id, zona);
//		} 
//		catch (Exception e)
//		{	
//			return Response.status(500).entity(doErrorMessage(e)).build();
//		}
//
//		return Response.status(200).entity(zona).build();
//	}
//	@GET
//	@Path("/zonas")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getZonas(){
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		List<Zona> zonas;
//		try
//		{
//			zonas = tm.darZonas();
//			return Response.status( 200 ).entity( zonas ).build( );			
//		}
//		catch( Exception e )
//		{
//			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
//		}
//	}
//	@Path("/usuarios/administradores/{id: \\d+}/restaurantes")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response addRestaurante(@PathParam("id") Long id, Restaurante rest){
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		try{
//			tm.agregarRestaurante(id,rest);
//		}catch(Exception e){
//			Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(rest).build();
//	}

//	@POST
//	@Path("/usuarios/{id: \\d+}/usuarios")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response addCliente(@PathParam("id") Long id, Usuario usuario){
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		try{
//			tm.agregarCliente(id, usuario);
//		}catch(Exception e){
//			Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(usuario).build();
//	}

//	@GET
//	@Path("/preferencias/categorias")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getPreferenciasCategorias(){
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		List<PreferenciaUsuarioCategoria> preferencias;
//		try
//		{
//			preferencias = tm.darPreferenciasCategoria();
//			return Response.status( 200 ).entity( preferencias ).build( );			
//		}
//		catch( Exception e )
//		{
//			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
//		}
//	}

//	@GET
//	@Path("/preferencias/precios")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getPreferenciasPrecios(){
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		List<PreferenciaUsuarioPrecio> preferencias;
//		try
//		{
//			preferencias = tm.darPreferenciasPrecio();
//			return Response.status( 200 ).entity( preferencias ).build( );			
//		}
//		catch( Exception e )
//		{
//			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
//		}
//	}

//	@GET
//	@Path("/preferencias/zonas")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getPreferenciasZonas(){
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		List<PreferenciaUsuarioZona> preferencias;
//		try
//		{
//			preferencias = tm.darPreferenciasZona();
//			return Response.status( 200 ).entity( preferencias ).build( );			
//		}
//		catch( Exception e )
//		{
//			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
//		}
//	}

//	@POST
//	@Path("/usuarios/{id: \\d+}/preferencias/categorias")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response addPreferenciaCategoria(@PathParam("id") Long id, PreferenciaUsuarioCategoria preferencia){
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		try{
//			tm.agregarPreferenciaCategoria(id, preferencia);
//		}catch(Exception e){
//			Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(preferencia).build();
//	}

//	@POST
//	@Path("/usuarios/{id: \\d+}/preferencias/precios")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response addPreferenciaPrecio(@PathParam("id") Long id, PreferenciaUsuarioPrecio preferencia){
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		try{
//			tm.agregarPreferenciaPrecio(id, preferencia);
//		}catch(Exception e){
//			Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(preferencia).build();
//	}

//	@POST
//	@Path("/usuarios/{id: \\d+}/preferencias/zonas")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response addPreferenciaZona(@PathParam("id") Long id, PreferenciaUsuarioZona preferencia){
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		try{
//			tm.agregarPreferenciaZona(id, preferencia);
//		}catch(Exception e){
//			Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(preferencia).build();
//	}

//	@PUT
//	@Path("/usuarios/{id: \\d+}/preferencias/categorias/")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response updatePreferenciaCategoria(@PathParam("id") Long id, PreferenciaUsuarioCategoria preferencia){
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		try{
//			tm.updatePreferenciaCategoria(id, preferencia);
//		}catch(Exception e){
//			Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(preferencia).build();
//	}

//	@PUT
//	@Path("/usuarios/{id: \\d+}/preferencias/precio/")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response updatePreferenciaPrecio(@PathParam("id") Long id, PreferenciaUsuarioPrecio preferencia){
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		try{
//			tm.updatePreferenciaPrecio(id, preferencia);
//		}catch(Exception e){
//			Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(preferencia).build();
//	}

//	@PUT
//	@Path("/usuarios/{id: \\d+}/preferencias/zonas/")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response updatePreferenciaZona(@PathParam("id") Long id, PreferenciaUsuarioZona preferencia){
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		try{
//			tm.updatePreferenciaZona(id, preferencia);
//		}catch(Exception e){
//			Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(preferencia).build();
//	}

//	@GET
//	@Path("/productos/mas-ofrecidos")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response getProductosMasOfrecidos(){
//		RotondAndesTM tm = new RotondAndesTM(getPath());
//		List<Producto> ofrecidos;
//		try
//		{
//			ofrecidos = tm.darPoductosMasOfrecidos();
//			return Response.status( 200 ).entity( ofrecidos ).build( );			
//		}
//		catch( Exception e )
//		{
//			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
//		}
//	}
}
