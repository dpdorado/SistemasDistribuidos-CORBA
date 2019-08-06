/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.objetosremotos;

import cliente.gui.EstudianteDirectorGUI;
import cliente.utilidades.Mensajes;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import sop_corba.AnteproyectoDTO;
import sop_corba.AnteproyectoSimpleDTO;
import sop_corba.OperacionesEDInt;
import sop_corba.OperacionesEDIntHelper;
import sop_corba.OperacionesEDIntOperations;

/**
 *
 * @author andres
 */
public class EstudianteDirectorRemoto extends ServicioRemoto implements OperacionesEDIntOperations { 
    private OperacionesEDInt operacionesEDInt;
    

    public EstudianteDirectorRemoto(EstudianteDirectorGUI gui) {
        super(gui);
        iniciar();
    }

    private void iniciar() {
        try {
            String name = "OperacionesE";
            this.operacionesEDInt = OperacionesEDIntHelper.narrow(ncref.resolve_str(name));

        } catch (CannotProceed | InvalidName | NotFound ex) {
            Mensajes.error(gui, ex);
        } 
    }

    @Override
    public AnteproyectoDTO buscarAnteproyecto(String codigoAnteproyecto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AnteproyectoSimpleDTO[] listarAnteproyectos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
