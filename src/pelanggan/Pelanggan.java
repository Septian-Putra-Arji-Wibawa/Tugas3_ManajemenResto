package pelanggan;

import menu.Menu;
import menu.MenuItem;
import transaction.Order;

import java.util.ArrayList;
import java.util.Scanner;

public class Pelanggan {
    private ArrayList<Order> daftarPesanan;
    private Menu menu;
    Order order = new Order("");

    public Pelanggan(Menu menu) {
        this.daftarPesanan = new ArrayList<>();
        this.menu = menu;
    }

    public void terimaPesanan() {

        double totalHargaPesanan = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Masukkan pesanan, Gunakan format 'Nama Menu = Jumlah' dan ketik 't' untuk menyelesaikan order :");

        while (true) {
            String pesananInput = scanner.nextLine().trim();

            if (pesananInput.equalsIgnoreCase("t")) {
                if (order.isEmpty()) {
                    System.out.println("Pesanan masih kosong. Gunakan format 'Nama Menu = Jumlah'");
                    continue;
                } else {
                    break; // Keluar dari loop dengan memasukkan 't'
                }
            }

            // Memisahkan nama menu & jumlah dengan "=" sebagai pemisah
            String[] pesananSplit = pesananInput.split("=");

            if (pesananSplit.length != 2) {
                System.out.println("Format pesanan tidak sesuai, Gunakan format 'Nama Menu = Jumlah' dan ketik 't' untuk selesai.");
                continue;
            }

            String namaMenu = pesananSplit[0].trim();
            int jumlah;
            try {
                jumlah = Integer.parseInt(pesananSplit[1].trim());
                if (jumlah < 0) {
                    throw new IllegalArgumentException("Jumlah pesanan tidak boleh negatif.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Jumlah pesanan harus berupa angka.");
                continue;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            // Cari menu dengan nama menu
            MenuItem menuItem = menu.cariMenu(namaMenu);

            if (menuItem != null) {
                // masukkan pesanan ke array class pesanan
                order.tambahItemPesanan(menuItem.getNama(), jumlah, menuItem.getHarga());
            } else {
                System.out.println("Menu '" + namaMenu + "' tidak ditemukan.");
            }

        }

        totalHargaPesanan =  order.hitungTotalHargaPesanan();
//         Apply an offer if the totalHargaPesanan is more than 50,000
        if (totalHargaPesanan >= 50000) {
            System.out.println("Selamat! Anda mendapatkan penawaran khusus by 1 get 1 Minuman. ");

            // Tampilkan menu kategori minuman
            System.out.println("Daftar Menu Minuman:");
            menu.tampilkanMenuMinuman();
            int y = 0;
            System.out.println("Masukkan nama minuman yang ingin Anda tambahkan (maksimal 1 menu) dan ketik 't' untuk selesai :");

            while (y < 1) {
                String minumamInput = scanner.nextLine();
                MenuItem menuItem = menu.cariMenu(minumamInput);

                if (minumamInput.equalsIgnoreCase("t")) {
                    break;
                } else if (menuItem != null) {
                    order.tambahItemPesanan(menuItem.getNama(), 1, menuItem.getHarga());
                    order.tambahItemPesanan(menuItem.getNama(), 1, 0);
                    y++;
                } else {
                    System.out.println("Menu '" + minumamInput + "' tidak ditemukan.");
                }

            }
        }
    }

    public void tampilkanStruk() {
        order.tampilkanStruk();
    }
}