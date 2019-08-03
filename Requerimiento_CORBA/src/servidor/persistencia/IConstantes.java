package servidor.persistencia;

public class IConstantes {
	//Inicio Sesion
	public static final String USUARIO="USER";
	public static final String CONTRASENIA="PASSWORD";
	public static final String NUEVA_CONTRASENIA="NEW_PASSWORD";
	public static final String TIPO_USUARIO="ROL";
	
	//Usuario
	public static final String NOMBRES_APELLIDOS="NOMBRE_APELLIDO";       
	public static final String IDENTIFICACION="ID_USUARIO";
	
	//Anteproyecto
	public static final String MODALIDAD="MODALIDAD";
	public static final String TITULO="TITULO";
	public static final String CODIGO_ANTEPROYECTO="ID_ANTEPROYECTO";
	public static final String NOMBRE_EST_1="NOMBRE_EST_1";
	public static final String NOMBRE_EST_2="NOMBRE_EST_2";
	public static final String NOMBRE_DIRECTOR="DIRECTOR";
	public static final String NOMBRE_CO_DIRECTOR="CO_DIRECTOR";
	public static final String FECHA_REGISTRO="FECHA_REGISTRO";
	public static final String FECHA_APROBACION="FECHA_APROBACION";
	public static final String CONCEPTO="CONCEPTO";
	public static final String ESTADO="ESTADO";
	public static final String NUMERO_REVISION="NUMERO_REVISION";
	
	//Evaluadores
	public static final String NOMBRE_EVAL_1="NOMBRE_EVAL_1";
	public static final String CONCEPTO_EVAL_1="CONCEPTO_EVAL_1";
	public static final String FECHA_REVISION_1="FECHA_REVISION_1";
	public static final String NOMBRE_EVAL_2="NOMBRE_EVAL_2";
	public static final String CONCEPTO_EVAL_2="CONCEPTO_EVAL_2";
	public static final String FECHA_REVISION_2="FECHA_REVISION_2";
        public static final String FECHA_REVISION="FECHA_REVISION";
	
	
	//Ubicacion archivos
	public static final String PATH_ADMIN="admin.txt";
	public static final String PATH_USERS="usuarios.txt";
	public static final String PATH_ANTEPROYECTOS="anteproyectos.txt";
	public static final String PATH_EVALUADORES="evaluadores.txt";
	
	//tipos de usuario
	public static final int JD=1;
	public static final int ED=2;
	public static final int E=3;
	public static final int NINGUNO=-1;
}
