/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.objetosremotos;

import cliente.gui.JefeDepartamentoGUI;
import cliente.utilidades.Mensajes;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import sop_corba.AnteproyectoCompletoDTO;
import sop_corba.AnteproyectoDTO;
import sop_corba.EvaluadoresDTO;
import sop_corba.MConceptoAnteproyectoDTO;
import sop_corba.OperacionesJDInt;
import sop_corba.OperacionesJDIntHelper;
import sop_corba.OperacionesJDIntOperations;
import sop_corba.RespuestaGDTO;
import sop_corba.UsuarioDTO;

/**
 *
 * @author andres
 */
public class JefeDepartamentoRemoto extends ServicioRemoto implements OperacionesJDIntOperations{

    private OperacionesJDInt operacionesJDInt;


    public JefeDepartamentoRemoto(JefeDepartamentoGUI gui) {
        super(gui);
        iniciar();
    }

    private void iniciar() {
        try {
            String name = "OperacionesJD";
            this.operacionesJDInt = OperacionesJDIntHelper.narrow(ncref.resolve_str(name));

        } catch (CannotProceed | InvalidName | NotFound ex) {
            Mensajes.error(gui, ex);
        } 
    }

    @Override
    public RespuestaGDTO registrarUsuario(UsuarioDTO datosUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RespuestaGDTO registrarAnteproyecto(AnteproyectoDTO datosAnteproyecto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RespuestaGDTO asignarEvaluadores(EvaluadoresDTO datosEvaluadores) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AnteproyectoCompletoDTO buscarAnteproyceto(String codigoAnteproyecto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RespuestaGDTO modificarConceptoAnteproyecto(MConceptoAnteproyectoDTO datosModificacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
