/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainjpa;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import jpatest.Alumne;
import jpatest.Assignatura;
import services.AlumneServices;
import services.AssignaturaServices;

/**
 *
 * @author Kerinvel
 */
public class JPATest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATestPU");
        switchMenu(emf);
    }

    public static void switchMenu(EntityManagerFactory emf) {
        int opcion;
        boolean salir = false;
        do {
            menu();
            //capturamos la opción
            opcion = leerValorEntero();
            switch (opcion) {
                case 1:
                    ejemploJPA(getEM(emf));
                    break;
                case 2:
                    buscarPorCodigo(getEM(emf));
                    break;
                case 3:
                    anadirAlumno(getEM(emf));
                    break;
                case 4:
                    borrarAlumno(getEM(emf));
                    break;
                case 5:
                    //Cambia la booleana para poder salir del bucle do while.
                    salir = true;
                    emf.close();
                    break;
                default: {
                    //En caso de introducir un valor númerico no valido.
                    System.out.println("");
                    System.out.println("No has introducido una opción valida.");
                }
            }
        } while (!salir);
    }

    /**
     * Método que imprime las opciones del menu principal.
     */
    public static void menu() {
        System.out.println("");
        System.out.println("╔══════════════════╗");
        System.out.println("║       MENU PRINCIPAL♫      ║");
        System.out.println("║ 1.Ejemplo de Query findAll ║");
        System.out.println("║ 2.Buscar por código        ║");
        System.out.println("║ 3.Añadir Alumno            ║");
        System.out.println("║ 4.Borrar Alumno            ║");
        System.out.println("║ 5.Salir                    ║");
        System.out.println("╚══════════════════╝");
        System.out.println("");
    }

    public static void ejemploJPA(EntityManager em) {

        Query query = em.createQuery("SELECT a FROM Alumne a");
        @SuppressWarnings("unchecked")
        List<Alumne> ca = (List<Alumne>) query.getResultList();
        for (Alumne a : ca) {
            System.out.println(a.getNom());
        }
        System.out.println("");
        System.out.println("");
        TypedQuery<Alumne> q = em.createNamedQuery("Alumne.findAll", Alumne.class);
        List<Alumne> rl = q.getResultList();
        for (Alumne a : rl) {
            System.out.println(a.getNom());
        }
        em.close();
    }

    public static void buscarPorCodigo(EntityManager em) {
        int cod;
        
        System.out.println("Escribe el numero de código del alumno a buscar:");
        cod = leerValorEntero();
        AlumneServices as = new AlumneServices(em);
        AssignaturaServices ass = new AssignaturaServices(em);

        if (as.findAlumne(cod) == null) {
            System.out.println("El Alumno con el código " + cod + " no existe.");
            em.close();
        } else {
            List<Alumne> la = as.findNameAlumne(cod);
            for (Alumne a: la) {
                System.out.println("Nombre: "+a.getNom());
            }
            List<Assignatura> las=ass.findAssignaturasAlumne(cod);
            System.out.println("Assignaturas:");
            for(Assignatura assig: las){
                System.out.println(assig.getNom());
            }
            
            em.close();
        }
    }

    public static boolean verificarCodigo(EntityManager em, int cod) {
        boolean encontrado = false;
        AlumneServices as = new AlumneServices(em);
        if (as.findAlumne(cod) == null) {
            System.out.println("El Alumno con el código " + cod + " no existe.");
            return encontrado;
        } else {
            System.out.println("Encontrado el Alumno con el código " + cod);
            encontrado = true;
            return encontrado;
        }
    }

    public static void anadirAlumno(EntityManager em) {
        int cod;
        String nom;
        AlumneServices as = new AlumneServices(em);
        System.out.println("Código del Alumno nuevo:");
        cod = leerValorEntero();
        System.out.println("Nombre del Alumno nuevo:");
        nom = leerString();
        as.addAlumne(cod, nom);
        System.out.println("Alumno añadido.");
        em.close();
    }

    public static void borrarAlumno(EntityManager em) {
        int cod;
        String nom;
        AlumneServices as = new AlumneServices(em);
        System.out.println("Código del Alumno que queremos eliminar:");
        cod = leerValorEntero();
        if (verificarCodigo(em, cod)) {
            as.removeAlumne(cod);
            System.out.println("Se a eliminado con exito.");
            em.close();

        } else {
            em.close();
        }
    }

    //Método para iniciar EntityManager
    public static EntityManager getEM(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        return em;
    }

    public static int leerValorEntero() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public static String leerString() {
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }
}
