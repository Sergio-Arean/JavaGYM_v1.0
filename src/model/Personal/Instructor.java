package model.Personal;
import model.Enum.EGenero;
import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.interfaces.I_toJson;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.time.LocalDate;

public class Instructor extends Personal implements Serializable, I_toJson {

private String imagenPerfil;

    public Instructor(String nombre, String dni, EGenero genero, String telefono, String domicilio,String email, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, LocalDate fecha_nacimiento, String comentario, String CUIL,String imagenPerfil) {
        super(nombre, dni, genero, telefono,domicilio,email,estado, grupo_sanguineo, contacto_emergencia, obra_social, fecha_nacimiento, comentario, CUIL);
        this.imagenPerfil = imagenPerfil;
    }

    public Instructor()
    {
        super();
        imagenPerfil=" ";
    }

    public Instructor (JSONObject jo) throws JSONException {
        super(jo);
    }


    public String getActividadesACargo(){
        return "===================\nACTIVIDADES A CARGO\n===================\n" ;
    }



    public String getImagenPerfil() {
        return imagenPerfil;
    }

    private void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }


    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public Instructor fromJson(JSONObject jo) throws JSONException {
        Instructor instructor = new Instructor(jo);
        instructor.setImagenPerfil(jo.getString("Imagen de Perfil"));
       return instructor;
    }

    @Override
    public JSONObject toJsonObj() throws JSONException {
        JSONObject jsonObject = super.toJsonObj();
        jsonObject.put("Imagen de Perfil", getImagenPerfil());
        return jsonObject;
    }

    public void modificar(String nombre, String dni, EGenero genero, String telefono, String domicilio,String email, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, LocalDate fecha_nacimiento,String comentario, String cuil, String imagen_de_perfil){
    super.modificar(nombre, dni, genero, telefono, domicilio, email,
            estado, grupo_sanguineo, contacto_emergencia,obra_social,
            fecha_nacimiento,comentario, cuil);
    setImagenPerfil(imagen_de_perfil);
    }

}
