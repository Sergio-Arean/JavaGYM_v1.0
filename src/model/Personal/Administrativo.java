package model.Personal;

import model.Enum.EGenero;
import model.Enum.EGrupoSanguineo;
import java.io.Serializable;
import java.time.LocalDate;


public class Administrativo extends Usuario implements Serializable {

    public Administrativo(String nombre, String dni, EGenero genero, String telefono, String domicilio, String email , EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, LocalDate fecha_nacimiento, String comentario, String CUIL, String usuario, String contrasenia) {
        super(nombre, dni,genero, telefono, domicilio,email, grupo_sanguineo,  contacto_emergencia,  obra_social, fecha_nacimiento,  comentario,  CUIL,  usuario,  contrasenia);
    }

    public Administrativo()
    {
        super();
    }

}
