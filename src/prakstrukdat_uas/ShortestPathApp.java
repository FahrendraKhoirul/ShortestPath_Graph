
package prakstrukdat_uas;

import java.util.Scanner;


public class ShortestPathApp {
    final static DirectedWeightedGraph graph = new DirectedWeightedGraph();
    final static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        graph.addVertex("Bondowoso");
        graph.addVertex("Malang");
        graph.addVertex("Surabaya");
        graph.addVertex("Lamongan");
        graph.addEdge("Bondowoso", "Surabaya", 150);
        graph.addEdge("Bondowoso", "Malang", 100);
        graph.addEdge("Malang", "Surabaya", 150);
        graph.addEdge("Surabaya", "Lamongan", 70);
        graph.findPaths("Bondowoso");
       
        while(true){
            Home();  
            int input = in.nextInt();
            if(input == 1){
               lihatDaftarKota();
            }else if(input == 2){
               lihatDaftar();
            }else if(input == 3){
               tambahKota();
            }else if(input == 4){
               tambahJalur();
            }else if(input == 5){
              mencariJalur();
            }else if(input == 6){
                mencariJalurAntarKota();
                break;
            }else if(input == 7){
                break;
            }else{
                System.out.println("Input tidak diketahui\n");
            }
        }
    }
    
    public static void Home(){
        System.out.println("\n==============================");
        System.out.println(" JALUR KURIR TERPENDEK ");
        System.out.println("========================");
        System.out.println("1. Daftar Kota");
        System.out.println("2. Daftar Jarak Kota");
        System.out.println("3. Tambahkan Kota");
        System.out.println("4. Tambahkan Jalur");
        System.out.println("5. Mencari Jalur");
        System.out.println("6. Mencari Jalur Antar Kota ");
        System.out.println("7. Keluar");
        System.out.println("");
        System.out.print("Pilih Nomor : ");
    }
    
    public static void lihatDaftar(){
        graph.printGraph();
        String exit = in.next();
    }
    
    public static void tambahKota(){
        System.out.println("\n-- Menambahkan Kota --");
        System.out.print("Masukkan nama kota : ");
        String kota = in.next();
        if(kota.isEmpty()){
            System.out.print("!! Nama kota tidak boleh kosong !!");
        }else{
            graph.addVertex(kota);
        }
        String exit = in.next();

    }
    
    public static void tambahJalur(){
        System.out.println("\n-- Menambahkan Jalur --");
        System.out.print("Masukkan Kota Asal : ");
        String kotaAsal = in.next();
        System.out.print("Masukkan Kota Tujuan : ");
        String kotaTujuan = in.next();
        System.out.print("Masukkan jarak antar kota : ");
        int jarak = in.nextInt();
        if(kotaAsal.isEmpty() || kotaTujuan.isEmpty() || jarak == 0){
            System.out.println("!! Kota Asal, Kota Tujuan, Jarak tidak boleh kosong !!");
        }else{
            graph.addEdge(kotaAsal, kotaTujuan, jarak);
        }
        String exit = in.next();
       
    }
    
    public static void mencariJalur(){
        System.out.println("\n-- Mencari Jalur --");
        System.out.print("Masukkan Kota Asal : ");
        String kotaAsal = in.next();
         if(kotaAsal.isEmpty()){
            System.out.print("!! Nama kota asal tidak boleh kosong !!");
        }else{
        graph.findPaths(kotaAsal);
        }
        String exit = in.next();
    }
    
    public static void mencariJalurAntarKota(){
        System.out.println("\n-- Mencari Jalur Antar Kota--");
        System.out.print("Masukkan Kota Asal : ");
        String kotaAsal = in.next();
        System.out.print("Masukkan Kota Tujuan : ");
        String kotaTujuan = in.next();
         if(kotaAsal.isEmpty() || kotaTujuan.isEmpty()){
            System.out.print("!! Nama kota asal dan tujuan tidak boleh kosong !!");
        }else{
            graph.findPaths(kotaAsal, kotaTujuan);
        }
        String exit = in.next();
    }
    
   public static void lihatDaftarKota(){
        graph.printVertex();
        String exit = in.nextLine();
    }
}
