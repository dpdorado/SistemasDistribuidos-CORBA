package cliente;

import cliente.gui.DatosORB;
import java.rmi.RemoteException;

/**
 * Cliente
 */
public class Cliente {

    public static void main(String[] args) throws RemoteException {
        DatosORB datosORB = new DatosORB();
        datosORB.setLocationRelativeTo(null);
        datosORB.setVisible(true);
          
    }
}