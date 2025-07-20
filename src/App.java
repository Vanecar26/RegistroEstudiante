import java.util.Scanner;

public class App {
      // Variables de alcance de clase
    static String estudianteNombre = "N/A";
    static double nota1 = -1, nota2 = -1, nota3 = -1;
    static boolean estudianteRegistrado = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
// Bucle principal del menú
        System.out.println("Bienvenido al sistema de registro de estudiantes.");
        do {
            mostrarMenu();
            System.out.print("Ingrese su opción: ");
            opcion = leerEntero(scanner);

            switch (opcion) {
                case 1:
                    if (estudianteRegistrado) {
                        System.out.print("Ya hay un estudiante registrado. ¿Desea sobrescribir los datos? (s/n): ");
                        String confirmacion = scanner.nextLine().trim().toLowerCase();
                        if (!confirmacion.equals("s")) {
                            System.out.println("Operación cancelada.");
                            break;
                        }
                    }
                    registrarEstudiante(scanner);
                    break;
                case 2:
                    mostrarInfoEstudiante();
                    break;
                case 3:
                    if (!estudianteRegistrado) {
                        System.out.println("No hay estudiante registrado.");
                    } else {
                        double promedio = calcularPromedio();
                        System.out.printf("Promedio: %.2f - %s%n", promedio,
                                (promedio >= 60 ? "Aprobado" : "Reprobado"));
                    }
                    break;
                case 4:
                    limpiarDatosEstudiante();
                    System.out.println("Datos del estudiante eliminados.");
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        scanner.close();
    }
// Método para mostrar el menú principal
    public static void mostrarMenu() {
        System.out.println("\n--- Sistema de Registro de Estudiantes ---");
        System.out.println("1. Registrar datos del estudiante");
        System.out.println("2. Mostrar datos del estudiante actual");
        System.out.println("3. Calcular promedio y ver si aprueba");
        System.out.println("4. Limpiar los datos del estudiante");
        System.out.println("0. Salir");
    }
// Método para registrar los datos del estudiante
    public static void registrarEstudiante(Scanner scanner) {
        String nombre;
        do {
            System.out.print("Ingrese el nombre del estudiante: ");
            nombre = scanner.nextLine();
            if (!validarNombre(nombre)) {
                System.out.println("Nombre inválido. Intente nuevamente.");
            }
        } while (!validarNombre(nombre));

        double n1 = pedirNota(scanner, "Ingrese la primera nota: ");
        double n2 = pedirNota(scanner, "Ingrese la segunda nota: ");
        double n3 = pedirNota(scanner, "Ingrese la tercera nota: ");

        // Mostrar resumen antes de guardar
        System.out.println("\nResumen de datos:");
        System.out.println("Nombre: " + nombre);
        System.out.printf("Notas: %.2f, %.2f, %.2f%n", n1, n2, n3);
        System.out.print("¿Desea guardar estos datos? (s/n): ");
        String confirmar = scanner.nextLine().trim().toLowerCase();

        if (confirmar.equals("s")) {
            estudianteNombre = nombre;
            nota1 = n1;
            nota2 = n2;
            nota3 = n3;
            estudianteRegistrado = true;
            System.out.println("Estudiante registrado con éxito.");
        } else {
            System.out.println("Registro cancelado.");
        }
    }
 // Método para mostrar la información del estudiante
    public static void mostrarInfoEstudiante() {
        if (!estudianteRegistrado) {
            System.out.println("No hay estudiante registrado.");
        } else {
            System.out.println("\n--- Información del Estudiante ---");
            System.out.println("Nombre: " + estudianteNombre);
            System.out.printf("Nota 1: %.2f\nNota 2: %.2f\nNota 3: %.2f\n", nota1, nota2, nota3);
        }
    }
// Método para calcular el promedio de las notas
    public static double calcularPromedio() {
        return (nota1 + nota2 + nota3) / 3;
    }
// Método para validar si una nota está en el rango permitido
    public static boolean validarNota(double nota) {
        return nota >= 0 && nota <= 100;
    }
// Método para validar si un nombre es válido
    public static boolean validarNombre(String nombre) {
        return nombre != null && !nombre.trim().isEmpty();
    }
// Método para pedir una nota al usuario con validación
      public static double pedirNota(Scanner scanner, String mensaje) {
        double nota;
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextDouble()) {
                nota = scanner.nextDouble();
                scanner.nextLine(); // limpiar buffer
                if (validarNota(nota)) {
                    break;
                } else {
                    System.out.println("La nota debe estar entre 0 y 100.");
                }
            } else {
                System.out.println("Entrada inválida. Debe ingresar un número.");
                scanner.nextLine(); // limpiar basura
            }
        }
        return nota;
    }
// Método para leer un entero con validación
    public static int leerEntero(Scanner scanner) {
        int numero;
        while (true) {
            if (scanner.hasNextInt()) {
                numero = scanner.nextInt();
                scanner.nextLine(); // limpiar buffer
                return numero;
            } else {
                System.out.println("Entrada inválida. Debe ser un número entero.");
                scanner.nextLine(); // limpiar entrada
                System.out.print("Ingrese nuevamente: ");
            }
        }
    }
// Método para limpiar los datos del estudiante
    public static void limpiarDatosEstudiante() {
        estudianteNombre = "N/A";
        nota1 = nota2 = nota3 = -1;
        estudianteRegistrado = false;
    }
}