package com.mycompany.reservasiretoran;

public class ReservasiAcara extends Reservasi {
    private String jenisAcara;
    private String paketMenu;
    private double hargaPerOrang;

    public ReservasiAcara(String namaPelanggan, int jumlahOrang, int jam, String jenisAcara, String paketMenu, double hargaPerOrang) {
        super(namaPelanggan, jumlahOrang, jam);
        setJenisAcara(jenisAcara);
        setPaketMenu(paketMenu);
        setHargaPerOrang(hargaPerOrang);
    }

    public String getJenisAcara() { return jenisAcara; }
    public String getPaketMenu() { return paketMenu; }
    public double getHargaPerOrang() { return hargaPerOrang; }

    public void setJenisAcara(String jenisAcara) {
        if (jenisAcara == null || jenisAcara.trim().isEmpty()) {
            this.jenisAcara = "Acara Umum";
        } else {
            this.jenisAcara = jenisAcara.trim();
        }
    }

    public void setPaketMenu(String paketMenu) {
        if (paketMenu == null || paketMenu.trim().isEmpty()) {
            this.paketMenu = "Paket A";
        } else {
            this.paketMenu = paketMenu.trim();
        }
    }

    public void setHargaPerOrang(double hargaPerOrang) {
        if (hargaPerOrang < 50000) {
            this.hargaPerOrang = 50000; // harga minimum paket acara
        } else {
            this.hargaPerOrang = hargaPerOrang;
        }
    }

    @Override
    public double hitungBiaya() {
        return getJumlahOrang() * hargaPerOrang * 0.30;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.printf("          -> Acara | %s | %s @Rp%,.0f | DP 30%% Rp%,.0f%n",
                jenisAcara, paketMenu, hargaPerOrang, hitungBiaya());
    }
}