package com.mycompany.reservasiretoran;

import java.util.ArrayList;
import java.util.HashMap;

public class Restoran {
    private String namaRestoran;
 
    private AreaMakan areaIndoor;
    private AreaMakan areaOutdoor;

    private ArrayList<Reservasi> daftarReservasi;
    private HashMap<String, Member> dataMember;
 
    public Restoran(String namaRestoran) {
        this.namaRestoran = namaRestoran;
 
        this.areaIndoor = new AreaMakan("Area Indoor", 60);
        this.areaOutdoor = new AreaMakan("Area Outdoor", 30);
 
        this.daftarReservasi = new ArrayList<>();
        this.dataMember = new HashMap<>();
    }
    
    public void bukaRestoran() {
        
    }
}
