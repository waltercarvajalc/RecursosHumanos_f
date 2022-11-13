/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.FuncionarioDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Conexion;
import model.Funcionario;
import utils.Mensajes;

/**
 *
 * @author JULIO MARTINEZ
 */
public abstract class FuncionarioDaoImpl implements FuncionarioDao{

    private Conexion conexion;
    private Statement st;
    private ResultSet rS;
    private PreparedStatement pSt;
    
    @Override
    public List<FuncionarioDTO> findAll() {
        List<FuncionarioDTO> funcionarios = new ArrayList<>();
        try {
            //para hacerlo con DTO
            String sql = "SELECT f.*,t.nombre nombret,e.descripcion desce "
                    + "FROM funcionarios f "
                    + "INNER JOIN tipos_identificacion t ON f.tipos_identificacion_id=t.id "
                    + "INNER JOIN estados_civil e "
                    + "ON f.estados_civil_id=e.id";
            
            //String sql = "SELECT * FROM funcionarios";
            conexion = new Conexion();
            st = conexion.getCon().createStatement();
            rS = st.executeQuery(sql);
            while(rS.next()){
                FuncionarioDTO funcionario = new FuncionarioDTO();
                funcionario.setId(rS.getInt("id"));
                funcionario.setNumeroIdentificacion(rS.getString("numero_identificacion"));
                funcionario.setNombres(rS.getString("nombres"));
                funcionario.setApellidos(rS.getString("apellidos"));
                funcionario.setSexo(rS.getString("sexo").charAt(0));
                funcionario.setDireccion(rS.getString("direccion"));
                funcionario.setTelefono(rS.getString("telefono"));
                funcionario.setFechaNacimiento(LocalDate.parse(rS.getString("fecha_nacimiento")));
                funcionario.setDescripcionTipoIdentificacion(rS.getString("nombret"));
                funcionario.setDescripcionEstadoCivil(rS.getString("desce"));
                funcionarios.add(funcionario);
            }
            st.close();
            rS.close();
            conexion.getCon().close();
        } catch (SQLException ex) {
            Mensajes.mensajeError("Error de BBDD", ex.getMessage());
        }
        return funcionarios;
    }

    @Override
    public Funcionario findByDocumento(String documento) {
        Funcionario funcionario = new Funcionario();
        String sql = "SELECT * FROM funcionarios WHERE "
                + "numero_identificacion=?";
        conexion = new Conexion();
        try {
            pSt = conexion.getCon().prepareStatement(sql);
            pSt.setString(1, documento);
            rS = pSt.executeQuery();
            if(rS.first()){
                funcionario.setId(rS.getInt("id"));
                funcionario.setNumeroIdentificacion(rS.getString("numero_identificacion"));
                funcionario.setNombres(rS.getString("nombres"));
                funcionario.setApellidos(rS.getString("apellidos"));
                funcionario.setFechaNacimiento(LocalDate.parse(rS.getString("fecha_nacimiento")));
                // setear el resto de campos obligatoriamente
            }
            pSt.close();
            rS.close();
            conexion.getCon().close();
        } catch (SQLException ex) {
            Mensajes.mensajeError("Error de BBDD", ex.getMessage());
        }
        return funcionario;
    }
    
    @Override
    public int save(Funcionario funcionario) {
        int resultado = 0;
        String sql = "INSERT INTO funcionarios(" +
        "    numero_identificacion," +
        "    nombres," +
        "    apellidos," +
        "    sexo," +
        "    direccion," +
        "    telefono," +
        "    fecha_nacimiento," +
        "    tipos_identificacion_id," +
        "    estados_civil_id" +
        ")" +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        conexion = new Conexion();
        try {
            pSt = conexion.getCon().prepareStatement(sql);
            pSt.setString(1, funcionario.getNumeroIdentificacion());
            pSt.setString(2, funcionario.getNombres());
            pSt.setString(3, funcionario.getApellidos());
            pSt.setString(4, String.valueOf(funcionario.getSexo()));
            pSt.setString(5, funcionario.getDireccion());
            pSt.setString(6, funcionario.getTelefono());
            pSt.setString(7, funcionario.getFechaNacimiento().toString());
            pSt.setInt(8, funcionario.getTipoIdentificacion().getId());
            pSt.setInt(9, funcionario.getEstadoCivil().getId());
            resultado = pSt.executeUpdate();
            pSt.close();
            conexion.getCon().close();
        } catch (SQLException ex) {
            Mensajes.mensajeError("Error de BBDD", ex.getMessage());
        }
        return resultado;
    }

    @Override
    public int update(Funcionario funcionario) {
        // TODO: Implementar parecido al save
        int resultado = 0;
       String sql = "UPDATE funcionarios SET numero_identificacion=?,nombres=?,apellidos=?,"
               + "  sexo=?,direccion=?,telefono=?,fecha_nacimiento=?,tipos_identificacion_id=?,"
               + "estados_civil_id=?";
       conexion = new Conexion();
        try {
            pSt = conexion.getCon().prepareStatement(sql);
            pSt.setString(1, funcionario.getNumeroIdentificacion());
            pSt.setString(2, funcionario.getNombres());
            pSt.setString(3, funcionario.getApellidos());
            pSt.setString(4, String.valueOf(funcionario.getSexo()));
            pSt.setString(5, funcionario.getDireccion());
            pSt.setString(6, funcionario.getTelefono());
            pSt.setString(7, funcionario.getFechaNacimiento().toString());
            pSt.setInt(8, funcionario.getTipoIdentificacion().getId());
            pSt.setInt(9, funcionario.getEstadoCivil().getId());
            resultado = pSt.executeUpdate();
            pSt.close();
            conexion.getCon().close();
        } catch (SQLException ex) {
            Mensajes.mensajeError("Error de BBDD", ex.getMessage());
        }
        return resultado;
       
    }
    
    @Override
    public void delete(String documento) {
        String sql = "DELETE FROM funcionarios WHERE "
                + "numero_identificacion=?";
        conexion = new Conexion();
        try {
            pSt = conexion.getCon().prepareStatement(sql);
            pSt.setString(1, documento);
            pSt.executeUpdate();
            conexion.getCon().close();
        } catch (SQLException ex) {
            Mensajes.mensajeError("Error de BBDD", ex.getMessage());
        }
    }
    
}
