/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.implementacion;

import java.util.Hashtable;
import servidor.dao.GestorObjetoDAO;
import servidor.persistencia.IConstantes;
import servidor.persistencia.GestorAnteproyectosDAO;
import servidor.persistencia.GestorEvaluadoresDAO;
import servidor.persistencia.GestorUsuariosDAO;
import sop_corba.AnteproyectoCompletoDTO;
import sop_corba.AnteproyectoDTO;
import sop_corba.EvaluadoresDTO;
import sop_corba.MConceptoAnteproyectoDTO;
import sop_corba.OperacionesJDIntPOA;
import sop_corba.RespuestaGDTO;
import sop_corba.UsuarioDTO;

/**
 *
 * @author DD
 */
public class OperacionesJDImpl extends OperacionesJDIntPOA{
    private GestorObjetoDAO gestorAnteproyectos;
    private GestorObjetoDAO gestorUsuarios;
    private GestorObjetoDAO gestorEvaluadores;
    
    public OperacionesJDImpl(){
        super();
        this.gestorAnteproyectos=new GestorAnteproyectosDAO();
        this.gestorUsuarios=new GestorUsuariosDAO();
        this.gestorEvaluadores=new GestorEvaluadoresDAO();        
    }
    
    @Override
    public RespuestaGDTO registrarUsuario(UsuarioDTO datosUser) {
        RespuestaGDTO respuesta=new RespuestaGDTO();        
        Hashtable<String,String> object=new Hashtable<String,String>(); 
        object.put(IConstantes.NOMBRES_APELLIDOS,datosUser.getNombresApellidos());
        object.put(IConstantes.IDENTIFICACION, datosUser.getIdentificacion());
        object.put(IConstantes.USUARIO, datosUser.getUsuario());
        object.put(IConstantes.CONTRASENIA, datosUser.getContrasenia());        
        object.put(IConstantes.TIPO_USUARIO, Integer.toString(datosUser.getTipoUser()));        
        respuesta.setOperacionExito(this.gestorUsuarios.registrarObjeto(object));
        if(respuesta.isOperacionExito()) {
                respuesta.setMensaje("Registro exitoso.");
        }else {
                respuesta.setMensaje("No se realizo el registro, puede ser que el id o el usuario ya existan.");
        }
        return respuesta;
    }

    @Override
    public RespuestaGDTO registrarAnteproyecto(AnteproyectoDTO datosAnteproyecto) {
        RespuestaGDTO respuesta=new RespuestaGDTO();        
        Hashtable<String,String> object=new Hashtable<String,String>(); 
        object.put(IConstantes.MODALIDAD,datosAnteproyecto.getModalidad());
        object.put(IConstantes.TITULO,datosAnteproyecto.getTitulo());
        object.put(IConstantes.CODIGO_ANTEPROYECTO,datosAnteproyecto.getCodigo());
        object.put(IConstantes.NOMBRE_EST_1,datosAnteproyecto.getNombreEstudiante1());
        object.put(IConstantes.NOMBRE_EST_2,datosAnteproyecto.getNombreEstudiante2());
        object.put(IConstantes.NOMBRE_DIRECTOR,datosAnteproyecto.getNombreDirector());
        object.put(IConstantes.NOMBRE_CO_DIRECTOR,datosAnteproyecto.getNombreCoDirector());
        object.put(IConstantes.FECHA_REGISTRO,datosAnteproyecto.getFechaRegistro());
        object.put(IConstantes.FECHA_APROBACION,datosAnteproyecto.getFechaAprobacion());
        object.put(IConstantes.CONCEPTO,Integer.toString(datosAnteproyecto.getConcepto()));
        object.put(IConstantes.ESTADO,Integer.toString(datosAnteproyecto.getEstado()));
        object.put(IConstantes.NUMERO_REVISION,Integer.toString(datosAnteproyecto.getNumeroRevision()));		
        respuesta.setOperacionExito(this.gestorAnteproyectos.registrarObjeto(object));
        if(respuesta.isOperacionExito()) {
                respuesta.setMensaje("Registro exitoso.");
        }else {
                respuesta.setMensaje("No se realizo el registro, puede ser que el codigo del anteproyecto ya este registrado.");
        }
        return respuesta;
    }

