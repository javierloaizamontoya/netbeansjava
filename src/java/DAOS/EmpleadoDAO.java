/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pojos.Empleado;
import pojos.Empresa;
import util.NewHibernateUtil;

/**
 *
 * @author Javier
 */
public class EmpleadoDAO {
    
    JFrame frame;
    public void crearEmpleado(Empleado emp){
        Transaction transaccion = null;
        Session sesion = NewHibernateUtil.getSessionFactory().openSession();
        try {
            transaccion = sesion.beginTransaction();
            sesion.save(emp);
            sesion.getTransaction().commit();
            System.out.println("empleado creado con éxito");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaccion != null) {
                transaccion.rollback();
            }
            /*frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Error al crear empleado");*/
            System.out.println("Error en DAO al crear empleado");
        } finally{
            
            sesion.flush();
            sesion.close();
            
        }
    }
    
    
    public Empleado buscarEmpleado(String documento) {
        
       Empleado emp = new Empleado();
       
        Session sesion = null;

        try {
        List<Empleado> listaEmpleados = null;
        System.out.println("PART1");

        System.out.println("PART2");

        Configuration conf = new Configuration();
        System.out.println("PART3");
        @SuppressWarnings("deprecation")
        SessionFactory sessionfactory = conf.configure().buildSessionFactory();
        System.out.println("PART4");
        sesion = sessionfactory.openSession();
        System.out.println("PART5");

            org.hibernate.Transaction tx = sesion.beginTransaction();
            System.out.println("PART6");
            Query q = sesion.createQuery("FROM Empleado WHERE emplDoc ='"+documento+"'");
            System.out.println("PART7");
            listaEmpleados = (List<Empleado>)q.list();
            System.out.println("PART8");
           // Object[] obj = listaEmpresas.toArray();
            System.out.println("PART9");


           emp = listaEmpleados.get(0);
           System.out.println("PART10");
           System.out.println(emp.getEmplNombre());
           System.out.println("PART11");
            tx.commit();
            System.out.println("PART12");
            sesion.close();
            System.out.println("PART12");
            

        } catch (Exception e) {

            System.out.println("No se encontró empresa");

        } 
        return emp;
    }
    
    
    public void borrarEmpleado(Empleado emp){
        
        Transaction transaccion = null;
        Session sesion = NewHibernateUtil.getSessionFactory().openSession();
        try {
            transaccion = sesion.beginTransaction();
            sesion.delete(emp);
            sesion.getTransaction().commit();
            System.out.println("empleado eliminado con éxito");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaccion != null) {
                transaccion.rollback();
            }
            /*frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Error al crear empleado");*/
            System.out.println("Error en DAO al eliminar empleado");
        } finally{
            
            sesion.flush();
            sesion.close();
            
        }
    
        
        
    }
    
    public void editarEmpleado (Empleado emp){
        
        Transaction transaccion = null;
        Session sesion = NewHibernateUtil.getSessionFactory().openSession();
        try {
            transaccion = sesion.beginTransaction();
            sesion.update(emp);
            sesion.getTransaction().commit();
            System.out.println("empleado editado con éxito");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaccion != null) {
                transaccion.rollback();
            }
            /*frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Error al crear empleado");*/
            System.out.println("Error en DAO al editar empleado");
        } finally{
            
            sesion.flush();
            sesion.close();
            
        }
        
    }
    
    public boolean comprobarEmpresa(String nombre){
        
        EmpresaDAO empDAO = new EmpresaDAO();
        Empresa emp = empDAO.buscarEmpresa(nombre);
        return emp != null;
        
    }

    
}
