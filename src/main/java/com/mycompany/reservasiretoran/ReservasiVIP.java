package com.mycompany.reservasiretoran;

public class ReservasiVIP extends Reservasi {
    private String namaRuangan;
    private int durasiJam; // lama pemakaian ruangan (1-6 jam)

    public ReservasiVIP(String namaPelanggan, int jumlahOrang, int jam,String namaRuangan, int durasiJam) {
        super(namaPelanggan, jumlahOrang, jam);
        setNamaRuangan(namaRuangan);
        setDurasiJam(durasiJam);
    }

    public String getNamaRuangan() { return namaRuangan; }
    public int getDurasiJam() { return durasiJam; }

    public void setNamaRuangan(String namaRuangan) {
        if (namaRuangan == null || namaRuangan.length() == 0) {
            this.namaRuangan = "VIP Melati";
        } else {
            this.namaRuangan = namaRuangan;
        }
    }

    public void setDurasiJam(int durasiJam) {
        if (durasiJam < 1) {
            this.durasiJam = 1;
        } else if (durasiJam > 6) {
            this.durasiJam = 6;
        } else {
            this.durasiJam = durasiJam;
        }
    }

    // OVERRIDING: biaya sewa ruangan Rp150.000 per jam
    @Override
    public double hitungBiaya() {
        return durasiJam * 150000;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.printf("-> VIP   | %s | %d jam | Sewa Rp%.0f%n",
                namaRuangan, durasiJam, hitungBiaya());
    }
}
