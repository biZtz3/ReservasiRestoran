package com.mycompany.reservasiretoran;

//implements satu interface (DapatPreOrder)
public class ReservasiAcara extends Reservasi implements DapatPreOrder{ //acara bisa ulang tahun, arisan, dll
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
        if (jenisAcara == null || jenisAcara.length() == 0) {
            this.jenisAcara = "Acara Umum";
        } else {
            this.jenisAcara = jenisAcara;
        }
    }
  
     public void setPaketMenu(String paketMenu) {
        if (paketMenu == null || paketMenu.length() == 0) {
            this.paketMenu = "Paket A";
        } else {
            this.paketMenu = paketMenu;
        }
    }
        
    public void setHargaPerOrang(double hargaPerOrang) {
        if (hargaPerOrang < 50000) {
            this.hargaPerOrang = 50000;
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
        System.out.printf("-> Acara | %s | %s @Rp%,.0f | DP 30%% Rp%,.0f%n",
                jenisAcara, paketMenu, hargaPerOrang, hitungBiaya());
    }
    
    //override abstract method dari superclass
    @Override
    public void tampilkanKetentuan(){
        System.out.println("-> Ketentuan Acara: DP 30% dibayar di muka, sisanya dilunasi di hari acara.");
    }
    
    //override method dari interface DapatPreOrder
    @Override
    public void prosesPreOrder(){
        System.out.println("-> [PRE-ORDER] \" + paketMenu + \" untuk acara \" + jenisAcara + \" disiapkan dapur sebelum acara.");
    }   
}