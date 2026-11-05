package com.mycompany.reservasiretoran;

public class TransaksiReservasi {
    public static void prosesTransaksi(Member member, Reservasi reservasi) {
        System.out.println("\n===== STRUK TRANSAKSI RESERVASI =====");
        System.out.println("Member    : " + member.getNama() + " (" + member.getIdMember() + ")");
        System.out.println("Reservasi : " + reservasi.getKodeReservasi() + " a.n. " + reservasi.getNamaPelanggan());
        System.out.printf("Jam       : %02d:00%n", reservasi.getJam());
        System.out.printf("Biaya     : Rp%,.0f%n", reservasi.hitungBiaya());
        System.out.println("Status    : Berhasil dicatat di sistem.");
        
        reservasi.tampilkanKetentuan();
 
        if (reservasi instanceof DapatPreOrder) {
            ((DapatPreOrder) reservasi).prosesPreOrder();
        }
        System.out.println("=====================================");
    }   
}
