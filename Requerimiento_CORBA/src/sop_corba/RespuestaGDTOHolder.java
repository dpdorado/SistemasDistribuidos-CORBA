package sop_corba;

/**
* sop_corba/RespuestaGDTOHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interfaces.idl
* viernes 2 de agosto de 2019 08:07:24 PM COT
*/

public final class RespuestaGDTOHolder implements org.omg.CORBA.portable.Streamable
{
  public sop_corba.RespuestaGDTO value = null;

  public RespuestaGDTOHolder ()
  {
  }

  public RespuestaGDTOHolder (sop_corba.RespuestaGDTO initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = sop_corba.RespuestaGDTOHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    sop_corba.RespuestaGDTOHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return sop_corba.RespuestaGDTOHelper.type ();
  }

}
