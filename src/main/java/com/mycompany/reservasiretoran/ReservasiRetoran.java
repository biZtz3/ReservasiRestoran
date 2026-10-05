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
    
    static void prosesReservasi(Reservasi r) {
        System.out.println("------------ PROSES RESERVASI ------------");
        r.tampilkanInfo();
        System.out.printf("Biaya yang harus dibayar : Rp%.0f%n", r.hitungBiaya());
        System.out.println("------------------------------------------");
    }
 
    static void prosesReservasi(Reservasi r, double diskonPersen) {
        System.out.println("------------ PROSES RESERVASI ------------");
        r.tampilkanInfo();
        System.out.printf("Biaya sebelum diskon     : Rp%.0f%n", r.hitungBiaya());
        System.out.printf("Diskon                   : %.0f persen%n", diskonPersen);
        System.out.printf("Biaya setelah diskon     : Rp%.0f%n", r.hitungBiaya(diskonPersen));
        System.out.println("------------------------------------------");
    }

    static void tampilkanSemua() {
        if (jumlahData == 0) {
            System.out.println("Belum ada data reservasi.");
            return;
        }
        System.out.println("============ DAFTAR RESERVASI RESTORAN ============");
        for (int i = 0; i < jumlahData; i++) {
            daftar[i].tampilkanInfo();
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
        tambah(new ReservasiVIP("Hendra", 8, 19, "VIP Anggrek", 3));
        tambah(new ReservasiVIP("Maya", 6, 12, "VIP Melati", 2));


        int pilihan;
        do {
            System.out.println("\n===== SISTEM RESERVASI RESTORAN =====");
            System.out.println("1. Tambah Reservasi Baru");
            System.out.println("2. Tampilkan Seluruh Reservasi");
            System.out.println("3. Cari Reservasi");
            System.out.println("4. Simulasi Proses Reservasi ");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.println("Jenis reservasi: 1. Meja  2. Acara  3.VIP");
                    System.out.print("Pilih jenis: ");
                    int jenis = input.nextInt();
                    input.nextLine();

                    if (jenis < 1 || jenis > 3) {
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
                    } else if (jenis ==2){
                        System.out.print("Jenis acara    : ");
                        String acara = input.nextLine();
                        System.out.print("Paket menu     : ");
                        String paket = input.nextLine();
                        System.out.print("Harga per orang: ");
                        double harga = input.nextDouble();
                        input.nextLine();
                        tambah(new ReservasiAcara(nama, orang, jam, acara, paket, harga));
                    } else {
                        System.out.print("Nama ruangan VIP : ");
                        String ruangan = input.nextLine();
                        System.out.print("Durasi (1-6 jam) : ");
                        int durasi = input.nextInt();
                        input.nextLine();
                        tambah(new ReservasiVIP(nama, orang, jam, ruangan, durasi));
                    }
                    System.out.println("Terimakasih, reservasi berhasil!");
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
                    if (jumlahData == 0) {
                        System.out.println("Belum ada data reservasi.");
                        break;
                    }
                    System.out.println("Simulasi: 1. Proses satu reservasi  2. Proses semua reservasi");
                    System.out.print("Pilih: ");
                    int mode = input.nextInt();
                    input.nextLine();
                    if (mode == 1) {
                        System.out.print("Nomor urut reservasi (1-" + jumlahData + "): ");
                        int nomor = input.nextInt();
                        System.out.print("Diskon persen (0 jika tanpa diskon): ");
                        double diskon = input.nextDouble();
                        input.nextLine();
                        if (nomor < 1 || nomor > jumlahData) {
                            System.out.println("Nomor tidak valid!");
                        } else if (diskon > 0) {
                            prosesReservasi(daftar[nomor - 1], diskon); 
                        } else {
                            prosesReservasi(daftar[nomor - 1]);        
                        }
                    } else if (mode == 2) {
                        for (int i = 0; i < jumlahData; i++) {
                            prosesReservasi(daftar[i]);
                        }
                    } else {
                        System.out.println("Pilihan tidak valid!");
                    }
                    break;
                
                case 5:
                    System.out.println("Terima kasih! Program selesai.");
                    break;
 
                default:
                    System.out.println("Menu tidak tersedia!");
            }
        } while (pilihan != 5);
 
        input.close();
    }
}