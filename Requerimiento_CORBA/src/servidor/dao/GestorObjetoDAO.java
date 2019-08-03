package servidor.dao;

public interface GestorObjetoDAO {
    public abstract boolean registrarObjeto(Object obj);
    public abstract Object leerObjeto(Object obj);
    public abstract boolean editarObjeto(Object obj);
    public abstract Object listarObjetos();
}
