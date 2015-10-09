/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaqueteDatosPersistencia;

import Clases.Cita;
import Clases.Cubiculo;
import Clases.Material;
import Clases.Paciente;
import Clases.Terapeuta;
import PaqueteDatosInterfacePersistencia.IPersistencia;
import PersistenciaExceptions.PersistenciaException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Oscar
 */
public class Persistencia implements IPersistencia {

    Connection conexion;

    public Persistencia() throws PersistenciaException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://192.168.1.65/caichv1", "OscarDev", "passwordSQL");
        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenciaException("Error al iniciar la conexión.");
        }
    }

    @Override
    public boolean agendar(Cita cita) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(Cita cita) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cita buscar(Cita cita) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizar(Cita cita) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean cancelar(Cita cita) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean activar(Cita cita) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean registrar(Cita cita) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cita> recuperarCitas() throws PersistenciaException {

        List<Cita> listaCitas = new ArrayList<Cita>();
        List<Material> listaMateriales = new ArrayList<Material>();

        Cita cita = new Cita();
        Paciente paciente = new Paciente();
        Cubiculo cubiculo = new Cubiculo();
        Terapeuta terapeuta = new Terapeuta();
        Material material = new Material();
        Material material2 = new Material();
        Material material3 = new Material();

        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM citas");

            while (rs.next()) {

                // Obtiene la clave del paciente y la asigna al paciente.
                paciente.setClave(rs.getString("clavePaciente"));
                //Asigna el paciente a la cita.
                cita.setPaciente(paciente);
                // Obtiene el nombre del terapeuta y lo asigna al terapeuta.
                terapeuta.setNombre(rs.getString("nombreTerapeuta"));
                // Asigna el terapeuta a la cita.
                cita.setTerapeuta(terapeuta);
                // Obtiene el primer material.
                material.setClave(rs.getString("claveMaterial1"));
                //CORREGIR CLAVE NULL -- Es crear otra instancia.
                System.out.println(rs.getString("claveMaterial1"));
                System.out.println(material.getClave());
                // Lo agrega a la lista.
                listaMateriales.add(material);
                // Obtiene el segundo material.
                material2.setClave(rs.getString("claveMaterial2"));
                // Lo agrega a la lista.
                listaMateriales.add(material);
                // Obtiene el tercer material.
                material3.setClave(rs.getString("claveMaterial3"));
                // Lo asigna a la lista.
                listaMateriales.add(material);
                // Agrega la lista a la cita.
                cita.setMateriales(listaMateriales);
                // Obtiene el numero del cubiculo.
                cubiculo.setNumero(rs.getInt("cubiculo"));
                // Asigna el cubiculo a la cita.
                cita.setCubiculo(cubiculo);
                // Obtiene la fecha.
                String fecha = rs.getString("fecha");
                // Se parsea a dia para poder restar un dia. El primer dia de Java es 0, mientras
                // que el de MySQL es 1.
                //CORREGIR. NO ES EL DIA, ES EL MES.
                int diaEntero = Integer.parseInt(fecha.substring(8, 10));
                diaEntero--;
                // Crea una nueva fecha y lo asigna a la cita.
                cita.setFecha(new GregorianCalendar(Integer.parseInt(fecha.substring(0, 4)), Integer.parseInt(fecha.substring(5, 7)), diaEntero));
                // Obtiene el estado y lo asigna a la cita.
                cita.setEstado(rs.getString("estado"));
                // Agrega la lista a la lista de citas.
                listaCitas.add(cita);

            }
            rs.close();

        } catch (Exception e) {
            throw new PersistenciaException("Ocurrió un error al obtener las citas.");
        }

        return listaCitas;

    }

    @Override
    public List<Cita> recuperarCitasActivas() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cita> recuperarCitasCanceladas() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cita> recuperarCitasPorPaciente(Paciente pcnt) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
