package model.Personal;

import model.Enum.EGenero;
import java.io.Serializable;


public class Encargado extends Usuario implements Serializable
{
    public Encargado(String nombre, String dni, EGenero genero, String telefono, String domicilio,String email, String comentario, String CUIL) {
        super(nombre, dni,genero, telefono, domicilio,email,null,"","",null,"","", "encargado2023","encargado2023");
    }




}
