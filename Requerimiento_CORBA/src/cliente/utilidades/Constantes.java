/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.utilidades;

/**
 *
 * @author andres
 */
public class Constantes {

    public static String[] Concepto = {"Aprobado", "No Aprobado"};
    public static String[] Estado = {"sin asignación", "evaluadores asignados", "en revisión", "evaluado"};
    public static String[] Modalidad = {"Trabajo de Investigación(TI)","Práctica Profesional(PP)"};

    public static final String servicioIniSesion = "Loguear";
    public static final String servicioJefeDep = "OperacionesJD";
    public static final String servicioEstDir = "OperacionesED";
    public static final String servicioEvaluador = "OperacionesE";

    public static String usuarioLogueado = "";
    
    public static int ORBInitialPort = 1024;
    public static String ORBInitialHost = "localhost";
    public static String[] argsORB(){
        String[] args = {"–ORBInitialHost", ORBInitialHost, "-ORBInitialPort", Integer.toString(ORBInitialPort)};
        return args;
    }
}
