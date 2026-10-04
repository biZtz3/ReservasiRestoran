package com.mycompany.reservasiretoran;

import java.util.Scanner;

public class ReservasiRetoran {
    static Reservasi[] daftar = new Reservasi[50];
    static int jumlahData = 0;

    static void tambah(Reservasi r) {
        if (jumlahData < daftar.length) {
            daftar[jumlahData] = r;
            jumlahData++;
        } else {
            System.out.println("Kapasitas penuh!");
        }
    }

    static void cariReservasi(String nama) {
        boolean ketemu = false;
        for (int i = 0; i < jumlahData; i++) {
            if (daftar[i].getNamaPelanggan().toLowerCase().contains(nama.toLowerCase())) {
                daftar[i].tampilkanInfo();
                ketemu = true;
            }
        }
        if (!ketemu) {
            System.out.println("Reservasi atas nama \"" + nama + "\" tidak ditemukan.");
        }
    }

    static void cariReservasi(int jam) {
        boolean ketemu = false;
        for (int i = 0; i < jumlahData; i++) {
            if (daftar[i].getJam() == jam) {
                daftar[i].tampilkanInfo();
                ketemu = true;
            }
        }
        if (!ketemu) {
            System.out.printf("Tidak ada reservasi pada jam %02d:00.%n", jam);
        }
    }

    static void tampilkanSemua() {
        if (jumlahData == 0) {
            System.out.println("Belum ada data reservasi.");
            return;
        }
        System.out.println("============ DAFTAR RESERVASI RESTORAN ============");
        for (int i = 0; i < jumlahData; i++) {
            daftar[i].tampilkanInfo(); // versi subclass yang berjalan (overriding)
            System.out.println("---------------------------------------------------");
        }
        System.out.println("Total reservasi dibuat: " + Reservasi.getTotalReservasi());
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Data awal (5 objek)
        tambah(new ReservasiMeja("Andi", 4, 12, 5, "Indoor"));
        tambah(new ReservasiMeja("Sinta", 2, 19, 12, "Outdoor"));
        tambah(new ReservasiAcara("Budi", 20, 18, "Ulang Tahun", "Paket B", 85000));
        tambah(new ReservasiAcara("Dewi", 12, 13, "Arisan", "Paket A", 65000));
        tambah(new ReservasiMeja("Rafi", 3, 19, 7, "Indoor"));

        int pilihan;
        do {
            System.out.println("\n===== SISTEM RESERVASI RESTORAN =====");
            System.out.println("1. Tambah Reservasi Baru");
            System.out.println("2. Tampilkan Seluruh Reservasi");
            System.out.println("3. Cari Reservasi");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.println("Jenis reservasi: 1. Meja  2. Acara");
                    System.out.print("Pilih jenis: ");
                    int jenis = input.nextInt();
                    input.nextLine();

                    if (jenis != 1 && jenis != 2) {
                        System.out.println("Jenis tidak valid!");
                        break;
                    }

                    System.out.print("Nama pelanggan : ");
                    String nama = input.nextLine();
                    System.out.print("Jumlah orang   : ");
                    int orang = input.nextInt();
                    System.out.print("Jam (10-22)    : ");
                    int jam = input.nextInt();
                    input.nextLine();

                    if (jenis == 1) {
                        System.out.print("Nomor meja (1-30): ");
                        int meja = input.nextInt();
                        input.nextLine();
                        System.out.print("Area (Indoor/Outdoor): ");
                        String area = input.nextLine();
                        tambah(new ReservasiMeja(nama, orang, jam, meja, area));
                    } else {
                        System.out.print("Jenis acara    : ");
                        String acara = input.nextLine();
                        System.out.print("Paket menu     : ");
                        String paket = input.nextLine();
                        System.out.print("Harga per orang: ");
                        double harga = input.nextDouble();
                        input.nextLine();
                        tambah(new ReservasiAcara(nama, orang, jam, acara, paket, harga));
                    }
                    System.out.println("Reservasi berhasil ditambahkan.");
                    break;

                case 2:
                    tampilkanSemua();
                    break;

                case 3:
                    System.out.println("Cari berdasarkan: 1. Nama  2. Jam");
                    System.out.print("Pilih: ");
                    int cari = input.nextInt();
                    input.nextLine();
                    if (cari == 1) {
                        System.out.print("Masukkan nama: ");
                        cariReservasi(input.nextLine());
                    } else if (cari == 2) {
                        System.out.print("Masukkan jam: ");
                        cariReservasi(input.nextInt());
                        input.nextLine();
                    } else {
                        System.out.println("Pilihan tidak valid!");
                    }
                    break;

                case 4:
                    System.out.println("Terima kasih! Program selesai.");
                    break;

                default:
                    System.out.println("Menu tidak tersedia!");
            }
        } while (pilihan != 4);

        input.close();
    }
}