/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.FuncionarioDao;
import dto.FuncionarioDTO;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Funcionario;

/**
 *
 * @author JULIO MARTINEZ
 */
public class FuncionarioController {
    
    private FuncionarioDao funcionarioDao;

    public FuncionarioController(FuncionarioDao funcionarioDao) {
        this.funcionarioDao = funcionarioDao;
    }
    
    public DefaultTableModel llenarTabla(){
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        model.addColumn("Id");
        model.addColumn("Número Doc.");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Fecha Nac.");
        model.addColumn("Tipo ID");
        model.addColumn("Estado Civil");
        List<FuncionarioDTO> funcionarios = funcionarioDao.findAll();
        String [] registros = new String[7];
        int id = 1;
        for (FuncionarioDTO f: funcionarios){
            registros[0] = String.valueOf(id);
            registros[1] = f.getNumeroIdentificacion();
            registros[2] = f.getNombres();
            registros[3] = f.getApellidos();
            registros[4] = f.getFechaNacimiento().toString();
            registros[5] = f.getDescripcionTipoIdentificacion();
            registros[6] = f.getDescripcionEstadoCivil();
            model.addRow(registros);
            id++;
        }
        return model;
    }
    
    public Funcionario listarPorDocumento(String documento){
        return funcionarioDao.findByDocumento(documento);
    }
    
    public int guardar(Funcionario funcionario){
        return funcionarioDao.save(funcionario);
    }
    
    public void eliminar(String documento){
         funcionarioDao.delete(documento);
         
    }
    
    
    // TODO: llamar los métodos restantes: editar y eliminar
    
     
    
}
