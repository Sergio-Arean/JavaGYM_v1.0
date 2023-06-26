/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.ApiClima;


import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author sergi
 */
public class GestionClima {
    public String ConsumirDatos(){
        //este metodo utiliza la clase de ConsumoAPI, toma los datos del clima actual del momento
        //en que es invocado el metodo, y devuelve el String correspondiente a la informacion del Clima,
        //utilizando para eso la clase Clima.
        String rta ="";
        JSONObject jsonObject_principal;
        JSONObject jsonObject_location;
        JSONObject jsonObject_current;
        JSONObject jsonObject_condition;
        try {
            jsonObject_principal = new JSONObject(ConsumoAPI.getInfo());
            jsonObject_location = jsonObject_principal.getJSONObject("location");
            jsonObject_current = jsonObject_principal.getJSONObject("current");
            jsonObject_condition = jsonObject_current.getJSONObject("condition");

            Clima clima_actual = new Clima();
            clima_actual.setCiudad(jsonObject_location.getString("name"));
            clima_actual.setProvincia(jsonObject_location.getString("region"));
            clima_actual.setPais(jsonObject_location.getString("country"));

            clima_actual.setFecha_y_hora_ultima_actualizacion(jsonObject_current.getString("last_updated"));
            clima_actual.setTemperatura(jsonObject_current.getDouble("temp_c"));
            clima_actual.setSensacion_termica(jsonObject_current.getDouble("feelslike_c"));

            clima_actual.setDescripcion(jsonObject_condition.getString("text"));
            clima_actual.setIcono(jsonObject_condition.getString("icon"));

            rta = clima_actual.toString();

        } catch (JSONException e) {
            rta = e.getMessage();
        }
        return rta;
    }
    
    public Clima DatosClima() throws JSONException{
        JSONObject jsonObject_principal;
        JSONObject jsonObject_location;
        JSONObject jsonObject_current;
        JSONObject jsonObject_condition;

            jsonObject_principal = new JSONObject(ConsumoAPI.getInfo());
            jsonObject_location = jsonObject_principal.getJSONObject("location");
            jsonObject_current = jsonObject_principal.getJSONObject("current");
            jsonObject_condition = jsonObject_current.getJSONObject("condition");

            Clima clima_actual = new Clima();
            clima_actual.setCiudad(jsonObject_location.getString("name"));
            clima_actual.setProvincia(jsonObject_location.getString("region"));
            clima_actual.setPais(jsonObject_location.getString("country"));

            clima_actual.setFecha_y_hora_ultima_actualizacion(jsonObject_current.getString("last_updated"));
            clima_actual.setTemperatura(jsonObject_current.getDouble("temp_c"));
            clima_actual.setSensacion_termica(jsonObject_current.getDouble("feelslike_c"));

            clima_actual.setDescripcion(jsonObject_condition.getString("text"));
            clima_actual.setIcono(jsonObject_condition.getString("icon"));
            

                            
        return clima_actual;
    }
    
}
