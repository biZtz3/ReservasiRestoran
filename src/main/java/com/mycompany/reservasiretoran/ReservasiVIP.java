package com.mycompany.reservasiretoran;

//multiple implements (2 interface sekaligus)
public class ReservasiVIP extends Reservasi implements DapatPreOrder, DapatDibatalkan{
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

    //overriding biaya sewa ruangan Rp150.000 per jam
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
    
    //override abstract method dari superclass
    @Override
    public void tampilkanKetentuan(){
        System.out.println("-> Ketentuan VIP: ruangan dipakai maksimal 6 jam, sewa Rp150.000 per jam.");
    }
    
    //override method dari interface DapatPreOrder
    public void prosesPreOrder(){
        System.out.println("-> [PRE-ORDER] Menu dipesan lebih dulu dan disajikan di " + namaRuangan + ".");
    }
    
    //override method dari interface DapatDibatalkan
    @Override
    public void batalkanReservasi(String alasan){
        System.out.println("-> [PEMBATALAN] Ruangan " + namaRuangan + " dibatalkan. Alasan: " + alasan);
        System.out.println("-> Slot ruangan dilepas kembali untuk pelanggan lain.");
    }
}
