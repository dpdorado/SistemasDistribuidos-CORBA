package sop_corba;


/**
* sop_corba/AnteproyectoCompletoDTO.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interfaces.idl
* viernes 2 de agosto de 2019 08:07:24 PM COT
*/

public final class AnteproyectoCompletoDTO implements org.omg.CORBA.portable.IDLEntity
{
  public sop_corba.AnteproyectoDTO anteproyecto = null;
  public sop_corba.EvaluadoresDTO evaluadores = null;

  public AnteproyectoCompletoDTO ()
  {
  } // ctor

  public AnteproyectoCompletoDTO (sop_corba.AnteproyectoDTO _anteproyecto, sop_corba.EvaluadoresDTO _evaluadores)
  {
    anteproyecto = _anteproyecto;
    evaluadores = _evaluadores;
  } // ctor

    public AnteproyectoDTO getAnteproyecto() {
        return anteproyecto;
    }

    public void setAnteproyecto(AnteproyectoDTO anteproyecto) {
        this.anteproyecto = anteproyecto;
    }

    public EvaluadoresDTO getEvaluadores() {
        return evaluadores;
    }

    public void setEvaluadores(EvaluadoresDTO evaluadores) {
        this.evaluadores = evaluadores;
    }
  
  

} // class AnteproyectoCompletoDTO