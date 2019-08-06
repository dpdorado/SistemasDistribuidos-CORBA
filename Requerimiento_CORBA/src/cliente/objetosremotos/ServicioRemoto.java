/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.objetosremotos;

import cliente.utilidades.Constantes;
import cliente.utilidades.Mensajes;
import java.net.ConnectException;
import javax.swing.JFrame;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;

/**
 *
 * @author andres
 */
public class ServicioRemoto {
    
    protected JFrame gui;
    protected ORB orb;
    protected NamingContextExt ncref;
    
    public ServicioRemoto(JFrame gui) {
        this.gui = gui;
        init();
    }
    
    private void init() {
        try {
            // Crea e inicializa el orb
            orb = ORB.init(Constantes.argsORB(), null);
            // obtiene el contexto de nombrado raiz de Name Service
            org.omg.CORBA.Object ref = orb.resolve_initial_references("NameService");
            // Usa NamingContextExt
            ncref = NamingContextExtHelper.narrow(ref);
            
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();
            // instancia el servant
        } catch (InvalidName | AdapterInactive ex) {
            Mensajes.error(gui, ex);
        }
    }
    
}
