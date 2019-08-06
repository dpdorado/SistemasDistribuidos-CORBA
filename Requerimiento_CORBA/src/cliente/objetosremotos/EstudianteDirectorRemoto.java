/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.objetosremotos;

import cliente.gui.EstudianteDirectorGUI;
import cliente.utilidades.Constantes;
import cliente.utilidades.Mensajes;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import sop_corba.AnteproyectoDTO;
import sop_corba.AnteproyectoSimpleDTO;
import sop_corba.OperacionesEDInt;
import sop_corba.OperacionesEDIntHelper;

/**
 *
 * @author andres
 */
public class EstudianteDirectorRemoto extends ServicioRemoto {

    private OperacionesEDInt operacionesEDInt;

    public EstudianteDirectorRemoto(EstudianteDirectorGUI gui) {
        super(gui);
        iniciar();
    }

    private void iniciar() {
        try {
            String name = Constantes.servicioEstDir;
            this.operacionesEDInt = OperacionesEDIntHelper.narrow(ncref.resolve_str(name));

        } catch (CannotProceed | InvalidName | NotFound ex) {
            Mensajes.error(gui, ex);
            System.exit(0);
        }
    }

    public void buscarAnteproyecto(String codigoAnteproyecto) {
        AnteproyectoDTO adto = this.operacionesEDInt.buscarAnteproyecto(codigoAnteproyecto);
        ((EstudianteDirectorGUI) gui).cargarAnteproyecto(adto);
    }

    public Object[][] listarAnteproyectos() {
        AnteproyectoSimpleDTO[] anteproyectos = this.operacionesEDInt.listarAnteproyectos();
        Object[][] lista = new Object[anteproyectos.length][2];
        for (int i = 0; i < anteproyectos.length; i++) {
            lista[i][0] = anteproyectos[i].getCodigo();
            lista[i][1] = anteproyectos[i].getTitulo();
        }
        return lista;
    }
}
