/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.ApiClima;

/**
 *
 * @author sergi
 */
public class Clima {
        /*Clase Clima
- ciudad
- provincia
- pais

- fecha_y_hora_ultima_actualizacion
- temperatura (double)
- descripcion (text)
- icono
*/
    private String ciudad;
    private String provincia;
    private String pais;
    private String fecha_y_hora_ultima_actualizacion;
    private double temperatura;
    private String descripcion;
    private String icono;

    private double sensacion_termica;

    EDescripcionClima estado;


    public Clima(){

    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getFecha_y_hora_ultima_actualizacion() {
        return fecha_y_hora_ultima_actualizacion;
    }

    public void setFecha_y_hora_ultima_actualizacion(String fecha_y_hora_ultima_actualizacion) {
        this.fecha_y_hora_ultima_actualizacion = fecha_y_hora_ultima_actualizacion;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        DescripcionToEstado();
    }
private void DescripcionToEstado(){
    if(descripcion.equals("Sunny")){
        setEstado(EDescripcionClima.Soleado);
    }
    else if(descripcion.equals("Cloudy")){
        setEstado(EDescripcionClima.Nuboso);
    }
    else if(descripcion.equals("Clear")){
        setEstado(EDescripcionClima.Despejado);
    }
    else if(descripcion.equals("Fog")){
        setEstado(EDescripcionClima.Niebla);
    }
    else if(descripcion.equals("Partly cloudy")){
        setEstado(EDescripcionClima.ParcialmenteNublado);
    }
    else if(descripcion.equals("Overcast")){
        setEstado(EDescripcionClima.Cubierto);
    }
    else if(descripcion.equals("Mist")){
        setEstado(EDescripcionClima.Niebla);
    }
    else if(descripcion.equals("Patchy rain possible")){
        setEstado(EDescripcionClima.PosibleLluviaIntermitente);
    }
    else if(descripcion.equals("Light drizzle")){
        setEstado(EDescripcionClima.LloviznaLeve);
    }
    else if(descripcion.equals("Light rain")){
        setEstado(EDescripcionClima.LluviaLeve);
    }
}


    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public double getSensacion_termica() {
        return sensacion_termica;
    }

    public void setSensacion_termica(double sensacion_termica) {
        this.sensacion_termica = sensacion_termica;
    }

    public EDescripcionClima getEstado() {
        return estado;
    }

    public void setEstado(EDescripcionClima estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Clima{" +
                "ciudad='" + ciudad + '\'' +
                ", provincia='" + provincia + '\'' +
                ", pais='" + pais + '\'' +
                ", fecha_y_hora_ultima_actualizacion='" + fecha_y_hora_ultima_actualizacion + '\'' +
                ", temperatura=" + temperatura +
                ", descripcion='" + descripcion + '\'' +
                ", icono='" + icono + '\'' +
                ", sensacion_termica=" + sensacion_termica +
                ", estado=" + estado +
                '}';
    }
    
}
