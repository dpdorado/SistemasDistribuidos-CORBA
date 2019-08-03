package servidor.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import servidor.dao.GestorObjetoDAO;

public class InicioSesionDAO implements GestorObjetoDAO{
	private GestorArchivo gestor;
        private GestorBD gestorBD;
	
	public InicioSesionDAO() {
            this.gestor=new GestorArchivo();
            this.gestorBD= new GestorBD();
	}
	
	@Override
	public boolean registrarObjeto(Object obj) {
            // TODO Auto-generated method stub
            //No se utiliza.
            return false;
	}

	@Override
	public Object leerObjeto(Object obj) {
            @SuppressWarnings("unchecked")
            Hashtable<String,String> datas=(Hashtable<String,String>)obj;
            Hashtable<String,String> object=null;
            String clave=datas.keys().nextElement();
            String valor=datas.elements().nextElement();
            //Leer objeto admin y si es nulo leer objeto usuario
            gestor.openFile(IConstantes.PATH_ADMIN);
            object=gestor.readObject(clave, valor);
            gestor.closeFile();

            if (object==null) {
                   String consulta="SELECT * FROM USUARIO WHERE USER = '"+valor+"'";
                   object=this.resolverObject(this.gestorBD.executeQuery(consulta));                    
            }
            return object;
	}

	@Override
	public boolean editarObjeto(Object obj) {
            boolean varResultado=false;
            @SuppressWarnings("unchecked")
            Hashtable<String,String> datas=(Hashtable<String,String>)obj;		                
            this.gestor.openFile(IConstantes.PATH_ADMIN);
            varResultado=this.gestor.modifyObject(IConstantes.PATH_USERS, datas.get(IConstantes.PATH_USERS), IConstantes.CONTRASENIA, datas.get(IConstantes.CONTRASENIA));
            this.gestor.closeFile();
            return varResultado;
	}

	@Override
	public Object listarObjetos() {
            // TODO Auto-generated method stub
            //No se utiliza.
            return null;
	}
        
        private Hashtable<String,String> resolverObject(ResultSet rs){
            Hashtable<String,String> object=null;           
            try {
                if(rs.next()){
                    if (object==null){
                        object= new Hashtable<String,String> ();
                    }
                    //object.put(IConstantes.IDENTIFICACION, rs.getObject(IConstantes.IDENTIFICACION).toString());
                    //object.put(IConstantes.NOMBRES_APELLIDOS, rs.getObject(IConstantes.NOMBRES_APELLIDOS).toString());
                    object.put(IConstantes.USUARIO, rs.getObject(IConstantes.USUARIO).toString());
                    object.put(IConstantes.CONTRASENIA, rs.getObject(IConstantes.CONTRASENIA).toString());
                    object.put(IConstantes.TIPO_USUARIO, rs.getObject(IConstantes.TIPO_USUARIO).toString());                   
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(InicioSesionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return object;
        }        

}
