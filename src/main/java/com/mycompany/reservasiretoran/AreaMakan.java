package com.mycompany.reservasiretoran;

public class AreaMakan {
    private String namaArea;
    private int kapasitasKursi;
    
    public AreaMakan(String namaArea, int kapasistasKursi) {
        this.namaArea = namaArea;
        this.kapasitasKursi = kapasitasKursi;
    }
    
    public void bukaArea() {
        System.out.println("-> [" + namaArea + "] dibuka. Kapasitas: " + kapasitasKursi + "kursi.");
    }
}
