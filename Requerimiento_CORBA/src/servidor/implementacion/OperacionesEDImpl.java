/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.implementacion;

import java.util.ArrayList;
import java.util.Hashtable;
import servidor.dao.GestorObjetoDAO;
import servidor.persistencia.IConstantes;
import servidor.utilidades.persistencia.GestorAnteproyectosDAO;
import sop_corba.AnteproyectoDTO;
import sop_corba.AnteproyectoSimpleDTO;
import sop_corba.OperacionesEDIntPOA;

/**
 *
 * @author DD
 */
public class OperacionesEDImpl  extends OperacionesEDIntPOA{
   
    private GestorObjetoDAO gestorAnteproyectos;
	
    public OperacionesEDImpl(){
        super();
        this.gestorAnteproyectos=new GestorAnteproyectosDAO();
    }
        
    @Override
    public AnteproyectoDTO buscarAnteproyecto(String codigoAnteproyecto) {
        AnteproyectoDTO anteproyecto=null;		        
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

        }
        return anteproyecto;
    }

    @Override
    public AnteproyectoSimpleDTO[] listarAnteproyectos() {
        AnteproyectoSimpleDTO[] datos=null;
        ArrayList<Hashtable<String, String>> datas=null;
        AnteproyectoSimpleDTO nuevo=null;
        datas=(ArrayList<Hashtable<String, String>>)this.gestorAnteproyectos.listarObjetos();
        if (datas!=null) {	
            datos=new AnteproyectoSimpleDTO[datas.size()];
            int index=0;            
            for(Hashtable<String, String> ant:datas) {
                nuevo=new AnteproyectoSimpleDTO();
                nuevo.setCodigo(ant.get(IConstantes.CODIGO_ANTEPROYECTO));
                nuevo.setTitulo(ant.get(IConstantes.TITULO));
                datos[index]=nuevo;
                index++;
            }
        }
        return datos;
    }
    
}
