import menu.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menuRestoran = new Menu();
        Resto restoran = new Resto(menuRestoran);

        // Menjalankan aplikasi
        restoran.mulaiAplikasi();
    }
}
