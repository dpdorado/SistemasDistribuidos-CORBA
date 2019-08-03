package servidor.utilidades.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class GestorArchivo {
	//Atributos 
    protected File file;
    
    private BufferedWriter bw;
    private BufferedReader bf;
    
	public GestorArchivo() {}
    
    //Abrir archivo
	public boolean openFile(String path) {
		boolean varResultado=false;
		try {
			this.file=new File(path);
		    if (!file.exists()){
		        varResultado=file.createNewFile();
		    }else {
		    	varResultado=true;
		    }
		    return varResultado;	        	
		}catch(Exception e) {
			return false;
		}
	 }
	
	public boolean initializeWriter() {
		try {
			bw = new BufferedWriter(new FileWriter(this.file,true));
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean initializeRead() {
		try {
			bf = new BufferedReader(new FileReader(this.file));
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	 //Escribir linea
    public boolean writeLine(String line){        
        if (this.bw==null) {
        	this.initializeWriter();
        }
        try{
            this.bw.write(line);
            this.bw.newLine();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }
    //leer una linea
    public String readLine() {
    	String line=null;
    	if(this.bf==null) {
    		this.initializeRead();
    	}
    	try {
    		line=bf.readLine();
    		return line;
    	}catch(Exception e) {
    		return line;
    	}
    }
	 
    public boolean existsObject(String NKey,String VKey) {
    	this.initializeRead();
    	Hashtable<String, String> datas=null;
    	String line;
    	int bandera=0;
    	while((line=this.readLine())!=null) {
    		if(line.equals("<.dato.>")) {
    			datas=new Hashtable<String, String> ();
    			while((line=this.readLine())!=null) {
    				if(line.equals("<.dato.>")) {
    					break;
    				}
    				String[] parts = line.split("\\|");
            		if(parts.length==2) {
            			datas.put(parts[0], parts[1]);            	
            		}
    			}
    			if (datas.get(NKey).equals(VKey)) {
    				bandera=1;
    				break;
    			}
    		}
    		
    	}
    	return bandera==1?true:false;
    	
    }
    
	//escribir un objeto
    public boolean writeObject(String clave_o,Hashtable<String, String> datas){
    	this.initializeWriter();
        //Verificar que la enumeraci�n no quede vacia
    	/*if(this.existsObject(clave_o, datas.get(clave_o))) {
    		return false;
    	}*/
        Enumeration e = datas.keys();
        Object clave;
        Object valor;
        String line;
        int contador=0;        
        while(e.hasMoreElements() ){
        	if (contador==0) {
        		this.writeLine("<.dato.>");
        		contador=1;
        	}
        	clave = e.nextElement();
        	valor = datas.get( clave );
        	line=(String)clave+"|"+(String)valor;
        	this.writeLine(line);
        }
        return contador==1?true:false;
    }
    //leer un objeto
    public Hashtable<String, String> readObject(String NKey,String VKey){
		System.out.println(NKey);
		System.out.println(VKey);
    	this.initializeRead();
    	Hashtable<String, String> datas=null;
    	String line;
    	int bandera=0;
    	while((line=this.readLine())!=null) {
			System.out.println(line);
    		if(line.equals("<.dato.>")) {
				
    			datas=new Hashtable<String, String> ();
				
    			while((line=this.readLine())!=null) {
					System.out.println(line);
    				if(line.equals("<.dato.>")) {
    					break;
    				}
    				String[] parts = line.split("\\|");
					System.out.println(parts.length);
            		if(parts.length==2) {
            			datas.put(parts[0], parts[1]);    
						System.out.println("entro");
            		}
    			}				
				System.out.println(datas.get(NKey));
				if (datas.get(NKey).equals(VKey)) {
					bandera=1;
					break;
				}
			
    		}
    		
    	}
    	return bandera==1?datas:null;
    }
    //Editar objeto
    public boolean modifyObject(String NKeyB, String VKeyB, String NKeyM, String VKeyM){
    	this.initializeRead();
    	Hashtable<String, String> object;
    	int bandera;
    	// Creo un nuevo archivo
        File fNuevo= new File("aux.txt");
        
        try
        {

	        String linea;
	        while((linea=this.readLine())!=null) {
	        	bandera=0;
	        	if(linea.equals("<.dato.>")) {
	        		object =new Hashtable<String, String> ();
	        		while((linea=this.readLine())!=null) {
	                	if(linea.equals("<.dato.>")) {
	                		break;
	                	}
	                	String[] parts = linea.split("\\|");
	            		if(parts.length==2) {
	            			object.put(parts[0], parts[1]);
	            			bandera++;
	            		}
	                	
	                }
	        		if (bandera>0) {
	        			if(object.get(NKeyB).equals(VKeyB)){
	        				object.put(NKeyM,VKeyM);
	        			}
	        			this.writeNewObject(object, fNuevo); 
	        		}
	        	}
                	
	        }
	        
	        this.bf.close();
            String nAntiguo = this.file.getName();
            this.borrar(this.file);
            fNuevo.renameTo(this.file);
            return true;
        }catch(Exception e)
        {
            System.out.println(e);
            return false;
        }
    }
    public boolean writeNewObject(Hashtable<String, String> datas,File file){
    	BufferedWriter bw;
    	try {
    		bw = new BufferedWriter(new FileWriter(file,true));
	        //Verificar que la enumeraci�n no quede vacia
	        Enumeration e = datas.keys();
	        Object clave;
	        Object valor;
	        String line;
	        int contador=0;        
	        while(e.hasMoreElements() ){
	        	if (contador==0) {        		
	        		 bw.write("<.dato.>");
	                 bw.newLine();
	        		contador=1;
	        	}
	        	clave = e.nextElement();
	        	valor = datas.get( clave );
	        	line=(String)clave+"|"+(String)valor;
	        	 bw.write(line);
                 bw.newLine();
	        }
	        bw.close();
	        return contador==1?true:false;
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }
    
    //listar objetos
	public ArrayList<Hashtable<String, String>> listObjects(){
		this.initializeRead();
		ArrayList<Hashtable<String, String>> datas= new ArrayList<Hashtable<String, String>>();
		Hashtable<String, String> object; 
		String line;
		int bandera;
		while((line=this.readLine())!=null) {
			bandera=0;
			if(line.equals("<.dato.>")) {
				object=new Hashtable<String, String>();
				while((line=this.readLine())!=null) {
					if(line.equals("<.dato.>")) {
						break;
					}
					String[] parts = line.split("\\|");
            		if(parts.length==2) {
            			object.put(parts[0], parts[1]);
            			bandera++;
            		}
				}
				if (bandera>0) {
					datas.add(object);
				}
			}
			
		}
		return datas;
	}
	public void borrar (File Ffichero)
    {
        try
        {
           // Comprovamos si el fichero existe  de ser as� procedemos a borrar el archivo
            if(Ffichero.exists())
            {
                Ffichero.delete();
            }

        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
	 //eliminar referencia al erchivo
	 public void closeFile(){		
		
		this.file=null;
	 }   
}
