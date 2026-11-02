package com.mycompany.reservasiretoran;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ReservasiRetoran {
    static ArrayList<Reservasi> daftar = new ArrayList<>();

    static void tambah(Reservasi r) {
        daftar.add(r);
    }

    static void cariReservasi(String nama) throws ReservasiTidakDitemukanException {
        boolean ketemu = false;
        for (int i = 0; i < daftar.size(); i++) {
            if (daftar.get(i).getNamaPelanggan().toLowerCase().contains(nama.toLowerCase())) {
                daftar.get(i).tampilkanInfo();
                ketemu = true;
            }
        }
        if (!ketemu) {
            throw new ReservasiTidakDitemukanException("Reservasi atas nama \"" + nama + "\" tidak ditemukan.");
        }
    }

    static void cariReservasi(int jam) throws ReservasiTidakDitemukanException {
        boolean ketemu = false;
        for (int i = 0; i < daftar.size(); i++) {
            if (daftar.get(i).getJam() == jam) {
                daftar.get(i).tampilkanInfo();
                ketemu = true;
            }
        }
        if (!ketemu) {
            throw new ReservasiTidakDitemukanException("Tidak ada reservasi pada jam " + jam + ":00.");
        }
    }

    static void validasiInput(String nama, int orang, int jam) throws Exception {
        if (nama.isEmpty()) {
            throw new Exception("Nama pelanggan tidak boleh kosong!");
        }
        if (orang < 1 || orang > 30) {
            throw new Exception("Jumlah orang harus 1 sampai 30!");
        }
        if (jam < 10 || jam > 22) {
            throw new Exception("Jam operasional hanya 10 sampai 22!");
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

    static void aksiPreOrder(Reservasi r) {
        if (r instanceof DapatPreOrder) {
            DapatPreOrder itemPreOrder = (DapatPreOrder) r;
            itemPreOrder.prosesPreOrder();
        } else {
            System.out.println("-> Reservasi " + r.getKodeReservasi() + " tidak mendukung pre-order.");
        }
    }

    static void aksiBatalkan(Reservasi r, String alasan) {
        if (r instanceof DapatDiBatalkan) {
            DapatDiBatalkan itemBisaBatal = (DapatDiBatalkan) r;
            itemBisaBatal.batalkanReservasi(alasan);
        } else {
            System.out.println("-> Reservasi " + r.getKodeReservasi() + " tidak bisa dibatalkan.");
        }
    }

    static void tampilkanSemua() {
        if (daftar.isEmpty()) {
            System.out.println("Belum ada data reservasi.");
            return;
        }
        System.out.println("============ DAFTAR RESERVASI RESTORAN ============");
        for (int i = 0; i < daftar.size(); i++) {
            daftar.get(i).tampilkanInfo();
            daftar.get(i).tampilkanKetentuan();
            System.out.println("---------------------------------------------------");
        }
        System.out.println("Total reservasi dibuat: " + Reservasi.getTotalReservasi());
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        HashMap<String, String> dataMember = new HashMap<>();
        dataMember.put("MBR001", "Andi");
        dataMember.put("MBR002", "Sinta");
        dataMember.put("MBR003", "Hendra");
        
        tambah(new ReservasiMeja("Andi", 4, 12, 5, "Indoor"));
        tambah(new ReservasiMeja("Sinta", 2, 19, 12, "Outdoor"));
        tambah(new ReservasiAcara("Budi", 20, 18, "Ulang Tahun", "Paket B", 85000));
        tambah(new ReservasiAcara("Dewi", 12, 13, "Arisan", "Paket A", 65000));
        tambah(new ReservasiMeja("Rafi", 3, 19, 7, "Indoor"));
        tambah(new ReservasiVIP("Hendra", 8, 19, "VIP Anggrek", 3));
        tambah(new ReservasiVIP("Maya", 6, 12, "VIP Melati", 2));


        int pilihan = 0;
        do {
            System.out.println("\n===== SISTEM RESERVASI RESTORAN =====");
            System.out.println("1. Tambah Reservasi Baru");
            System.out.println("2. Tampilkan Seluruh Reservasi");
            System.out.println("3. Cari Reservasi");
            System.out.println("4. Simulasi Proses Reservasi ");
            System.out.println("5. Aksi Khusus (Pre-Order / Pembatalan)");
            System.out.println("6. Kelola Member (HashMap)");
            System.out.println("7. Keluar");
            System.out.print("Pilih menu: ");

            try {
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

                        validasiInput(nama, orang, jam);

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
                        System.out.println("Terimakasih, reservasi berhasil! (Total: " + daftar.size() + ")");
                        break;

                    case 2:
                        tampilkanSemua();
                        break;

                    case 3:
                        System.out.println("Cari berdasarkan: 1. Nama  2. Jam");
                        System.out.print("Pilih: ");
                        int cari = input.nextInt();
                        input.nextLine();

                        try {
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
                        } catch (ReservasiTidakDitemukanException e) {
                            System.out.println("Peringatan: " + e.getMessage());
                        } finally {
                            System.out.println("-> Sesi pencarian selesai dieksekusi.");
                        }
                        break;

                    case 4:
                        if (daftar.isEmpty()) {
                            System.out.println("Belum ada data reservasi.");
                            break;
                        }
                        System.out.println("Simulasi: 1. Proses satu reservasi  2. Proses semua reservasi");
                        System.out.print("Pilih: ");
                        int mode = input.nextInt();
                        input.nextLine();
                        if (mode == 1) {
                            System.out.print("Nomor urut reservasi (1-" + daftar.size() + "): ");
                            int nomor = input.nextInt();
                            System.out.print("Diskon persen (0 jika tanpa diskon): ");
                            double diskon = input.nextDouble();
                            input.nextLine();
                            if (nomor < 1 || nomor > daftar.size()) {
                                System.out.println("Nomor tidak valid!");
                            } else if (diskon > 0) {
                                prosesReservasi(daftar.get(nomor - 1), diskon); 
                            } else {
                                prosesReservasi(daftar.get(nomor - 1));        
                            }
                        } else if (mode == 2) {
                            for (int i = 0; i < daftar.size(); i++) {
                                prosesReservasi(daftar.get(i));
                            }
                        } else {
                            System.out.println("Pilihan tidak valid!");
                        }
                        break;

                    case 5:
                        if (daftar.isEmpty()) {
                            System.out.println("Belum ada data reservasi.");
                            break;
                        }
                        System.out.println("Aksi khusus: 1. Pre-order menu  2. Batalkan reservasi  3. Coba semua aksi ke seluruh reservasi");
                        System.out.print("Pilih: ");
                        int aksi = input.nextInt();
                        input.nextLine();
                        if (aksi == 1 || aksi == 2) {
                            System.out.print("Nomor urut reservasi (1-" + daftar.size() + "): ");
                            int urut = input.nextInt();
                            input.nextLine();
                            if (urut < 1 || urut > daftar.size()) {
                                System.out.println("Nomor tidak valid!");
                            } else {
                                daftar.get(urut - 1).tampilkanInfo();
                                if (aksi == 1) {
                                    aksiPreOrder(daftar.get(urut - 1));
                                } else {
                                    System.out.print("Alasan pembatalan: ");
                                    String alasan = input.nextLine();
                                    aksiBatalkan(daftar.get(urut - 1), alasan);
                                }
                            }
                        } else if (aksi == 3) {
                            System.out.println("=========== SIMULASI AKSI KHUSUS ===========");
                            for (int i = 0; i < daftar.size(); i++) {
                                daftar.get(i).tampilkanInfo();
                                aksiPreOrder(daftar.get(i));
                                aksiBatalkan(daftar.get(i), "Pelanggan berhalangan hadir");
                                System.out.println("---------------------------------------------");
                            }
                        } else {
                            System.out.println("Pilihan tidak valid!");
                        }
                        break;

                    case 6:
                        System.out.println("\n-- Kelola Member (HashMap) --");
                        System.out.println("1. Tambah Member Baru");
                        System.out.println("2. Cari Member via ID");
                        System.out.println("3. Tampilkan Semua Member");
                        System.out.print("Pilih: ");
                        int menuMember = input.nextInt();
                        input.nextLine();

                        if (menuMember == 1) {
                            System.out.print("Masukkan ID Member (sebagai Key): ");
                            String idMember = input.nextLine();
                            System.out.print("Masukkan Nama Member (sebagai Value): ");
                            String namaMember = input.nextLine();

                            if (idMember.isEmpty() || namaMember.isEmpty()) {
                                throw new Exception("ID dan nama member tidak boleh kosong!");
                            }
                            if (dataMember.containsKey(idMember)) {
                                System.out.println("ID " + idMember + " sudah terdaftar atas nama " + dataMember.get(idMember) + ".");
                            } else {
                                dataMember.put(idMember, namaMember);
                                System.out.println("Berhasil menambahkan member!");
                            }
                        } else if (menuMember == 2) {
                            System.out.print("Masukkan ID Member yang dicari: ");
                            String cariId = input.nextLine();

                            if (dataMember.containsKey(cariId)) {
                                System.out.println("Ditemukan! Nama: " + dataMember.get(cariId));
                            } else {
                                System.out.println("Member dengan ID tersebut tidak terdaftar.");
                            }
                        } else if (menuMember == 3) {
                            System.out.println("Total Member Terdaftar: " + dataMember.size());
                            for (String keyId : dataMember.keySet()) {
                                System.out.println("- ID: " + keyId + " | Nama: " + dataMember.get(keyId));
                            }
                        } else {
                            System.out.println("Pilihan tidak valid!");
                        }
                        break;
                    
                    case 7:
                        System.out.println("Terima kasih! Program selesai.");
                        break;
     
                    default:
                        System.out.println("Menu tidak tersedia!");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n[ERROR INPUT] Input tidak valid! Anda harus memasukkan angka.");
                input.nextLine();
            } catch (Exception e) {
                System.out.println("\n[ERROR SISTEM] Terjadi kegagalan: " + e.getMessage());
            }
        } while (pilihan != 7);
 
        input.close();
    }
}