package model.Persona;
import model.ActivYrutina.Actividad;
import model.ActivYrutina.Rutina;
import model.Enum.EGenero;
import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.Otros.Factura;
import model.interfaces.I_toJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

public class Cliente extends Persona implements I_toJson, Serializable {
    private boolean alta_medica;
    private boolean solicito_rutina;
    private boolean debe;
    private Rutina rutina;
    private LocalDate fechaUltimoPago;


    private LocalDate fecha_de_inscripcion;
    private LinkedHashSet<Factura> listaFacturas;
    private TreeSet<Actividad> actividades_cliente;

    public Cliente()
    {
        super();
        alta_medica=true;
        solicito_rutina=true;
        debe=true;
        fecha_de_inscripcion = null;
        rutina = null;
        listaFacturas=new LinkedHashSet<>();
        actividades_cliente=new TreeSet<>();
    }

    public Cliente(String nombre, String dni, EGenero genero, String telefono, String domicilio, String email, LocalDate fecha_de_inscripcion, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, boolean alta_medica, LocalDate fecha_nacimiento, String comentario, boolean solicito_rutina, boolean debe) {
        super(nombre, dni,genero, telefono, domicilio,email ,estado, grupo_sanguineo, contacto_emergencia, obra_social, fecha_nacimiento, comentario);
        this.alta_medica = alta_medica;
        this.solicito_rutina = solicito_rutina;
        this.debe = debe;
        rutina = null;
        fechaUltimoPago = fecha_de_inscripcion;/**La fecha de ultimo pago cuando se crea el cliente es la de inscripcion(paga al instante)*/
        this.fecha_de_inscripcion = fecha_de_inscripcion;
        listaFacturas=new LinkedHashSet<>();
        actividades_cliente=new TreeSet<>();
    }
    /**Este constructor es para el fromJson*/
    public Cliente(JSONObject jo) throws JSONException {
        super(jo);
        listaFacturas=new LinkedHashSet<>();
        actividades_cliente=new TreeSet<>();
    }

    //el constructor de aca abajo recibe la lista de actividades por parametro, lo cual sera necesario al momento de realizar inscripcion (no borrar)
    public Cliente(String nombre, String dni,EGenero genero, String telefono, String domicilio,String email, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, LocalDate fecha_nacimiento, String comentario, boolean alta_medica, boolean solicito_rutina, boolean debe, Rutina rutina, LocalDate fecha_de_inscripcion, TreeSet<Actividad> actividades_cliente) {
        super(nombre, dni,genero, telefono, domicilio, email,estado, grupo_sanguineo, contacto_emergencia, obra_social, fecha_nacimiento, comentario);
        this.alta_medica = alta_medica;
        this.solicito_rutina = solicito_rutina;
        this.debe = debe;
        this.rutina = rutina;
        fechaUltimoPago = fecha_de_inscripcion;/**La fecha de ultimo pago cuando se crea el cliente es la de inscripcion(paga al instante)*/
        this.fecha_de_inscripcion = fecha_de_inscripcion;
        this.actividades_cliente = actividades_cliente;
    }

    public Cliente(boolean alta_medica, boolean solicito_rutina, boolean debe, Rutina rutina, LocalDate fecha_de_inscripcion, TreeSet<Actividad> actividades_cliente) {
        this.alta_medica = alta_medica;
        this.solicito_rutina = solicito_rutina;
        this.debe = debe;
        this.rutina = rutina;
        fechaUltimoPago = fecha_de_inscripcion;
        this.fecha_de_inscripcion = fecha_de_inscripcion;
        this.actividades_cliente = actividades_cliente;
    }

    public boolean agregarFactura(Factura factura){
        listaFacturas.add(factura);
        return true;
    }

    /**Bloque get ---------------------------------------------------------------------------------------*/
    public boolean isAlta_medica() {
        return alta_medica;
    }
    public boolean isSolicito_rutina() {
        return solicito_rutina;
    }
    public boolean isDebe() {
        return debe;
    }
    public LocalDate getFechaUltimoPago() {
        return fechaUltimoPago;
    }
    public LocalDate getFecha_de_inscripcion() {
        return fecha_de_inscripcion;
    }

    public Rutina getRutina() {
        return rutina;
    }

