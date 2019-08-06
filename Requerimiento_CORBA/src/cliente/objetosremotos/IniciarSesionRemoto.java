/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.objetosremotos;

import cliente.gui.IniciarSesion;
import cliente.implementacion.UsuarioCllbckImpl;
import cliente.utilidades.Constantes;
import cliente.utilidades.Mensajes;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import sop_corba.CambioContraseniaDTO;
import sop_corba.IniciarSesionDTO;
import sop_corba.LoguearInt;
import sop_corba.LoguearIntHelper;
import sop_corba.LoguearIntOperations;
import sop_corba.RespuestaGDTO;
import sop_corba.RespuestaISDTO;
import sop_corba.UsuarioCllbckInt;
import sop_corba.UsuarioCllbckIntHelper;

/**
 *
 * @author andres
 */
public class IniciarSesionRemoto extends ServicioRemoto{

    public LoguearInt refLoguearInt;
    public UsuarioCllbckInt hrefUsuarioCllbck;

    public IniciarSesionRemoto(IniciarSesion gui) {
        super(gui);
        iniciar();
    }

    
    private void iniciar() {
        try {
            String name = Constantes.servicioIniSesion;
            this.refLoguearInt = LoguearIntHelper.narrow(ncref.resolve_str(name));

            /* String name = "PingPong";
            PingObject addref = PingObjectHelper.narrow(ncref.resolve_str(name));*/
            POA rootPOA1 = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA1.the_POAManager().activate();
            // instancia el servant
            UsuarioCllbckImpl cliente = new UsuarioCllbckImpl();
            // obtiene la referencia del rootpoa & activate el POAManager
            org.omg.CORBA.Object ref2 = rootPOA1.servant_to_reference(cliente);
            hrefUsuarioCllbck = UsuarioCllbckIntHelper.narrow(ref2);
        } catch (InvalidName | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName | NotFound | AdapterInactive | ServantNotActive | WrongPolicy ex) {
            Mensajes.error(gui, ex);
            System.exit(0);
        } 
    }
    
    public RespuestaGDTO cambiarContrasenia(CambioContraseniaDTO datosCC) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public RespuestaISDTO iniciarSesion(String usuario, String contrasenia) {
        return this.refLoguearInt.iniciarSesion(new IniciarSesionDTO(usuario, contrasenia, hrefUsuarioCllbck));
    }
    
    
    

}
