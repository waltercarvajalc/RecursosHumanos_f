/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import dto.FuncionarioDTO;
import java.util.List;
import model.Funcionario;

/**
 *
 * @author JULIO MARTINEZ
 */
public interface FuncionarioDao {
    // se declaran los métodos del CRUD
    List<FuncionarioDTO> findAll();
    
    Funcionario findByDocumento(String documento);
    
    int save(Funcionario funcionario);
    
    int update(Funcionario funcionario);
    
    void delete(String documento);
}
