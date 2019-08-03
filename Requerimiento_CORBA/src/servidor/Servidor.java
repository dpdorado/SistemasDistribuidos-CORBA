/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import servidor.implementacion.LoguearImpl;
import servidor.implementacion.OperacionesEDImpl;
import servidor.implementacion.OperacionesEImpl;
import servidor.implementacion.OperacionesJDImpl;
import sop_corba.LoguearInt;
import sop_corba.LoguearIntHelper;
import sop_corba.OperacionesEDInt;
import sop_corba.OperacionesEDIntHelper;
import sop_corba.OperacionesEInt;
import sop_corba.OperacionesEIntHelper;
import sop_corba.OperacionesJDInt;
import sop_corba.OperacionesJDIntHelper;


/**
 *
 * @author andres
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);

            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            /*-----------------------------------*/
            LoguearImpl  GUImpl = new LoguearImpl();
            //pingImpl.setORB(orb);

            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(GUImpl);
            LoguearInt cref = LoguearIntHelper.narrow(ref);

            org.omg.CORBA.Object objRef =
                           orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            String name = "Loguear";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, cref);
            
            /*---------------------------------------------*/
            OperacionesJDImpl  operaciones = new OperacionesJDImpl();
            //pingImpl.setORB(orb);

            org.omg.CORBA.Object ref1 = rootpoa.servant_to_reference(operaciones);
            OperacionesJDInt cref1 = OperacionesJDIntHelper.narrow(ref1);

            org.omg.CORBA.Object objRef1 =
                           orb.resolve_initial_references("NameService");
            NamingContextExt ncRef1 = NamingContextExtHelper.narrow(objRef1);

            String name1 = "OperacionesJD";
            NameComponent path1[] = ncRef.to_name(name1);
            ncRef.rebind(path1, cref1);
            /*--------------------------------------------*/
            OperacionesEDImpl  operaciones2 = new OperacionesEDImpl();
            //pingImpl.setORB(orb);

            org.omg.CORBA.Object ref2 = rootpoa.servant_to_reference(operaciones2);
            OperacionesEDInt cref2 = OperacionesEDIntHelper.narrow(ref2);

            org.omg.CORBA.Object objRef2 =
                           orb.resolve_initial_references("NameService");
            NamingContextExt ncRef2 = NamingContextExtHelper.narrow(objRef2);

            String name2 = "OperacionesED";
            NameComponent path2[] = ncRef.to_name(name2);
            ncRef.rebind(path2, cref2);
            /*-------------------------------------------*/
            OperacionesEImpl  operaciones3 = new OperacionesEImpl();
            //pingImpl.setORB(orb);

            org.omg.CORBA.Object ref3 = rootpoa.servant_to_reference(operaciones3);
            OperacionesEInt cref3 = OperacionesEIntHelper.narrow(ref3);

            org.omg.CORBA.Object objRef3 =
                           orb.resolve_initial_references("NameService");
            NamingContextExt ncRef3 = NamingContextExtHelper.narrow(objRef3);

            String name3 = "OperacionesE";
            NameComponent path3[] = ncRef.to_name(name3);
            ncRef.rebind(path3, cref3);

            orb.run();
        }

        catch(Exception e) {
            System.err.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }
  
    
}
