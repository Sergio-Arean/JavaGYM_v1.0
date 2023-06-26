package excepciones;

public class UsuarioExistenteException extends Exception{
    public UsuarioExistenteException() {
        super("El nombre de usuario elegido no esta disponible. Debera elegir otro"); 
    }
}
