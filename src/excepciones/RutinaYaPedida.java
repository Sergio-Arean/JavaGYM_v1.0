package excepciones;

public class RutinaYaPedida extends Exception {

    public RutinaYaPedida()
    {
        super("El cliente seleccionado ya tiene una solicitud de rutina en curso");
    }
}
