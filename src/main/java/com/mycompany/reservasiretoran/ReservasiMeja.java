package com.mycompany.reservasiretoran;
 
//implements 1 interface (DapatDibatalkan)
public class ReservasiMeja extends Reservasi implements DapatDiBatalkan {
    private int nomorMeja;
    private String area;
 
    public ReservasiMeja(String namaPelanggan, int jumlahOrang, int jam, int nomorMeja, String area) {
        super(namaPelanggan, jumlahOrang, jam);
        setNomorMeja(nomorMeja);
        setArea(area);
    }
 
    public int getNomorMeja() { return nomorMeja; }
    public String getArea() { return area; }
 
    public void setNomorMeja(int nomorMeja) {
        if (nomorMeja < 1 || nomorMeja > 30) {
            this.nomorMeja = 1;
        } else {
            this.nomorMeja = nomorMeja;
        }
    }
    public void setArea(String area) {
        if (area != null && area.toLowerCase().contains("outdoor")) {
            this.area = "Outdoor";
        } else {
            this.area = "Indoor";
        }
    }
 
    @Override
    public double hitungBiaya() {
        return getJumlahOrang() * 15000;
    }
 
    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.printf("-> Meja  | Meja %d (%s) | Deposit Rp%,.0f%n",
                nomorMeja, area, hitungBiaya());
    }
 
    //override abstract method dari superclass
    @Override
    public void tampilkanKetentuan() {
        System.out.println("-> Ketentuan Meja: deposit dikembalikan jika pelanggan hadir tepat waktu.");
    }
 
    //override method dari interface DapatDibatalkan
    @Override
    public void batalkanReservasi(String alasan) {
        System.out.println("-> [PEMBATALAN] Meja " + nomorMeja + " (" + area + ") dibatalkan. Alasan: " + alasan);
        System.out.println("-> Deposit dikembalikan jika batal minimal 2 jam sebelum jam reservasi.");
    }
}
 
