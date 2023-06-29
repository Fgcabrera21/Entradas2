import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Concert {
    private String name;
    private List<Section> sections;

    public Concert(String name) {
        this.name = name;
        this.sections = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addSection(Section section) {
        sections.add(section);
    }

    public List<Section> getSections() {
        return sections;
    }
}

class Section {
    private String name;
    private int numRows;
    private int numSeatsPerRow;
    private double price;

    private boolean[][] seats;

    public Section(String name, int numRows, int numSeatsPerRow, double price) {
        this.name = name;
        this.numRows = numRows;
        this.numSeatsPerRow = numSeatsPerRow;
        this.price = price;
        this.seats = new boolean[numRows][numSeatsPerRow];
    }

    public String getName() {
        return name;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumSeatsPerRow() {
        return numSeatsPerRow;
    }

    public double getPrice() {
        return price;
    }

    public boolean isSeatAvailable(int row, int seat) {
        return !seats[row][seat];
    }

    public void bookSeat(int row, int seat) {
        seats[row][seat] = true;
    }

    public void cancelSeat(int row, int seat) {
        seats[row][seat] = false;
    }

    public int getAvailableSeatsCount() {
        int count = 0;
        for (int row = 0; row < numRows; row++) {
            for (int seat = 0; seat < numSeatsPerRow; seat++) {
                if (!seats[row][seat]) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getBookedSeatsCount() {
        return (numRows * numSeatsPerRow) - getAvailableSeatsCount();
    }

    public double getRevenue() {
        return getBookedSeatsCount() * price;
    }

    public void setNumRows(int newNumRows) {
    }

    public void setName(String newSectionName) {
    }

    public void setNumSeatsPerRow(int newNumSeatsPerRow) {
    }

    public void setPrice(double newPrice) {
    }
}

class ConcertApp {
    private List<Concert> concerts;
    private Scanner scanner;

    public ConcertApp() {
        this.concerts = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        createPreloadedConcerts();
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            displayMainMenu();
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (option) {
                case 1:
                    createConcert();
                    break;
                case 2:
                    editConcert();
                    break;
                case 3:
                    deleteConcert();
                    break;
                case 4:
                    viewConcerts();
                    break;
                case 5:
                    login();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida. Inténtalo de nuevo.");
                    break;
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("=== Menú Principal ===");
        System.out.println("1. Agregar concierto");
        System.out.println("2. Editar concierto");
        System.out.println("3. Eliminar concierto");
        System.out.println("4. Ver conciertos disponibles");
        System.out.println("5. Iniciar sesión");
        System.out.println("6. Salir");
        System.out.print("Elige una opción: ");
    }

    private void createPreloadedConcerts() {
        Concert concert1 = new Concert("Concierto 1");
        Concert concert2 = new Concert("Concierto 2");
        Concert concert3 = new Concert("Concierto 3");

        Section plateaIzquierda = new Section("Platea Izquierda", 3, 3, 50.0);
        Section campo = new Section("Campo", 4, 4, 30.0);
        Section plateaDerecha = new Section("Platea Derecha", 3, 3, 50.0);
        Section palcoVIP = new Section("Palco VIP", 4, 4, 100.0);

        concert1.addSection(plateaIzquierda);
        concert1.addSection(campo);
        concert1.addSection(plateaDerecha);
        concert1.addSection(palcoVIP);

        concert2.addSection(plateaIzquierda);
        concert2.addSection(campo);
        concert2.addSection(plateaDerecha);
        concert2.addSection(palcoVIP);

        concert3.addSection(plateaIzquierda);
        concert3.addSection(campo);
        concert3.addSection(plateaDerecha);
        concert3.addSection(palcoVIP);

        concerts.add(concert1);
        concerts.add(concert2);
        concerts.add(concert3);
    }

    private void createConcert() {
        System.out.print("Ingrese el nombre del concierto: ");
        String concertName = scanner.nextLine();
        Concert concert = new Concert(concertName);

        Section plateaIzquierda = new Section("Platea Izquierda", 3, 3, 50.0);
        Section campo = new Section("Campo", 4, 4, 30.0);
        Section plateaDerecha = new Section("Platea Derecha", 3, 3, 50.0);
        Section palcoVIP = new Section("Palco VIP", 4, 4, 100.0);

        concert.addSection(plateaIzquierda);
        concert.addSection(campo);
        concert.addSection(plateaDerecha);
        concert.addSection(palcoVIP);

        concerts.add(concert);
        System.out.println("Concierto agregado exitosamente.");
    }

    private void editConcert() {
        System.out.println("=== Conciertos Disponibles ===");
        for (int i = 0; i < concerts.size(); i++) {
            System.out.println(i + 1 + ". " + concerts.get(i).getName());
        }

        System.out.print("Seleccione el número de concierto a editar: ");
        int selectedConcertIndex = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (selectedConcertIndex <= 0 || selectedConcertIndex > concerts.size()) {
            System.out.println("Número de concierto inválido.");
            return;
        }

        Concert concert = concerts.get(selectedConcertIndex - 1);

        System.out.println("=== Secciones del concierto ===");
        int sectionIndex = 1;
        for (Section section : concert.getSections()) {
            System.out.println(sectionIndex + ". " + section.getName());
            sectionIndex++;
        }

        System.out.print("Seleccione el número de sección a editar: ");
        int selectedSectionIndex = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (selectedSectionIndex <= 0 || selectedSectionIndex > concert.getSections().size()) {
            System.out.println("Número de sección inválido.");
            return;
        }

        Section section = concert.getSections().get(selectedSectionIndex - 1);

        System.out.print("Ingrese el nuevo nombre de la sección: ");
        String newSectionName = scanner.nextLine();
        section.setName(newSectionName);

        System.out.print("Ingrese el nuevo número de filas: ");
        int newNumRows = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        section.setNumRows(newNumRows);

        System.out.print("Ingrese el nuevo número de asientos por fila: ");
        int newNumSeatsPerRow = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        section.setNumSeatsPerRow(newNumSeatsPerRow);

        System.out.print("Ingrese el nuevo precio: ");
        double newPrice = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character
        section.setPrice(newPrice);

        System.out.println("Sección editada exitosamente.");
    }

    private void deleteConcert() {
        System.out.println("=== Conciertos Disponibles ===");
        for (int i = 0; i < concerts.size(); i++) {
            System.out.println(i + 1 + ". " + concerts.get(i).getName());
        }

        System.out.print("Seleccione el número de concierto a eliminar: ");
        int selectedConcertIndex = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (selectedConcertIndex <= 0 || selectedConcertIndex > concerts.size()) {
            System.out.println("Número de concierto inválido.");
            return;
        }

        concerts.remove(selectedConcertIndex - 1);
        System.out.println("Concierto eliminado exitosamente.");
    }

    private void viewConcerts() {
        System.out.println("=== Conciertos Disponibles ===");
        for (int i = 0; i < concerts.size(); i++) {
            System.out.println(i + 1 + ". " + concerts.get(i).getName());
        }
    }

    private void login() {
        System.out.print("Ingrese su nombre de usuario: ");
        String username = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        if (username.equals("admin") && password.equals("admin123")) {
            // Usuario es un administrador
            displayAdminMenu();
        } else {
            // Usuario es un cliente
            displayClientMenu();
        }
    }

    private void displayAdminMenu() {
        System.out.println("=== Menú de Administrador ===");
        System.out.println("1. Comprar entrada");
        System.out.println("2. Asientos disponibles en cada concierto");
        System.out.println("3. Conciertos disponibles");
        System.out.println("4. Generar estadísticas de ventas");
        System.out.println("5. Cerrar sesión");
        System.out.print("Elige una opción: ");

        int option = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (option) {
            case 1:
                buyTicket();
                break;
            case 2:
                displayAvailableSeats();
                break;
            case 3:
                viewConcerts();
                break;
            case 4:
                generateSalesStatistics();
                break;
            case 5:
                System.out.println("Cerrando sesión de administrador...");
                break;
            default:
                System.out.println("Opción inválida. Inténtalo de nuevo.");
                break;
        }
    }

    private void displayClientMenu() {
        System.out.println("=== Menú de Cliente ===");
        System.out.println("1. Comprar entrada");
        System.out.println("2. Asientos disponibles en cada concierto");
        System.out.println("3. Conciertos disponibles");
        System.out.println("4. Cerrar sesión");
        System.out.print("Elige una opción: ");

        int option = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (option) {
            case 1:
                buyTicket();
                break;
            case 2:
                displayAvailableSeats();
                break;
            case 3:
                viewConcerts();
                break;
            case 4:
                System.out.println("Cerrando sesión de cliente...");
                break;
            default:
                System.out.println("Opción inválida. Inténtalo de nuevo.");
                break;
        }
    }

    private void buyTicket() {
        System.out.print("Seleccione el número de concierto: ");
        int selectedConcertIndex = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (selectedConcertIndex <= 0 || selectedConcertIndex > concerts.size()) {
            System.out.println("Número de concierto inválido.");
            return;
        }

        Concert concert = concerts.get(selectedConcertIndex - 1);

        System.out.println("=== Secciones del concierto ===");
        int sectionIndex = 1;
        for (Section section : concert.getSections()) {
            System.out.println(sectionIndex + ". " + section.getName());
            sectionIndex++;
        }

        System.out.print("Seleccione el número de sección: ");
        int selectedSectionIndex = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (selectedSectionIndex <= 0 || selectedSectionIndex > concert.getSections().size()) {
            System.out.println("Número de sección inválido.");
            return;
        }

        Section selectedSection = concert.getSections().get(selectedSectionIndex - 1);

        System.out.println("=== Asientos disponibles ===");
        for (int row = 0; row < selectedSection.getNumRows(); row++) {
            for (int seat = 0; seat < selectedSection.getNumSeatsPerRow(); seat++) {
                if (selectedSection.isSeatAvailable(row, seat)) {
                    System.out.println("Fila " + (row + 1) + ", Asiento " + (seat + 1));
                }
            }
        }

        System.out.print("Ingrese el número de fila: ");
        int selectedRow = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Ingrese el número de asiento: ");
        int selectedSeat = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (selectedRow <= 0 || selectedRow > selectedSection.getNumRows() ||
                selectedSeat <= 0 || selectedSeat > selectedSection.getNumSeatsPerRow()) {
            System.out.println("Número de fila o asiento inválido.");
            return;
        }

        if (selectedSection.isSeatAvailable(selectedRow - 1, selectedSeat - 1)) {
            selectedSection.bookSeat(selectedRow - 1, selectedSeat - 1);
            System.out.println("¡Entrada comprada exitosamente!");
        } else {
            System.out.println("El asiento seleccionado no está disponible.");
        }
    }

    private void displayAvailableSeats() {
        System.out.print("Ingrese el número de concierto: ");
        int selectedConcertIndex = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (selectedConcertIndex <= 0 || selectedConcertIndex > concerts.size()) {
            System.out.println("Número de concierto inválido.");
            return;
        }

        Concert concert = concerts.get(selectedConcertIndex - 1);

        System.out.println("=== Asientos disponibles en cada sección ===");
        for (Section section : concert.getSections()) {
            System.out.println("Sección: " + section.getName());
            System.out.println("Asientos disponibles: " + section.getAvailableSeatsCount());
        }
    }

    private void generateSalesStatistics() {
        System.out.print("Ingrese el número de concierto: ");
        int selectedConcertIndex = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (selectedConcertIndex <= 0 || selectedConcertIndex > concerts.size()) {
            System.out.println("Número de concierto inválido.");
            return;
        }

        Concert concert = concerts.get(selectedConcertIndex - 1);

        System.out.println("=== Estadísticas de ventas ===");
        System.out.println("Concierto: " + concert.getName());
        for (Section section : concert.getSections()) {
            System.out.println("Sección: " + section.getName());
            System.out.println("Asientos vendidos: " + section.getBookedSeatsCount());
            System.out.println("Ingresos generados: " + section.getRevenue());
            System.out.println("-----------------------------");
        }
    }
}

public class Principal {
    public static void main(String[] args) {
        ConcertApp concertApp = new ConcertApp();
        concertApp.run();
    }
}
