import java.text.DecimalFormat;

/**
 * Συγκρινει τρεις αλγοριθμους ευρεσης του ΜΚΔ
 * @author I Gaviotis
 * @version 2007-11-20
 */
public class MKD {
  
  /**
   * επαναληψεις του κωδικα
   */
  public static int ITERATIONS = 3000;
  
  /**
   * Καλεί διαδοχικα τις τρεις παραλλαγες
   * @param args αγνοησε το
   */
  public static void main (String args[]) {
    long startTime, endTime; // για τη χρονομετρηση
    int mkd; // μεταβλητη για το αποτελεσμα
    System.out.println ("ΕΠΑΝΑΛΗΨΕΙΣ: " + ITERATIONS);
    System.out.println ("Ο χρόνος επηρεάζεται από την ενημέρωση των μετρητών.");
    
    initCounters ();
    startTime = System.currentTimeMillis ();
    for (int i = 1; i <= ITERATIONS; i++) {
      for (int j = 1; j <= ITERATIONS; j++) {
        mkd = mkdA (i, j);
        // System.out.println("A " + i + " " + j + " " + mkd);
      } // end for j
    } // end for i
    endTime = System.currentTimeMillis ();
    printResults ("ΑΛΓΟΡΙΘΜΟΣ ΔΙΑΔΟΧΙΚΩΝ ΑΦΑΙΡΕΣΕΩΝ", endTime - startTime);
    
    initCounters ();
    startTime = System.currentTimeMillis ();
    for (int i = 1; i <= ITERATIONS; i++) {
      for (int j = 1; j <= ITERATIONS; j++) {
        mkd = mkdB (i, j);
        // System.out.println("B " + i + " " + j + " " + mkd);
      } // end for j
    } // end for i
    endTime = System.currentTimeMillis ();
    printResults ("ΑΛΓΟΡΙΘΜΟΣ ΔΙΑΔΟΧΙΚΩΝ ΥΠΟΛΟΙΠΩΝ ΑΚΕΡΑΙΑΣ ΔΙΑΙΡΕΣΗΣ", endTime - startTime);
    
    initCounters ();
    startTime = System.currentTimeMillis ();
    for (int i = 1; i <= ITERATIONS; i++) {
      for (int j = 1; j <= ITERATIONS; j++) {
        mkd = mkdM (i, j);
        // System.out.println("M " + i + " " + j + " " + mkd);
      } // end for j
    } // end for i
    endTime = System.currentTimeMillis ();
    printResults ("ΑΛΓΟΡΙΘΜΟΣ JOSEPH STEIN", endTime - startTime);
    
  }
  
  private static int countComp;   // συγκρισεις
  private static int countAddSub; // προσθαφαιρεσεις
  private static int countMulDiv; // πολλαπλασιασμοι, διαιρεσεις
  private static int countMod;    // υπολοιπα ακεραιας διαιρεσης
  private static int countIter;   // επαναληψεις
  private static DecimalFormat decForm = new DecimalFormat ("0,000");
  
  private static void initCounters () {
    countComp = countAddSub = countMulDiv = countMod = countIter = 0;
  }
  
  private static void printResults (String title, long time) {
    System.out.println (title);
    System.out.println ("   Χρόνος: " + decForm.format (time) + " millisec");
    System.out.println ("   Συγκρίσεις: "                   + decForm.format (countComp ));
    System.out.println ("   Προσθαφαιρέσεις: "              + decForm.format (countAddSub));
    System.out.println ("   Πολλαπλασιασμοί / Διαιρέσεις: " + decForm.format (countMulDiv));
    System.out.println ("   Υπόλοιπα ακέραιας διαίρεσης: "  + decForm.format (countMod));
    System.out.println ("   Επαναλήψεις: "                  + decForm.format (countIter));
  }
  
