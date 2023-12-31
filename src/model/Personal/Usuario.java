package model.Personal;

import model.ActivYrutina.Actividad;
import model.Enum.EGenero;
import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.interfaces.I_toJson;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;


public class Usuario extends Personal implements Serializable, I_toJson {
    private String usuario;
    private String contrasenia;

    public Usuario(String nombre, String dni, EGenero genero, String telefono, String domicilio,String email, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, LocalDate fecha_nacimiento, String comentario, String CUIL, String usuario, String contrasenia) {
        super(nombre,dni,genero,telefono,domicilio,email,Eestado.ACTIVO,grupo_sanguineo,contacto_emergencia,obra_social,fecha_nacimiento,comentario,CUIL);
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public Usuario()
    {
        this("","",null,"","","",null,"","",null,"","","","");
    }

    public Usuario(JSONObject jo) throws JSONException {
        super(jo);
    }

    public String getUsuario() {
        return usuario;
    }

    private String getContrasenia() {
        return contrasenia;
    }

    public boolean ValidarCredenciales(String usuario, String contrasenia)
    {
        return (usuario.equals(getUsuario()) && contrasenia.equals(getContrasenia()));
    }

    private void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    private void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

   
    @Override
    public String toString() {
        return super.toString() +"Usuario{" +
                "usuario='" + usuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                "} " ;
    }

    @Override
    public boolean equals(Object o) {
        boolean rta = false;
        if(o!=null){
            if(o instanceof Usuario){
                Usuario aux = (Usuario) o;
                if(aux.getUsuario().equals(getUsuario()) && aux.getContrasenia().equals(getContrasenia())){
                    rta = true;
                }
            }
        }
        return rta;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public boolean cambiarContrasenia(String viejaContrasenia, String nuevaContrasenia) {
        boolean rta=false;
        if (viejaContrasenia.equals(getContrasenia()))
        {
            contrasenia=nuevaContrasenia;
            rta=true;
        }
        return rta;
    }

    public double calcularCuota (HashSet<Actividad> setActividades) {
        double cuota = 0;
        int cantidad_actividades = 0;

        Iterator<Actividad> it = setActividades.iterator();

        while (it.hasNext()) {
            cantidad_actividades++;
            cuota += it.next().getPrecio_mensual();
        }

        return cuota;
    }

    @Override
    public Usuario fromJson(JSONObject jo) throws JSONException {
        Usuario usuario = new Usuario(jo);
        usuario.setUsuario(jo.getString("Usuario"));
        usuario.setContrasenia(jo.getString("Contraseña"));
        return usuario;
    }

    @Override
    public JSONObject toJsonObj() throws JSONException {
        JSONObject jsonObject = super.toJsonObj();
        jsonObject.put("Usuario", getUsuario());
        jsonObject.put("Contraseña", getContrasenia());
        return jsonObject;
    }
    
        public void cambiarContrasenia(String contrasenia_nueva){
        setContrasenia(contrasenia_nueva);
    }



}
