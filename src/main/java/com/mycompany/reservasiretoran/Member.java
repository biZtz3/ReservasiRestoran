package com.mycompany.reservasiretoran;

public class Member {
    private String idMember;
    private String nama;
    
    public Member(String idMember, String nama) {
        this.idMember = idMember;
        this.nama = nama;
    }
    
    public String getIdMember() { return idMember; }
    public String getNama() {return nama; }
}