  /**
   * Αλγόριθμος εύρεσης ΜΚΔ με διαδοχικές αφαιρέσεις
   * @param x ο ένας ακέραιος, >=0
   * @param y ο άλλος ακέραιος, >=0
   * @return  ο μέγιστος κοινός διαιρέτης τους >=1
   */
  public static int mkdA (int x, int y) {
    int temp; // προσωρινη μεταβλητη για αντιμεταθεσεις τιμων
    countComp++;
    if ( x < y ) {    // αν χρειαζεται,
      temp = y;     // αντιμεταθεσε τις τιμες των x, y
      y    = x;     // ετσι ωστε στον y να βρισκεται η μικροτερη
      x    = temp;  // και στον x η μεγαλυτερη των δυο
    }
    
    // επαναλαμβανε μεχρι καποιο απο τα δυο να γινει μηδεν
    while ( y != 0 ) { // γλιτωνω συγκριση και && ελεγχοντας μονο τον μικροτερο
      countComp++; countIter++;
      temp = y;
      y    = Math.abs (x - y);                       countComp++; countAddSub++;
      x    = temp;
      countComp++;
      if ( x < y ) {    // αν χρειαζεται,
        temp = y;     // αντιμεταθεσε τις τιμες των x, y
        y    = x;     // ετσι ωστε στον y να βρισκεται η μικροτερη
        x    = temp;  // και στον x η μεγαλυτερη των δυο
      }
    } // end while
    countComp++; countIter++;
    return x; // τον μεγαλυτερο των δυο
  }
  
  
  /**
   * Αλγόριθμος εύρεσης ΜΚΔ με διαδοχικά υπόλοιπα ακέραιας διαίρεσης
   * @param x ο ένας ακέραιος, >=0
   * @param y ο άλλος ακέραιος, >=0
   * @return  ο μέγιστος κοινός διαιρέτης τους >=1
   */
  public static int mkdB (int x, int y) {
    int temp; // προσωρινη μεταβλητη για αντιμεταθεσεις τιμων
    countComp++;
    if ( x < y ) {    // αν χρειαζεται,
      temp = y;     // αντιμεταθεσε τις τιμες των x, y
      y    = x;     // ετσι ωστε στον y να βρισκεται η μικροτερη
      x    = temp;  // και στον x η μεγαλυτερη των δυο
    }
    
    // επαναλαμβανε μεχρι καποιο απο τα δυο να γινει μηδεν
    while ( y != 0 ) { // γλιτωνω συγκριση και && ελεγχοντας μονο τον μικροτερο
      countComp++; countIter++;
      temp = y;
      y    = x % y;                                                  countMod++;
      x    = temp;
      countComp++;
      if ( x < y ) {    // αν χρειαζεται,
        temp = y;     // αντιμεταθεσε τις τιμες των x, y
        y    = x;     // ετσι ωστε στον y να βρισκεται η μικροτερη
        x    = temp;  // και στον x η μεγαλυτερη των δυο
      }
    } // end while
    countComp++; countIter++;
    return x; // τον μεγαλυτερο των δυο
  }
  
  /**
   * Αλγόριθμος εύρεσης ΜΚΔ του Joseph Stein
   * @param x ο ένας ακέραιος, >=0
   * @param y ο άλλος ακέραιος, >=0
   * @return  ο μέγιστος κοινός διαιρέτης τους >=1
   */
  public static int mkdM (int x, int y) {
    countIter++;
    countComp++;
    if (x == 0) return y;
    countComp++;
    if (y == 0) return x;
    countMod++; countComp++;
    if (x%2 == 0) { // x άρτιος
      countMod++; countComp++;
      if (y%2 == 0) { // y άρτιος
        countMulDiv++; countMulDiv++; countMulDiv++;
        return 2* mkdM (x/2, y/2);
      } else { // y περιττός
        countMulDiv++;
        return mkdM (x/2, y);
      }
    } else { // x περιττός
      countMod++; countComp++;
      if (y%2 == 0) { // y άρτιος
        countMulDiv++;
        return mkdM (x, y/2);
      } else { // y περιττός
        countAddSub++; countMulDiv++; countAddSub++; countMulDiv++; countComp++;
        return mkdM ((x + y)/2, Math.abs (x-y)/2);
      }
    }
  }
  
}