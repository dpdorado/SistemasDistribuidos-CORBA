package sop_corba;


/**
* sop_corba/UsuarioCllbckIntPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interfaces.idl
* viernes 2 de agosto de 2019 08:07:24 PM COT
*/

public abstract class UsuarioCllbckIntPOA extends org.omg.PortableServer.Servant
 implements sop_corba.UsuarioCllbckIntOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("notificar", new java.lang.Integer (0));
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
       case 0:  // sop_corba/UsuarioCllbckInt/notificar
       {
         String codigoAnteproyecto = in.read_string ();
         String $result = null;
         $result = this.notificar (codigoAnteproyecto);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:sop_corba/UsuarioCllbckInt:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public UsuarioCllbckInt _this() 
  {
    return UsuarioCllbckIntHelper.narrow(
    super._this_object());
  }

  public UsuarioCllbckInt _this(org.omg.CORBA.ORB orb) 
  {
    return UsuarioCllbckIntHelper.narrow(
    super._this_object(orb));
  }


} // class UsuarioCllbckIntPOA
