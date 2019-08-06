/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.objetosremotos;

import cliente.gui.EvaluadorGUI;
import cliente.utilidades.Constantes;
import cliente.utilidades.Mensajes;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import sop_corba.AgregarConceptoAnteproyectoDTO;
import sop_corba.AnteproyectoDTO;
import sop_corba.AnteproyectoSimpleDTO;
import sop_corba.OperacionesEDInt;
import sop_corba.OperacionesEDIntOperations;
import sop_corba.OperacionesEInt;
import sop_corba.OperacionesEIntHelper;
import sop_corba.OperacionesEIntOperations;
import sop_corba.RespuestaGDTO;

/**
 *
 * @author andres
 */
public class EvaluadorRemoto extends ServicioRemoto {

    private OperacionesEInt operacionesEInt;
    private OperacionesEDInt operacionesEDInt;

    public EvaluadorRemoto(EvaluadorGUI gui) {
        super(gui);
        iniciar();
    }

    private void iniciar() {
        try {
            String name = Constantes.servicioEvaluador;
            this.operacionesEInt = OperacionesEIntHelper.narrow(ncref.resolve_str(name));
            String name2 = Constantes.servicioEstDir;
            this.operacionesEInt = OperacionesEIntHelper.narrow(ncref.resolve_str(name2));

        } catch (CannotProceed | InvalidName | NotFound ex) {
            Mensajes.error(gui, ex);
            System.exit(0);

        }
    }

    public void agregarConceptoAnteproyecto(String codigo, String concepto) {
        AgregarConceptoAnteproyectoDTO acadto = new AgregarConceptoAnteproyectoDTO(concepto, codigo, Constantes.usuarioLogueado);
        RespuestaGDTO rgdto = this.operacionesEInt.agregarConceptoAnteproyecto(acadto);
        if (rgdto.isOperacionExito()) {
            Mensajes.info(gui, rgdto.getMensaje());
        } else {
            Mensajes.error(gui, rgdto.getMensaje());
        }
        ((EvaluadorGUI) gui).limpiarModificarConcepto();

    }

    public void buscarAnteproyecto(String codigoAnteproyecto) {
        AnteproyectoDTO adto = this.operacionesEDInt.buscarAnteproyecto(codigoAnteproyecto);
        ((EvaluadorGUI) gui).cargarAnteproyecto(adto);
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
