package servidor.utilidades.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import servidor.dao.GestorObjetoDAO;
import servidor.persistencia.GestorBD;
import servidor.persistencia.IConstantes;


/****falta acomodar*////
public class GestorAnteproyectosDAO implements GestorObjetoDAO{
	private GestorArchivo gestor;
	private GestorBD gestorBD;
	public GestorAnteproyectosDAO() {
            this.gestor=new GestorArchivo();
            this.gestorBD= new GestorBD();
	}
	@Override
	public boolean registrarObjeto(Object obj) {		
		@SuppressWarnings("unchecked")
		Hashtable<String,String> object=(Hashtable<String,String>)obj;
                /*
		 String consulta = "INSERT INTO USUARIO ("+IConstantes.NOMBRES_APELLIDOS+","+IConstantes.IDENTIFICACION+
                    ","+IConstantes.USUARIO+","+IConstantes.CONTRASENIA+","+IConstantes.TIPO_USUARIO+") VALUES"
                    + "('"+object.get(IConstantes.NOMBRES_APELLIDOS)+"','"+object.get(IConstantes.IDENTIFICACION)+"',"
                    + "'"+object.get(IConstantes.USUARIO)+"','"+object.get(IConstantes.CONTRASENIA)+"','"+object.get(IConstantes.TIPO_USUARIO)+"')";            
                return this.gestorBD.executeUpdate(consulta);             */
                return false;
		
	}

	@Override
	public Object leerObjeto(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean editarObjeto(Object obj) {
		boolean varResultado=false;
		@SuppressWarnings("unchecked")
		Hashtable<String,String> object=(Hashtable<String,String>)obj;					
		return modifyConcepto(object);
	}

	@Override
	public Object listarObjetos() {		
		return listAnteproyectos();
	}
        
         private boolean modifyConcepto(Hashtable<String,String> object){
            boolean resultado=false;
            String consulta = "UPDATE USUARIOS_ANTEPROYECTO SET "+IConstantes.CONCEPTO+"="+object.get(IConstantes.CONCEPTO) +" WHERE "
                    + ""+IConstantes.CODIGO_ANTEPROYECTO+"="+object.get(IConstantes.CODIGO_ANTEPROYECTO)+"";                    
            resultado=this.gestorBD.executeUpdate(consulta); 
            return resultado;
        }
         
          private ArrayList<Hashtable<String,String>> listAnteproyectos(){
            ArrayList<Hashtable<String,String>> array=new ArrayList<Hashtable<String,String>>();
            Hashtable<String,String> object=null;    
            ResultSet rs;
            try {
                String consulta="SELECT ID_ANTEPROYECTO, TITULO FROM ANTEPROYECTO";
                rs=this.gestorBD.executeQuery(consulta);  
                                             
                while(rs.next()){                   
                    object= new Hashtable<String,String> ();                                       
                    object.put(IConstantes.CODIGO_ANTEPROYECTO, rs.getObject(IConstantes.CODIGO_ANTEPROYECTO).toString());
                    object.put(IConstantes.TITULO, rs.getObject(IConstantes.TITULO).toString());
                    array.add(object);
                }
                rs.close();
            } catch (SQLException ex) {
                
            }
            return array;
        }        

}
