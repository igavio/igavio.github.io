import javax.swing.*;
  
public class Zari {
  
    public static void main(String[] args) {
  
        double lefta = 10.0; // με πόσα λεφτά ξεκινάς το παιχνίδι
        String s; // προσωρινη μεταβλητη για διαβασμα απο το πληκτρολογιο
        double stoixima; // ποσα λεφτα βαζεις στοιχημα
        int zari;        // ποσο εφερε το ζαρι
    
        s = JOptionPane.showInputDialog("Πόσα στοιχηματίζεις; ");
        stoixima = Double.parseDouble(s); //μετατρέπει σε πραγματικό
        zari = 1 + (int) (6 * Math.random()); //τυχαίος αριθμός μεταξύ 1..6
        System.out.print(" Το ζάρι έφερε " + zari + " άρα ");
        if ((zari == 1) || (zari == 2) || (zari == 4)) { //χάνεις
            lefta -= stoixima;
            System.out.print("έχασες   ");
        } else { //κερδιζεις οταν το ζαρι φερει 3, 5, 6
            lefta += stoixima;
            System.out.print("κέρδισες ");
        }
        System.out.println(stoixima + " και σου μένουν " + lefta);
    }
}
