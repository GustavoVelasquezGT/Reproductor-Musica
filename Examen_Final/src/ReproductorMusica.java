import java.util.Scanner;

public class ReproductorMusica {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Playlist playlist = new Playlist();
        int opcion;
        
        do {
            System.out.println("\n--- REPRODUCTOR DE MÚSICA ---");
            System.out.println("1. Crear nueva playlist");
            System.out.println("2. Agregar canción");
            System.out.println("3. Mostrar canciones");
            System.out.println("4. Buscar canción por título");
            System.out.println("5. Eliminar canción por título");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        playlist = new Playlist();
                        System.out.println("Nueva playlist creada.");
                        break;
                    case 2:
                        System.out.print("Ingrese el título: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Ingrese el artista: ");
                        String artista = scanner.nextLine();
                        System.out.print("Ingrese duración en segundos: ");
                        int duracion = Integer.parseInt(scanner.nextLine());
                        playlist.agregarCancion(titulo, artista, duracion);
                        break;
                    case 3:
                        playlist.mostrarCanciones();
                        break;
                    case 4:
                        System.out.print("Ingrese el título a buscar: ");
                        playlist.buscarCancion(scanner.nextLine());
                        break;
                    case 5:
                        System.out.print("Ingrese el título a eliminar: ");
                        playlist.eliminarCancion(scanner.nextLine());
                        break;
                    case 6:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Intente de nuevo.");
            }
        } while (opcion != 6);

        scanner.close();
    }
}

class Cancion {
    String titulo;
    String artista;
    int duracion;
    Cancion siguiente;

    public Cancion(String titulo, String artista, int duracion) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracion = duracion;
        this.siguiente = null;
    }
}

class Playlist {
    private Cancion cabeza;

    public void agregarCancion(String titulo, String artista, int duracion) {
        if (titulo == null || titulo.trim().isEmpty() || duracion <= 0) {
            System.out.println("Datos inválidos. No se agregó la canción.");
            return;
        }

        Cancion nueva = new Cancion(titulo.trim(), artista.trim(), duracion);
        if (cabeza == null) {
            cabeza = nueva;
        } else {
            Cancion temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nueva;
        }
        System.out.println("Canción agregada exitosamente.");
    }

    public void mostrarCanciones() {
        if (cabeza == null) {
            System.out.println("La playlist está vacía.");
            return;
        }
        Cancion temp = cabeza;
        System.out.println("\n--- Lista de canciones ---");
        while (temp != null) {
            System.out.println("Título: " + temp.titulo + " | Artista: " + temp.artista + " | Duración: " + temp.duracion + "s");
            temp = temp.siguiente;
        }
    }

    public void buscarCancion(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            System.out.println("Título inválido.");
            return;
        }

        Cancion temp = cabeza;
        while (temp != null) {
            if (temp.titulo.equalsIgnoreCase(titulo.trim())) {
                System.out.println("Canción encontrada:");
                System.out.println("Título: " + temp.titulo + " | Artista: " + temp.artista + " | Duración: " + temp.duracion + "s");
                return;
            }
            temp = temp.siguiente;
        }
        System.out.println("Canción no encontrada.");
    }

    public void eliminarCancion(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            System.out.println("Título inválido.");
            return;
        }

        if (cabeza == null) {
            System.out.println("La playlist está vacía.");
            return;
        }

        if (cabeza.titulo.equalsIgnoreCase(titulo.trim())) {
            cabeza = cabeza.siguiente;
            System.out.println("Canción eliminada.");
            return;
        }

        Cancion temp = cabeza;
        while (temp.siguiente != null && !temp.siguiente.titulo.equalsIgnoreCase(titulo.trim())) {
            temp = temp.siguiente;
        }

        if (temp.siguiente != null) {
            temp.siguiente = temp.siguiente.siguiente;
            System.out.println("Canción eliminada.");
        } else {
            System.out.println("Canción no encontrada.");
        }
    }
}
