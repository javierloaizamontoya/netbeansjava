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
import pojos.Empresa;
import util.NewHibernateUtil;
import org.postgresql.Driver;
import pojos.Empleado;

public class EmpresaDAO {

    //JFrame frame;
    public void crearEmpresa(Empresa empr) {
        Transaction transaccion = null;
        Session sesion = NewHibernateUtil.getSessionFactory().openSession();
        try {
            transaccion = sesion.beginTransaction();
            sesion.save(empr);
            sesion.getTransaction().commit();
            //frame = new JFrame();
            //JOptionPane.showMessageDialog(frame, "Empresa creada con éxito");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaccion != null) {
                transaccion.rollback();
            }
            //frame = new JFrame();
            //JOptionPane.showMessageDialog(frame, "Error al crear empresa");
            System.out.println("Error en DAO al crear empresa");
        } finally {

            sesion.flush();
            sesion.close();

        }

    }

    /*public Empresa buscarEmpresa(String nombre) {
        //SessionFactory factory = new Configuration().configure().buildSessionFactory();
        String consulta = "FROM empresa E WHERE E.emp_nombre =" + nombre;
        //Transaction transaccion = null;
        Session sesion = NewHibernateUtil.getSessionFactory().openSession();
        Query query = sesion.createQuery(consulta);
        List resultados = query.list();
        Empresa x = new Empresa();
        try {

            List list = sesion.createQuery("FROM empresa").list();
            System.out.println(list.get(0));

        } catch (Exception e) {

            System.out.println("ERROR");

        } finally {

            sesion.flush();
            sesion.close();
        }
        return null;
    }*/
    public Empresa buscarEmpresa(String nombre) {

        Empresa empr = new Empresa();

        Session sesion = null;

        try {
            List<Empresa> listaEmpresas = null;
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
            Query q = sesion.createQuery("FROM Empresa WHERE empNombre ='" + nombre + "'");
            System.out.println("PART7");
            listaEmpresas = (List<Empresa>) q.list();
            System.out.println("PART8");
            // Object[] obj = listaEmpresas.toArray();
            System.out.println("PART9");

            empr = listaEmpresas.get(0);
            System.out.println("PART10");
            //System.out.println(empr.getEmpNombre());
            System.out.println("PART11");
            tx.commit();
            System.out.println("PART12");
            sesion.close();
            System.out.println("PART12");

        } catch (Exception e) {

            System.out.println("No se encontró empresa");

        }
        return empr;
    }

    public List<Empleado> buscarEmpleados(Empresa emp) {

        //Empresa empr = new Empresa();
        List<Empleado> listaEmpleados = null;
        List<Empleado> listaIterativa = null;
        Empleado iteracion = null;
        Session sesion = null;

        try {

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
            Query q = sesion.createQuery("FROM Empleado");
            System.out.println("PART7");
            listaEmpleados = (List<Empleado>) q.list();
            System.out.println("TAMAÑO DE LA LISTA DE EMPLEADOS "+listaEmpleados.size());
            System.out.println("PART8");

            for (int i = 0; i < listaEmpleados.size(); i++) {
                if (   ((listaEmpleados.get(i)).getEmpresa().getEmpNombre()).equals(emp.getEmpNombre())) {
                    iteracion = listaEmpleados.get(i);
                    System.out.println(iteracion.getEmplNombre());
                    if (iteracion != null) {
                        listaIterativa.add(iteracion);
                    }
                }
            }
            listaEmpleados = listaIterativa;
            System.out.println("TAMAÑO DE LA LISTA DE EMPLEADOS "+listaEmpleados.size());

            tx.commit();
            sesion.close();

        } catch (Exception e) {

            System.out.println("No se encontró empresa");

        }
        return listaEmpleados;
    }

    public void borrarEmpresa(Empresa emp) {
        List<Empleado> listaEmpleados = null;
        Transaction transaccion = null;
        Session sesion = NewHibernateUtil.getSessionFactory().openSession();
        try {
            listaEmpleados = buscarEmpleados(emp);
            if (listaEmpleados!=null) {
                throw new ArithmeticException("Error: la empresa contiene empleados, por lo tanto no puede ser eliminada");
            } else {

                transaccion = sesion.beginTransaction();
                sesion.delete(emp);
                sesion.getTransaction().commit();
                System.out.println("proceso completado");
            }
            //frame = new JFrame();
            //JOptionPane.showMessageDialog(frame, "Empresa creada con éxito");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaccion != null) {
                transaccion.rollback();
            }
            //frame = new JFrame();
            //JOptionPane.showMessageDialog(frame, "Error al crear empresa");
            System.out.println("Error en DAO al eliminar empresa");
        } finally {

            sesion.flush();
            sesion.close();

        }

    }

    public void editarEmpresa(Empresa empr) {
        
        Transaction transaccion = null;
        Session sesion = NewHibernateUtil.getSessionFactory().openSession();
        try {
            transaccion = sesion.beginTransaction();
            sesion.update(empr);
            sesion.getTransaction().commit();
            System.out.println("empresa editada con éxito");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaccion != null) {
                transaccion.rollback();
            }
            /*frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Error al crear empleado");*/
            System.out.println("Error en DAO al editar empresa");
        } finally{
            
            sesion.flush();
            sesion.close();
            
        }
        
    }

}
