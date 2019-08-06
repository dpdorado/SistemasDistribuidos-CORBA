package servidor.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import servidor.dao.GestorObjetoDAO;
import servidor.persistencia.IConstantes;

public class GestorUsuariosDAO implements GestorObjetoDAO{
	private GestorArchivo gestor;
        private GestorBD gestorBD;
	
	public GestorUsuariosDAO() {
		this.gestor=new GestorArchivo();
                this.gestorBD= new GestorBD();
	}
	
	@Override
	public boolean registrarObjeto(Object obj) {
		boolean varResultado=false;
		@SuppressWarnings("unchecked")
		Hashtable<String,String> object=(Hashtable<String,String>)obj;		
		boolean isAdminUs;
                boolean isUsersId;
                boolean isUsersUs;
		this.gestor.openFile(IConstantes.PATH_ADMIN);
		isAdminUs=this.gestor.existsObject(IConstantes.TIPO_USUARIO, object.get(IConstantes.TIPO_USUARIO));
		this.gestor.closeFile();                
		if (isAdminUs){
                    return varResultado;
		}                
                System.out.println("1");
                isUsersId=this.exists(IConstantes.IDENTIFICACION, object.get(IConstantes.IDENTIFICACION));
		 System.out.println("2");
                isUsersUs=this.exists(IConstantes.TIPO_USUARIO, object.get(IConstantes.TIPO_USUARIO));
                 System.out.println("3");
                if (isUsersId || isUsersUs){
                    return varResultado;
		}                
                varResultado = this.writeUser( object);		
		return varResultado;
	}

	@Override
	public Object leerObjeto(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean editarObjeto(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object listarObjetos() {
		// TODO Auto-generated method stub
		return null;
	}
        
        private boolean exists(String clave,String valor){
            boolean varResultado=false;            
            String consulta="SELECT * FROM USUARIO WHERE "+clave+" = '"+valor+"'";
            ResultSet rs=this.gestorBD.executeQuery(consulta); 
            System.out.println(consulta);
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
        
        private boolean writeUser(Hashtable<String,String> object){            
            String consulta = "INSERT INTO USUARIO ("+IConstantes.NOMBRES_APELLIDOS+","+IConstantes.IDENTIFICACION+
                    ","+IConstantes.USUARIO+","+IConstantes.CONTRASENIA+","+IConstantes.TIPO_USUARIO+") VALUES"
                    + "('"+object.get(IConstantes.NOMBRES_APELLIDOS)+"','"+object.get(IConstantes.IDENTIFICACION)+"',"
                    + "'"+object.get(IConstantes.USUARIO)+"','"+object.get(IConstantes.CONTRASENIA)+"','"+object.get(IConstantes.TIPO_USUARIO)+"')";            
            return this.gestorBD.executeUpdate(consulta);             
        }

}
