/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.objetosremotos;

import cliente.gui.JefeDepartamentoGUI;
import cliente.utilidades.Constantes;
import cliente.utilidades.Mensajes;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import sop_corba.AnteproyectoCompletoDTO;
import sop_corba.AnteproyectoDTO;
import sop_corba.AnteproyectoSimpleDTO;
import sop_corba.EvaluadoresDTO;
import sop_corba.MConceptoAnteproyectoDTO;
import sop_corba.OperacionesEDInt;
import sop_corba.OperacionesEDIntHelper;
import sop_corba.OperacionesJDInt;
import sop_corba.OperacionesJDIntHelper;
import sop_corba.RespuestaGDTO;
import sop_corba.UsuarioDTO;

/**
 *
 * @author andres
 */
public class JefeDepartamentoRemoto extends ServicioRemoto {

    private OperacionesJDInt operacionesJDInt;
    private OperacionesEDInt operacionesEDInt;

    public JefeDepartamentoRemoto(JefeDepartamentoGUI gui) {
        super(gui);
        iniciar();
    }

    private void iniciar() {
        try {
            // SERVANT JEFE DEPARTAMENTO
            String name = Constantes.servicioJefeDep;
            this.operacionesJDInt = OperacionesJDIntHelper.narrow(ncref.resolve_str(name));

            // SERVANT ESTUDIANTE - DIRECTOR
            String name2 = Constantes.servicioEstDir;
            this.operacionesEDInt = OperacionesEDIntHelper.narrow(ncref.resolve_str(name2));

        } catch (CannotProceed | InvalidName | NotFound ex) {
            Mensajes.error(gui, ex);
            System.exit(0);
        }
    }

    public void registrarUsuario(String nombreCompleto, String identificacion, String usuario, String contrasenia, int tipoUsuario) {
        UsuarioDTO newUser = new UsuarioDTO(nombreCompleto, identificacion, usuario, contrasenia, tipoUsuario);
        RespuestaGDTO rgdto = this.operacionesJDInt.registrarUsuario(newUser);
        if (rgdto.operacionExito) {
            ((JefeDepartamentoGUI) gui).limpiarRegistroUsuario();
            Mensajes.info(gui, rgdto.mensaje);
        } else {
            Mensajes.error(gui, rgdto.mensaje);
        }
    }

    public void registrarAnteproyecto(String modalidad, String titulo, String codigo, String e1, String e2, String director, String coDirector, String fechaR, String fechaAp, int concepto, int estado, int numeroRev) {
        AnteproyectoDTO datosAnteproyecto = new AnteproyectoDTO(modalidad, titulo, codigo, e1, e2, director, coDirector, fechaR, fechaAp, concepto, estado, numeroRev);
        RespuestaGDTO rgdto = this.operacionesJDInt.registrarAnteproyecto(datosAnteproyecto);
        if (rgdto.operacionExito) {
            ((JefeDepartamentoGUI) gui).limpiarRegistrarAnteproyecto();
            Mensajes.info(gui, rgdto.mensaje);
        } else {
            Mensajes.error(gui, rgdto.mensaje);
        }
    }

    public void asignarEvaluadores(String codigo, String nomEva1, String fechaEva1, String conceptoEva1, String nomEva2, String fechaEva2, String conceptoEva2) {
        EvaluadoresDTO datosEvaluadores = new EvaluadoresDTO(codigo, nomEva1, conceptoEva1, fechaEva1, nomEva2, conceptoEva2, fechaEva2);
        RespuestaGDTO rgdto = this.operacionesJDInt.asignarEvaluadores(datosEvaluadores);
        if (rgdto.operacionExito) {
            ((JefeDepartamentoGUI) gui).limpiarAsignarAnteproyecto();
            Mensajes.info(gui, rgdto.mensaje);
        } else {
            Mensajes.error(gui, rgdto.mensaje);
        }
    }

    public void buscarAnteproyecto(String codigo) {
        AnteproyectoCompletoDTO completoDTO = this.operacionesJDInt.buscarAnteproyceto(codigo);
        if (completoDTO.getAnteproyecto().getCodigo() != null && completoDTO.getAnteproyecto().getCodigo().equals("")) {
            Mensajes.info(gui, "Anteproyecto encontrado!");
            ((JefeDepartamentoGUI) gui).cargarBuscarAnteproyecto(completoDTO.getAnteproyecto());
        } else {
            Mensajes.error(gui, "No se encontro ningun anteproyecto con código: " + codigo);
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void modificarConceptoAnteproyecto(String codigo, int nuevoConcepto) {
        MConceptoAnteproyectoDTO datosModificacion = new MConceptoAnteproyectoDTO(codigo, nuevoConcepto);
        RespuestaGDTO rgdto = this.operacionesJDInt.modificarConceptoAnteproyecto(datosModificacion);
        if (rgdto.operacionExito) {
            ((JefeDepartamentoGUI) gui).limpiarModificarConcepto();
            Mensajes.info(gui, rgdto.mensaje);
        } else {
            Mensajes.error(gui, rgdto.mensaje);
        }
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
