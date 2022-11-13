/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.EstadoCivilDao;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import model.EstadoCivil;


/**
 *
 * @author JULIO MARTINEZ
 */
public class EstadoCivilController {
    
    private EstadoCivilDao estadoCivilDao;

    public EstadoCivilController(EstadoCivilDao estadoCivilDao) {
        this.estadoCivilDao = estadoCivilDao;
    }
    
    public DefaultComboBoxModel llenarCombo(){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        List<EstadoCivil> estadosCiv = estadoCivilDao.findAll();
        for(EstadoCivil e:estadosCiv){
            modelo.addElement(e.getDescripcion());
        }
        return modelo;
    }
    
}
