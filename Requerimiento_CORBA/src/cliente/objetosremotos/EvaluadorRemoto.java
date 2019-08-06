/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.objetosremotos;

import cliente.gui.EvaluadorGUI;
import cliente.utilidades.Mensajes;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import sop_corba.AgregarConceptoAnteproyectoDTO;
import sop_corba.OperacionesEInt;
import sop_corba.OperacionesEIntHelper;
import sop_corba.OperacionesEIntOperations;
import sop_corba.RespuestaGDTO;

/**
 *
 * @author andres
 */
public class EvaluadorRemoto extends ServicioRemoto implements OperacionesEIntOperations {

    private OperacionesEInt operacionesEInt;

    public EvaluadorRemoto(EvaluadorGUI gui) {
        super(gui);
        iniciar();
    }

    private void iniciar() {
        try {
            String name = "OperacionesE";
            this.operacionesEInt = OperacionesEIntHelper.narrow(ncref.resolve_str(name));

        } catch (CannotProceed | InvalidName | NotFound ex) {
            Mensajes.error(gui, ex);
        }
    }

    @Override
    public RespuestaGDTO agregarConceptoAnteproyecto(AgregarConceptoAnteproyectoDTO datosAnteproyecto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
