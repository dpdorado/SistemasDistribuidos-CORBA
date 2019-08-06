package servidor.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import servidor.dao.GestorObjetoDAO;
import servidor.persistencia.GestorBD;

public class GestorEvaluadoresDAO implements GestorObjetoDAO{
	private GestorArchivo gestor;
	private GestorBD gestorBD;
	public GestorEvaluadoresDAO() {
		this.gestor=new GestorArchivo();
                this.gestorBD= new GestorBD();
	}
	
	@Override
	public boolean registrarObjeto(Object obj) {
		boolean varResultado=false;
                String auxIDeval1;
                String auxIDeval2;
		@SuppressWarnings("unchecked")
		Hashtable<String,String> object=(Hashtable<String,String>)obj;
		boolean isCodigoAnt;
                                		
		isCodigoAnt=this.exists(IConstantes.CODIGO_ANTEPROYECTO, object.get(IConstantes.CODIGO_ANTEPROYECTO));								
		if (!isCodigoAnt){
                    return varResultado;
		}
		auxIDeval1=this.idEval(object.get(IConstantes.NOMBRE_EVAL_1));
                auxIDeval2=this.idEval(object.get(IConstantes.NOMBRE_EVAL_2));
                
                if (auxIDeval1==null || auxIDeval2==null){
                    return varResultado;
                }
                                               					
		varResultado= this.writeEvals(object,auxIDeval1,auxIDeval2);	
                
		return varResultado;
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
		                                
		if(this.exists(IConstantes.CODIGO_ANTEPROYECTO, object.get(IConstantes.CODIGO_ANTEPROYECTO))) {
                    varResultado=this.modifyConcepto(object);
		}
		return varResultado;
	}

	@Override
	public Object listarObjetos() {
		// TODO Auto-generated method stub
		return null;
	}
        
        /*--------------------------------------*/
        
        private boolean exists(String clave,String valor){
            boolean varResultado=false;            
            String consulta="SELECT * FROM ANTEPROYECTO WHERE "+clave+" = '"+valor+"'";
            ResultSet rs=this.gestorBD.executeQuery(consulta); 
            if (rs!=null){
                try {
                    if(rs.next()){
                        varResultado=true;
                    }
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorUsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
                }                                
            }            
            return varResultado;
        }
        
        private String idEval(String nombres){
            String result=null;
            String consulta="SELECT ID_USUARIO FROM USUARIO WHERE NOMBRE_APELLIDO = '"+nombres+"'";
            ResultSet rs=this.gestorBD.executeQuery(consulta);
            try {
                if(rs.next()){                                        
                    result=rs.getObject(IConstantes.IDENTIFICACION).toString();                    
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(InicioSesionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return result;
        }
        
        private boolean writeEvals(Hashtable<String,String> object, String id1, String id2){  
            boolean r1,r2;            
            String consulta = "INSERT INTO USUARIOS_ANTEPROYECTO ("+IConstantes.CODIGO_ANTEPROYECTO+","+IConstantes.IDENTIFICACION+
                    ","+IConstantes.CONCEPTO+","+IConstantes.FECHA_REVISION+",ROL_ANTEPROYECTO) VALUES"
                    + "('"+object.get(IConstantes.CODIGO_ANTEPROYECTO)+"','"+id1+"',"
                    + "'"+object.get(IConstantes.CONCEPTO_EVAL_1)+"','"+object.get(IConstantes.FECHA_REVISION_1)+"',4)";            
            r1=this.gestorBD.executeUpdate(consulta);             
            consulta = "INSERT INTO USUARIOS_ANTEPROYECTO ("+IConstantes.CODIGO_ANTEPROYECTO+","+IConstantes.IDENTIFICACION+
                    ","+IConstantes.CONCEPTO+","+IConstantes.FECHA_REVISION+",ROL_ANTEPROYECTO) VALUES"
                    + "('"+object.get(IConstantes.CODIGO_ANTEPROYECTO)+"','"+id2+"',"
                    + "'"+object.get(IConstantes.CONCEPTO_EVAL_2)+"','"+object.get(IConstantes.FECHA_REVISION_2)+"',4)";
            r2=this.gestorBD.executeUpdate(consulta);              
            if(r1 && r2){
                return true;
            }
            return false;
        }
        
        private boolean modifyConcepto(Hashtable<String,String> object){
            boolean resultado=false;
            String consulta = "UPDATE USUARIOS_ANTEPROYECTO SET "+IConstantes.CONCEPTO+"="+object.get(IConstantes.CONCEPTO) +" WHERE "
                    + ""+IConstantes.CODIGO_ANTEPROYECTO+"="+object.get(IConstantes.CODIGO_ANTEPROYECTO)+" AND "+IConstantes.NOMBRES_APELLIDOS+"="+object.get(IConstantes.NOMBRES_APELLIDOS)+"";                    
            resultado=this.gestorBD.executeUpdate(consulta); 
            return resultado;
        }

}
