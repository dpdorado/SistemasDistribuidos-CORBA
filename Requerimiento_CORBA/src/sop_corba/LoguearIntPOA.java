package sop_corba;


/**
* sop_corba/LoguearIntPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interfaces.idl
* viernes 2 de agosto de 2019 08:07:24 PM COT
*/

public abstract class LoguearIntPOA extends org.omg.PortableServer.Servant
 implements sop_corba.LoguearIntOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("iniciarSesion", new java.lang.Integer (0));
    _methods.put ("cambiarContrasenia", new java.lang.Integer (1));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // sop_corba/LoguearInt/iniciarSesion
       {
         sop_corba.IniciarSesionDTO datosIS = sop_corba.IniciarSesionDTOHelper.read (in);
         sop_corba.RespuestaISDTO $result = null;
         $result = this.iniciarSesion (datosIS);
         out = $rh.createReply();
         sop_corba.RespuestaISDTOHelper.write (out, $result);
         break;
       }

       case 1:  // sop_corba/LoguearInt/cambiarContrasenia
       {
         sop_corba.CambioContraseniaDTO datosCC = sop_corba.CambioContraseniaDTOHelper.read (in);
         sop_corba.RespuestaGDTO $result = null;
         $result = this.cambiarContrasenia (datosCC);
         out = $rh.createReply();
         sop_corba.RespuestaGDTOHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:sop_corba/LoguearInt:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public LoguearInt _this() 
  {
    return LoguearIntHelper.narrow(
    super._this_object());
  }

  public LoguearInt _this(org.omg.CORBA.ORB orb) 
  {
    return LoguearIntHelper.narrow(
    super._this_object(orb));
  }


} // class LoguearIntPOA