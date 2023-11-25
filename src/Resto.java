
import admin.AdminToko;
import menu.Menu;
import pelanggan.Pelanggan;

import java.util.Scanner;

public class Resto {
    private Menu menu;
    private Pelanggan pelanggan;
    private AdminToko admin;

    public Resto(Menu menuRestoran) {
        this.menu = menuRestoran;
        this.pelanggan = new Pelanggan(menuRestoran);
        this.admin = new AdminToko(menuRestoran);
    }

    public void mulaiAplikasi() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Siapakah Anda?:");
        System.out.println("1. Pelanggan");
        System.out.println("2. AdminToko");

        //handle if input string or other
        while (!scanner.hasNextInt()) {
            System.out.println("Pilihan tidak valid");
            scanner.next();
        }

        int pilihanUser = scanner.nextInt();
        if (pilihanUser == 1) {
            System.out.println("Anda login sebagai Pelanggan");
//            menu.tampilkanSemuaMenu();
            menu.tampilMenu();
            pelanggan.terimaPesanan();
            pelanggan.tampilkanStruk();
        } else if (pilihanUser == 2) {
            System.out.println("Anda login sebagai Admin");
            admin.menuAdmin();
            mulaiAplikasi();
        }
        else {
            System.out.println("Pilihan tidak valid");
            mulaiAplikasi();
        }

        scanner.close();
    }
}