    //NOTIFICAR CALLBACK
    @Override
    public RespuestaGDTO asignarEvaluadores(EvaluadoresDTO datosEvaluadores) {
        RespuestaGDTO respuesta=new RespuestaGDTO();        
        Hashtable<String,String> object=new Hashtable<String,String>(); 		
        object.put(IConstantes.CODIGO_ANTEPROYECTO,datosEvaluadores.getCodigoAnteproyecto());
        object.put(IConstantes.NOMBRE_EVAL_1,datosEvaluadores.getNombreEvaluador1());
        object.put(IConstantes.CONCEPTO_EVAL_1,datosEvaluadores.getConceptoEvaluador1());
        object.put(IConstantes.FECHA_REVISION_1,datosEvaluadores.getFechaRevision1());
        object.put(IConstantes.NOMBRE_EVAL_2,datosEvaluadores.getNombreEvaluador2());
        object.put(IConstantes.CONCEPTO_EVAL_2,datosEvaluadores.getConceptoEvaluador2());
        object.put(IConstantes.FECHA_REVISION_2,datosEvaluadores.getFechaRevision2());
        respuesta.setOperacionExito(this.gestorEvaluadores.registrarObjeto(object));
        if(respuesta.isOperacionExito()) {
                String message=datosEvaluadores.getCodigoAnteproyecto()+" y evaluadore:"+datosEvaluadores.getNombreEvaluador1()+","+datosEvaluadores.getConceptoEvaluador2();
                respuesta.setMensaje("Se asignaron los evaluadores.");
                //doCallbacks(message);
        }else {
                respuesta.setMensaje("No se pudo realizar la asignaci�n, verifique que el anteproyecto no tenga evaluadores asignados.");
        }
        return respuesta;
    }

    @Override
    public AnteproyectoCompletoDTO buscarAnteproyceto(String codigoAnteproyecto) {
        AnteproyectoCompletoDTO anteproyectosC=new AnteproyectoCompletoDTO();
        AnteproyectoDTO anteproyecto=null;
        EvaluadoresDTO evaluadores=null;        
        Hashtable<String,String> objectA=(Hashtable<String,String>)this.gestorAnteproyectos.leerObjeto(codigoAnteproyecto);
        if(objectA!=null) {
            anteproyecto=new AnteproyectoDTO();
            anteproyecto.setModalidad(objectA.get(IConstantes.MODALIDAD));
            anteproyecto.setTitulo(objectA.get(IConstantes.TITULO));
            anteproyecto.setCodigo(objectA.get(IConstantes.CODIGO_ANTEPROYECTO));
            anteproyecto.setNombreEstudiante1(objectA.get(IConstantes.NOMBRE_EST_1));
            anteproyecto.setNombreEstudiante2(objectA.get(IConstantes.NOMBRE_EST_2));
            anteproyecto.setNombreDirector(objectA.get(IConstantes.NOMBRE_DIRECTOR));
            anteproyecto.setNombreCoDirector(objectA.get(IConstantes.NOMBRE_CO_DIRECTOR));
            anteproyecto.setFechaRegistro(objectA.get(IConstantes.FECHA_REGISTRO));
            anteproyecto.setFechaAprobacion(objectA.get(IConstantes.FECHA_APROBACION));
            anteproyecto.setConcepto(Integer.parseInt(objectA.get(IConstantes.CONCEPTO)));
            anteproyecto.setEstado(Integer.parseInt(objectA.get(IConstantes.ESTADO)));
            anteproyecto.setNumeroRevision(Integer.parseInt(objectA.get(IConstantes.NUMERO_REVISION)));
            @SuppressWarnings("unchecked")
            Hashtable<String,String> objectE=(Hashtable<String,String>)this.gestorEvaluadores.leerObjeto(codigoAnteproyecto);
            if(objectE!=null) {
                    evaluadores=new EvaluadoresDTO();
                    evaluadores.setCodigoAnteproyecto(objectE.get(IConstantes.CODIGO_ANTEPROYECTO));
                    evaluadores.setNombreEvaluador1(objectE.get(IConstantes.NOMBRE_EVAL_1));
                    evaluadores.setConceptoEvaluador1(objectE.get(IConstantes.CONCEPTO_EVAL_1));
                    evaluadores.setFechaRevision1(objectE.get(IConstantes.FECHA_REVISION_1));
                    evaluadores.setNombreEvaluador2(objectE.get(IConstantes.NOMBRE_EVAL_2));
                    evaluadores.setConceptoEvaluador2(objectE.get(IConstantes.CONCEPTO_EVAL_2));
                    evaluadores.setFechaRevision2(objectE.get(IConstantes.FECHA_REVISION_2));						
            }
        }
        anteproyectosC.setAnteproyecto(anteproyecto);
        anteproyectosC.setEvaluadores(evaluadores);
        return anteproyectosC;
    }

    @Override
    public RespuestaGDTO modificarConceptoAnteproyecto(MConceptoAnteproyectoDTO datosModificacion) {
        RespuestaGDTO respuesta=new RespuestaGDTO();		        
        Hashtable<String,String> object=new Hashtable<String,String>(); 
        object.put(IConstantes.CODIGO_ANTEPROYECTO, datosModificacion.getCodigoAnteproyecto());
        object.put(IConstantes.CONCEPTO, Integer.toString(datosModificacion.getNuevoConcepto()));
        respuesta.setOperacionExito(this.gestorAnteproyectos.editarObjeto(object));
        if(respuesta.isOperacionExito()) {
                respuesta.setMensaje("Se realizó el cambio de concepto del anteproyecto.");
        }else {
                respuesta.setMensaje("No se pudo realizar la modificación, contacte con eladministrador.");
        }
        return respuesta;
    }
    
}
