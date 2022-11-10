package EjerciciosClase;

import java.sql.*;
import java.util.Scanner;

public class Ejercicio3 {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args)  {

        Connection con;
        String url = "jdbc:mysql://localhost/acceso_datos";

        try {
            con = DriverManager.getConnection(url, "root", "");
            //System.out.println("Introduce la nota");
            //double nota = teclado.nextDouble();
            //consultaNotaAlumporNota(con, nota);
            System.out.println("¿A quién quieres cambiarle la nota?");
            String nombre = teclado.nextLine();
            System.out.println("¿Que nota quieres ponerle?");
            double nota = teclado.nextDouble();
            //cambiarAlumnoCurso(con,"Tiziano","2DAM");
            cambiarNotaAlumno(con,nombre,nota);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }

    }
    public static void consultaNotaAlumporNota(Connection con,double nota) throws SQLException {
        String sql = "Select * from alumnos WHERE nota_media >= " + nota;
        Statement sentencia = con.createStatement();
        ResultSet rs = sentencia.executeQuery(sql);
        while (rs.next()){
            System.out.println(rs.getString("nombre") + " " + rs.getDate( "f_nacimiento")+" "+
                    rs.getDouble("nota_media")+ " "+  rs.getString("curso"));
        }
        con.close();
    }

    public static void cambiarAlumnoCurso(Connection con, String nombre, String curso) throws SQLException {
        Statement sentencia = con.createStatement();
        int result = sentencia.executeUpdate("UPDATE alumnos SET curso = '"+curso+
                "' WHERE nombre = '"+nombre+"'");
        System.out.println("El numero de registros modificados es: " + result);
    }

    public static void cambiarNotaAlumno(Connection con, String alumno, double nota) throws SQLException {
        Statement sentencia = con.createStatement();
        int result;
        if(alumno.equals("todos")){
             result = sentencia.executeUpdate("UPDATE alumnos SET nota_media = nota_media +"+ nota
                    +"");
        }else{
            result = sentencia.executeUpdate("UPDATE alumnos SET nota_media = nota_media +"+nota+
                    "WHERE nombre = '"+alumno+"'");
        }
        System.out.println("El numero de registros modificados es: " + result);

    }
}