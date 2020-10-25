/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import DAOS.EmpleadoDAO;
import DAOS.EmpresaDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import pojos.Empleado;
import pojos.Empresa;

/**
 *
 * @author Javier
 */
@Named(value = "empresaBean")
@SessionScoped
public class EmpresaBean implements Serializable {

    /**
     * @return the notificacionBorrado
     */
    public String getNotificacionBorrado() {
        return notificacionBorrado;
    }

    /**
     * @param notificacionBorrado the notificacionBorrado to set
     */
    public void setNotificacionBorrado(String notificacionBorrado) {
        this.notificacionBorrado = notificacionBorrado;
    }

    
    public void buscarEmpresa(){
        Empresa empr = new Empresa();
        EmpresaDAO empDAO = new EmpresaDAO();
        empr = empDAO.buscarEmpresa(empNombre);
        setEmpDir(empr.getEmpDir());
        setEmpTel(empr.getEmpTel());
        
    }
    
    
    
    
    public void crearEmpresa(){
        
        Empresa empr = new Empresa(getEmpNombre(), getEmpDir(), getEmpTel());
        EmpresaDAO empDAO = new EmpresaDAO();
        empDAO.crearEmpresa(empr);
    }
    
        public void borrarEmpresa() {
        Empresa emp = new Empresa();
        EmpresaDAO empDAO = new EmpresaDAO();
        System.out.println("borrar1");
        emp = empDAO.buscarEmpresa(getEmpNombre());
        System.out.println("borrar2");
        empDAO.borrarEmpresa(emp);
        System.out.println("borrar3");
        if(emp==null){
            setNotificacionBorrado("Empresa");
        }

    }
        
    public void editarEmpresa(){
        Empleado emp = new Empleado();
        Empresa empr = new Empresa();
        EmpleadoDAO empDAO = new EmpleadoDAO();
        EmpresaDAO emprDAO = new EmpresaDAO();
        
        empr = emprDAO.buscarEmpresa(getEmpNombre());
        
        if(getEmpDir()!=null){
            empr.setEmpDir(getEmpDir());
        }
        if(getEmpTel()!=null){
            empr.setEmpTel(getEmpTel());
        }
        emprDAO.editarEmpresa(empr);
        
    }

    
    
    
        
    public String getEmpNombre() {
        return empNombre;
    }

    /**
     * @param empNombre the empNombre to set
     */
    public void setEmpNombre(String empNombre) {
        this.empNombre = empNombre;
    }

    /**
     * @return the empDir
     */
    public String getEmpDir() {
        return empDir;
    }

    /**
     * @param empDir the empDir to set
     */
    public void setEmpDir(String empDir) {
        this.empDir = empDir;
    }

    /**
     * @return the empTel
     */
    public String getEmpTel() {
        return empTel;
    }

    /**
     * @param empTel the empTel to set
     */
    public void setEmpTel(String empTel) {
        this.empTel = empTel;
    }

    /**
     * @return the empleados
     */
    public Set getEmpleados() {
        return empleados;
    }

    /**
     * @param empleados the empleados to set
     */
    public void setEmpleados(Set empleados) {
        this.empleados = empleados;
    }

    
     private String empNombre;
     private String empDir;
     private String empTel;
     private Set empleados = new HashSet(0);
     private String notificacionBorrado; 
    
    
    
    public EmpresaBean() {
    }
    
}
