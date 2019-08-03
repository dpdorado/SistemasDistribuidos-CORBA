package sop_corba;


/**
* sop_corba/_OperacionesJDIntStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interfaces.idl
* viernes 2 de agosto de 2019 08:07:24 PM COT
*/

public class _OperacionesJDIntStub extends org.omg.CORBA.portable.ObjectImpl implements sop_corba.OperacionesJDInt
{

  public sop_corba.RespuestaGDTO registrarUsuario (sop_corba.UsuarioDTO datosUser)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("registrarUsuario", true);
                sop_corba.UsuarioDTOHelper.write ($out, datosUser);
                $in = _invoke ($out);
                sop_corba.RespuestaGDTO $result = sop_corba.RespuestaGDTOHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return registrarUsuario (datosUser        );
            } finally {
                _releaseReply ($in);
            }
  } // registrarUsuario

  public sop_corba.RespuestaGDTO registrarAnteproyecto (sop_corba.AnteproyectoDTO datosAnteproyecto)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("registrarAnteproyecto", true);
                sop_corba.AnteproyectoDTOHelper.write ($out, datosAnteproyecto);
                $in = _invoke ($out);
                sop_corba.RespuestaGDTO $result = sop_corba.RespuestaGDTOHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return registrarAnteproyecto (datosAnteproyecto        );
            } finally {
                _releaseReply ($in);
            }
  } // registrarAnteproyecto

  public sop_corba.RespuestaGDTO asignarEvaluadores (sop_corba.EvaluadoresDTO datosEvaluadores)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("asignarEvaluadores", true);
                sop_corba.EvaluadoresDTOHelper.write ($out, datosEvaluadores);
                $in = _invoke ($out);
                sop_corba.RespuestaGDTO $result = sop_corba.RespuestaGDTOHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return asignarEvaluadores (datosEvaluadores        );
            } finally {
                _releaseReply ($in);
            }
  } // asignarEvaluadores

  public sop_corba.AnteproyectoCompletoDTO buscarAnteproyceto (String codigoAnteproyecto)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("buscarAnteproyceto", true);
                $out.write_string (codigoAnteproyecto);
                $in = _invoke ($out);
                sop_corba.AnteproyectoCompletoDTO $result = sop_corba.AnteproyectoCompletoDTOHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return buscarAnteproyceto (codigoAnteproyecto        );
            } finally {
                _releaseReply ($in);
            }
  } // buscarAnteproyceto

  public sop_corba.RespuestaGDTO modificarConceptoAnteproyecto (sop_corba.MConceptoAnteproyectoDTO datosModificacion)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("modificarConceptoAnteproyecto", true);
                sop_corba.MConceptoAnteproyectoDTOHelper.write ($out, datosModificacion);
                $in = _invoke ($out);
                sop_corba.RespuestaGDTO $result = sop_corba.RespuestaGDTOHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return modificarConceptoAnteproyecto (datosModificacion        );
            } finally {
                _releaseReply ($in);
            }
  } // modificarConceptoAnteproyecto

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:sop_corba/OperacionesJDInt:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _OperacionesJDIntStub