    /**Bloque set ---------------------------------------------------------------------------------------*/
    private void setAlta_medica(boolean alta_medica) {
        this.alta_medica = alta_medica;
    }
    private void setSolicito_rutina(boolean solicito_rutina) {
        this.solicito_rutina = solicito_rutina;
    }
    public void setDebe(boolean debe) {
        this.debe = debe;
    }
    private void setRutina(Rutina rutina) {
        this.rutina = rutina;
    }
    private void setFecha_de_inscripcion(LocalDate fecha_de_inscripcion) {
        this.fecha_de_inscripcion = fecha_de_inscripcion;
    }

    public void setActividades_cliente(TreeSet<Actividad> actividades_cliente) {
        this.actividades_cliente = actividades_cliente;
    }

    public void setFechaUltimoPago(LocalDate fechaUltimoPago) {
        this.fechaUltimoPago = fechaUltimoPago;
    }
    /**--------------------------------------------------------------------------------------------------*/

    public String getDebe(){
        //metodo que devuelve si debe o no el cliente, en un formato de texto legible para cualquier usuario
        //Utilidad : mostrar datos en un formato amigable, y que no se visualice true/false
        String rta = "No";
        if(isDebe()){
            rta = "Si";
        }
        return rta;
    }

    public String getAltaMedica(){
        String rta = "No";
        if(isAlta_medica()){
            rta = "Si";
        }
        return rta;
    }

    public String getSolicitoRutina(){
        String rta = "No";
        if(isSolicito_rutina()){
            rta = "Si";
        }
        return rta;
    }


    @Override
    public String toString() {
        if(rutina==null){
            rutina = new Rutina();
        }
        if(listaFacturas==null){
            listaFacturas = new LinkedHashSet<>();
        }
        return  super.toString() + "Cliente{" +
                "alta_medica=" + alta_medica +
                ", solicito_rutina=" + solicito_rutina +
                ", debe=" + debe +
                ", fecha de ultimo pago=" + fechaUltimoPago +
                ", fecha de inscripcion=" + fecha_de_inscripcion +
                ", Rutinas=" + rutina.toString() +
                ", listaFacturas=" + listaFacturas.toString() +
                ", hashDeActividades=" + actividades_cliente.toString() +
                "\n";
    }

    public JSONObject toJsonObj() throws JSONException {
        JSONObject jsonObject = super.toJsonObj();

        jsonObject.put("Solicita rutina", isSolicito_rutina());
        jsonObject.put("Alta Medica", isAlta_medica());
        jsonObject.put("Debe", isDebe());
        jsonObject.put("Fecha de Inscripcion", getFecha_de_inscripcion().toString());
        jsonObject.put("Fecha de ultimo pago", getFechaUltimoPago().toString());/**Agregue esto*/

        if(rutina !=null) {
            jsonObject.put("Rutina", rutina.toJsonObj());
        }
        
        if(listaFacturas==null){
            listaFacturas = new LinkedHashSet<>();
        }

        JSONArray jsonArray = new JSONArray();
        int i =0;
        Iterator<Factura> it = listaFacturas.iterator();
        while(it.hasNext()){
            jsonArray.put(i,it.next().toJsonObj());
            i++;
        }
        jsonObject.put("Facturas", jsonArray);


        JSONArray jsonArray1 = new JSONArray();
        Iterator<Actividad> it2 = actividades_cliente.iterator();
        i=0;
        while(it2.hasNext()){
            jsonArray1.put(i, it2.next().toJsonObj());
            i++;
        }
        jsonObject.put("Actividades", jsonArray1);

        return jsonObject;
    }

    @Override
    public Cliente fromJson(JSONObject jo) throws JSONException {
        Cliente cliente = new Cliente(jo);
        cliente.setDebe(jo.getBoolean("Debe"));
        cliente.setAlta_medica(jo.getBoolean("Alta Medica"));
        cliente.setSolicito_rutina(jo.getBoolean("Solicita rutina"));
       // cliente.setFecha_de_inscripcion((LocalDate) jo.get("Fecha de Inscripcion"));

        String fecha_string = jo.getString("Fecha de Inscripcion");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(fecha_string, formatter);

        cliente.setFecha_de_inscripcion(fecha);
        /**Agregue esto ---------------------------------------------------------------------------*/
        String fecha_string2 = jo.getString("Fecha de ultimo pago");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha2 = LocalDate.parse(fecha_string2, formatter2);

        cliente.setFechaUltimoPago(fecha2);
        /**------------------------------------------------------------------------------------------*/

        if(jo.has("Rutina")) {
            Rutina rutina = new Rutina();
            cliente.setRutina(rutina.fromJson(jo.getJSONObject("Rutina")));
        }
        JSONArray jsonArray = jo.getJSONArray("Facturas");
        for(int i=0; i<jsonArray.length();i++){
            Factura factura = new Factura();
            cliente.listaFacturas.add(factura.fromJson(jsonArray.getJSONObject(i)));
        }

        JSONArray jsonArray2 = jo.getJSONArray("Actividades");
        for(int i=0; i<jsonArray2.length();i++){
            Actividad actividad = new Actividad();
            cliente.actividades_cliente.add(actividad.fromJson(jsonArray2.getJSONObject(i)));
        }
        return cliente;
    }

