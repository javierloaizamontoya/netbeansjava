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
import pojos.Empleado;
import pojos.Empresa;

/**
 *
 * @author Javier
 */
@Named(value = "empleadoBean")
@SessionScoped
public class EmpleadoBean implements Serializable {

    /**
     * @return the nombreEmpresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * @param nombreEmpresa the nombreEmpresa to set
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    private String nombreEmpresa;

    /* public Empresa buscarEmpresa(String nombreEmpr){
        
        Empresa a = new Empresa();
        EmpresaDAO empDAO = new EmpresaDAO();
         try {
            a = empDAO.buscarEmpresa(nombreEmpr);
        } catch (Exception e) {
             System.out.println("no se ha podido encontrar la empresa");
        }
        
        return null;
        
    }*/
    
    public void editarEmpleado(){
        
        Empleado emp = new Empleado();
        Empresa empr = new Empresa();
        EmpleadoDAO empDAO = new EmpleadoDAO();
        EmpresaDAO emprDAO = new EmpresaDAO();
        System.out.println("editar1");
        emp = empDAO.buscarEmpleado(emplDoc);
        if (getEmplNombre() !=null) {
            emp.setEmplNombre(emplNombre);
        }
        if (getEmplEdad() >=18) {
            emp.setEmplEdad(emplEdad);
        }
        if(empDAO.comprobarEmpresa(getNombreEmpresa())){
            emp.setEmpresa(emprDAO.buscarEmpresa(nombreEmpresa));
        }
        System.out.println("editar2");
        empDAO.editarEmpleado(emp);
        System.out.println("editar3");
        
    }
    
    
    public void crearEmpleado() {
        try {
            EmpresaDAO emprDAO = new EmpresaDAO();
            System.out.println("emp1");
            Empresa empr = new Empresa();
            System.out.println("emp2");
            empr = emprDAO.buscarEmpresa(getNombreEmpresa());
            System.out.println("emp3");
            System.out.println(empr.getEmpNombre());
            System.out.println("emp4");
            Empleado emp = new Empleado(getEmplDoc(), empr, getEmplNombre(), getEmplEdad());
            if (emp.getEmplEdad() < 18) {
                throw new ArithmeticException("Error: edad mínima debe ser 18 años");
            }
            System.out.println("emp5");
            EmpleadoDAO empDAO = new EmpleadoDAO();
            System.out.println("emp6");
            empDAO.crearEmpleado(emp);
            System.out.println("emp7");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error en bean al crear empleado");
        }

    }

    public void buscarEmpleado() {
        Empleado emp = new Empleado();
        EmpleadoDAO empDAO = new EmpleadoDAO();
        emp = empDAO.buscarEmpleado(emplDoc);
        setEmplEdad(emp.getEmplEdad());
        setEmplNombre(emp.getEmplNombre());

    }

    public void borrarEmpleado() {
        Empleado emp = new Empleado();
        EmpleadoDAO empDAO = new EmpleadoDAO();
        System.out.println("borrar1");
        emp = empDAO.buscarEmpleado(emplDoc);
        System.out.println("borrar2");
        empDAO.borrarEmpleado(emp);
        System.out.println("borrar3");

    }

    public String getEmplDoc() {
        return emplDoc;
    }

    public void setEmplDoc(String emplDoc) {
        this.emplDoc = emplDoc;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getEmplNombre() {
        return emplNombre;
    }

    public void setEmplNombre(String emplNombre) {
        this.emplNombre = emplNombre;
    }

    public int getEmplEdad() {
        return emplEdad;
    }

    public void setEmplEdad(int emplEdad) {
        this.emplEdad = emplEdad;
    }

    private String emplDoc;
    private Empresa empresa;
    private String emplNombre;
    private int emplEdad;

    public EmpleadoBean() {
    }

}
