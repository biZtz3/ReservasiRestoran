package com.mycompany.reservasiretoran;

public class Reservasi {
    private String kodeReservasi;
    private String namaPelanggan;
    private int jumlahOrang;
    private int jam;

    private static int totalReservasi = 0;

    public Reservasi(String namaPelanggan, int jumlahOrang, int jam) {
        totalReservasi++;
        this.kodeReservasi = String.format("RSV-%03d", totalReservasi);
        setNamaPelanggan(namaPelanggan);
        setJumlahOrang(jumlahOrang);
        setJam(jam);
    }

    public String getKodeReservasi() { return kodeReservasi; }
    public String getNamaPelanggan() { return namaPelanggan; }
    public int getJumlahOrang() { return jumlahOrang; }
    public int getJam() { return jam; }
    public static int getTotalReservasi() { return totalReservasi; }

    public void setNamaPelanggan(String namaPelanggan) {
        if (namaPelanggan == null || namaPelanggan.trim().isEmpty()) {
            this.namaPelanggan = "Tanpa Nama";
        } else {
            this.namaPelanggan = namaPelanggan.trim();
        }
    }

    public void setJumlahOrang(int jumlahOrang) {
        if (jumlahOrang < 1) {
            this.jumlahOrang = 1;
        } else if (jumlahOrang > 30) {
            this.jumlahOrang = 30;
        } else {
            this.jumlahOrang = jumlahOrang;
        }
    }

    public void setJam(int jam) {
        if (jam < 10 || jam > 22) {
            this.jam = 10;
        } else {
            this.jam = jam;
        }
    }

    public double hitungBiaya() {
        return 0;
    }

    public double hitungBiaya(double diskonPersen) {
        double biaya = hitungBiaya();
        return biaya - (biaya * diskonPersen / 100);
    }

    public void tampilkanInfo() {
        System.out.printf("%-9s | %-15s | %2d orang | Jam %02d:00%n",
                kodeReservasi, namaPelanggan, jumlahOrang, jam);
    }
}