    public void agregarActividad(Actividad actividad){
        actividades_cliente.add(actividad);
    }
    public void agregarActividad(TreeSet<Actividad> actividades){
        actividades_cliente.addAll(actividades);
    }

    /**

     Permite obtener una cadena de texto que incluya informacion adicional del cliente*/
 public String MostrarInformacionAdicional(){
        String rta = "";
        if(getEstado().toString().equals("INACTIVO")){
            rta += "CLIENTE INACTIVO\n";}

        if(getCantidadApercibimientos()>0){

            rta += "\nApercibimientos:\n" + DescripcionApercibimientos() + "\n";
        }

        if (!isAlta_medica()){
            rta += "\nAlta Médica Pendiente" + "\n";
        }

        if(!getComentario().equals("")){
            rta += "\nComentario/s:" + getComentario() + "\n";
        }

        if(actividades_cliente.size()>0){ //si tiene actividades las listamos
            rta+= "Inscripto en:" + listarActividades();
        }

        rta+= "\n\nContacto de Emergencia: " + getContacto_emergencia() + "\n" +
                "Obra Social: " + getObra_social() + "\n" +
                "Grupo Sanguíneo: " + getGrupo_sanguineo().toString() + "\n" +
                "Fecha de Inscripción: " + getFecha_de_inscripcion().toString();

        return rta;
    }

    public String listarActividades(){
        String rta = "";
        for(Actividad actividad:actividades_cliente){
            rta += actividad.MostrarActividadBasica() + "\n";
        }
        return rta;
    }

    public Factura pagar(){
        double monto = 0;

        Iterator<Actividad> it = actividades_cliente.iterator();

        while(it.hasNext()){
            monto += it.next().getPrecio_mensual();
        }


        LocalDate fecha = LocalDate.now();
        String mes = fecha.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
        mes = mes.substring(0, 1).toUpperCase() + mes.substring(1);


        Factura factura = new Factura(mes, String.valueOf(LocalDate.now().getYear()), getDni(), getNombre(), LocalDate.now(),monto);
        setDebe(false);
        setFechaUltimoPago(LocalDate.now());
        listaFacturas.add(factura);

        return factura;
    }

    public void modificar(String nombre, String dni, EGenero genero, String telefono, String domicilio, String email, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, LocalDate fecha_nacimiento, String comentario,  boolean alta_medica, boolean solicito_rutina, boolean debe, Rutina rutina, TreeSet<Actividad> actividades_cliente) {
        super.modificar(nombre, dni, genero, telefono, domicilio, email, estado, grupo_sanguineo, contacto_emergencia, obra_social, fecha_nacimiento, comentario);
        setAlta_medica(alta_medica);
        setSolicito_rutina(solicito_rutina);
        setDebe(debe);
        setRutina(rutina);
        setActividades_cliente(actividades_cliente);
    }

    public void borrarActividad(Actividad actividad){
        actividades_cliente.remove(actividad);
    }



    public void cambiarEstadoRutina()
    {
        solicito_rutina=false;
    }

    public void agregarRutina(Rutina rutinaNueva)
    {
        rutina=rutinaNueva;
    }

    public void pedidoRutina()
    {
        solicito_rutina=true;
    }

    public void CambiarSolicitudRutina(boolean valor){
            setSolicito_rutina(valor);
    }
    
        /**
     * Permite dar cuenta de si el cliente se encuentra en un estado inactivo.
     * @return true si el cliente se encuentra en un estado invalido, como inactivo o baneado.
     */
    public boolean EstadoInvalido(){
        boolean rta = false;
        if(getEstado()==Eestado.INACTIVO || getEstado()==Eestado.BANEADO){
            rta = true;
        }
        return rta;
    }

}
