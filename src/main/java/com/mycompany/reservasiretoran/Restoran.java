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
        System.out.println("\n*** Menginisialisasi sistem " + this.namaRestoran + " ***");
        areaIndoor.bukaArea();
        areaOutdoor.bukaArea();
    }

    public void tambahReservasi(Reservasi r) {
        daftarReservasi.add(r);
    }

    public void lihatSemuaReservasi() {
        if (daftarReservasi.isEmpty()) {
            System.out.println("Belum ada data reservasi.");
            return;
        }
        System.out.println("============ DAFTAR RESERVASI RESTORAN ============");
        for (int i = 0; i < daftarReservasi.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarReservasi.get(i).tampilkanInfo();
            daftarReservasi.get(i).tampilkanKetentuan();
            System.out.println("---------------------------------------------------");
        }
        System.out.println("Total reservasi dibuat: " + Reservasi.getTotalReservasi());
    }

    public void cariReservasi(String nama) throws ReservasiTidakDitemukanException {
        boolean ketemu = false;
        for (Reservasi r : daftarReservasi) {
            if (r.getNamaPelanggan().toLowerCase().contains(nama.toLowerCase())) {
                r.tampilkanInfo();
                ketemu = true;
            }
        }
        if (!ketemu) {
            throw new ReservasiTidakDitemukanException("Reservasi atas nama \"" + nama + "\" tidak ditemukan.");
        }
    }

    public void cariReservasi(int jam) throws ReservasiTidakDitemukanException {
        boolean ketemu = false;
        for (Reservasi r : daftarReservasi) {
            if (r.getJam() == jam) {
                r.tampilkanInfo();
                ketemu = true;
            }
        }
        if (!ketemu) {
            throw new ReservasiTidakDitemukanException("Tidak ada reservasi pada jam " + jam + ":00.");
        }
    }

    public void tambahMember(Member m) {
        dataMember.put(m.getIdMember(), m);
    }

    public void cariMember(String id) {
        if (dataMember.containsKey(id)) {
            System.out.println("Ditemukan! Nama: " + dataMember.get(id).getNama());
        } else {
            System.out.println("Member dengan ID tersebut tidak terdaftar.");
        }
    }

    public void lihatSemuaMember() {
        System.out.println("Total Member Terdaftar: " + dataMember.size());
        for (Member m : dataMember.values()) {
            System.out.println("- ID: " + m.getIdMember() + " | Nama: " + m.getNama());
        }
    }

    public Member getMember(String id) { return dataMember.get(id); }
    public Reservasi getReservasiInt(int index) { return daftarReservasi.get(index); }
    public int getJumlahReservasi() { return daftarReservasi.size(); }
}