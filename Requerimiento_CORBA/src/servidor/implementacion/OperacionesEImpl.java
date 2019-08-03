/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.implementacion;

import java.util.Hashtable;
import servidor.dao.GestorObjetoDAO;
import servidor.persistencia.IConstantes;
import servidor.persistencia.GestorEvaluadoresDAO;
import sop_corba.AgregarConceptoAnteproyectoDTO;
import sop_corba.OperacionesEIntPOA;
import sop_corba.RespuestaGDTO;

/**
 *
 * @author DD
 */
public class OperacionesEImpl extends OperacionesEIntPOA {
    
    private GestorObjetoDAO gestorEvaluadores;
	
    public OperacionesEImpl(){
            super();
            this.gestorEvaluadores=new GestorEvaluadoresDAO();
    }
    
    @Override
    public RespuestaGDTO agregarConceptoAnteproyecto(AgregarConceptoAnteproyectoDTO datosAnteproyecto) {
        RespuestaGDTO respuesta=new RespuestaGDTO();
        if (datosAnteproyecto==null) {
                return respuesta;
        }			        
        Hashtable<String,String> object=new Hashtable<String,String>();
        object.put(IConstantes.CODIGO_ANTEPROYECTO, datosAnteproyecto.getCodigoAnteproyecto());
        object.put(IConstantes.CONCEPTO,datosAnteproyecto.getConcepto());
        object.put(IConstantes.NOMBRES_APELLIDOS,datosAnteproyecto.getNombreEvaluador());
        respuesta.setOperacionExito(this.gestorEvaluadores.editarObjeto(object));
        if(respuesta.isOperacionExito()) {
                respuesta.setMensaje("Se modific� el condepto del anteproyecto.");
        }else {
                respuesta.setMensaje("No se pudo modificar el concepto del anteproyecto, consulte con el administrador.");
        }
        return respuesta;
    }
    
}
