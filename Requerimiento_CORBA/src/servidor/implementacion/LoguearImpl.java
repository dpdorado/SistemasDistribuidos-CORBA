/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.implementacion;

import java.util.Hashtable;
import servidor.dao.GestorObjetoDAO;
import servidor.persistencia.IConstantes;
import servidor.persistencia.InicioSesionDAO;
import sop_corba.CambioContraseniaDTO;
import sop_corba.IniciarSesionDTO;
import sop_corba.LoguearIntPOA;
import sop_corba.RespuestaGDTO;
import sop_corba.RespuestaISDTO;

/**
 *
 * @author DD
 */
public class LoguearImpl extends LoguearIntPOA{
    private GestorObjetoDAO gestor;
	
    public LoguearImpl(){
            super();
            gestor=new InicioSesionDAO();
    }
        
    @Override
    public RespuestaISDTO iniciarSesion(IniciarSesionDTO datosIS) {
        RespuestaISDTO respuesta=new RespuestaISDTO();            
        Hashtable<String,String> object= new Hashtable<String,String>(); 
        object.put(IConstantes.USUARIO, datosIS.getUsuario());
        object= (Hashtable<String,String>)this.gestor.leerObjeto((Object)object);
        if (object!=null) {
                //System.out.println(object.get(IConstantes.CONTRASENIA));
                if(object.get(IConstantes.CONTRASENIA).equals(datosIS.getContrasenia())) {
                    respuesta.setTipoUser(Integer.parseInt(object.get(IConstantes.TIPO_USUARIO)));
                    respuesta.setMensaje("Inicio de sesion exitoso.");
                }
        }
        return respuesta;
    }

    @Override
    public RespuestaGDTO cambiarContrasenia(CambioContraseniaDTO datosCC) {
        RespuestaGDTO respuesta=new RespuestaGDTO();       
        Hashtable<String,String> object= new Hashtable<String,String>(); 

        object.put(IConstantes.USUARIO, datosCC.getDatosUsuario().getUsuario());
        //object.put(IConstantes.CONTRASENIA, is.getContrase�a());

        //object.put(IConstantes.NUEVA_CONTRASENIA, is.getNuevaContrase�a());

        object= (Hashtable<String,String>)this.gestor.leerObjeto((Object)object);
        if (object!=null) {
            if(object.get(IConstantes.CONTRASENIA).equals(datosCC.getDatosUsuario().getContrasenia())) {
                object.put(IConstantes.CONTRASENIA,datosCC.getDatosUsuario().getContrasenia());
                respuesta.setOperacionExito(this.gestor.editarObjeto(object));
                if(respuesta.isOperacionExito()) {
                        respuesta.setMensaje("Se cambio la contrase�a.");
                }else {
                        respuesta.setMensaje("No se pudo cambiar la contrase�a.");
                }
            }
        }else {
                respuesta.setMensaje("Datos incorrectos, intentelo denuevo.");
        }
        return respuesta;
    }
    
}
