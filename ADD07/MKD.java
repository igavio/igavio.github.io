import java.text.DecimalFormat;

/**
 * ��������� ����� ����������� ������� ��� ���
 * @author I Gaviotis
 * @version 2007-11-20
 */
public class MKD {
  
  /**
   * ����������� ��� ������
   */
  public static int ITERATIONS = 3000;
  
  /**
   * ����� ��������� ��� ����� ����������
   * @param args ������� ��
   */
  public static void main (String args[]) {
    long startTime, endTime; // ��� �� ������������
    int mkd; // ��������� ��� �� ����������
    System.out.println ("�����������: " + ITERATIONS);
    System.out.println ("� ������ ����������� ��� ��� ��������� ��� ��������.");
    
    initCounters ();
    startTime = System.currentTimeMillis ();
    for (int i = 1; i <= ITERATIONS; i++) {
      for (int j = 1; j <= ITERATIONS; j++) {
        mkd = mkdA (i, j);
        // System.out.println("A " + i + " " + j + " " + mkd);
      } // end for j
    } // end for i
    endTime = System.currentTimeMillis ();
    printResults ("���������� ���������� ����������", endTime - startTime);
    
    initCounters ();
    startTime = System.currentTimeMillis ();
    for (int i = 1; i <= ITERATIONS; i++) {
      for (int j = 1; j <= ITERATIONS; j++) {
        mkd = mkdB (i, j);
        // System.out.println("B " + i + " " + j + " " + mkd);
      } // end for j
    } // end for i
    endTime = System.currentTimeMillis ();
    printResults ("���������� ���������� ��������� �������� ���������", endTime - startTime);
    
    initCounters ();
    startTime = System.currentTimeMillis ();
    for (int i = 1; i <= ITERATIONS; i++) {
      for (int j = 1; j <= ITERATIONS; j++) {
        mkd = mkdM (i, j);
        // System.out.println("M " + i + " " + j + " " + mkd);
      } // end for j
    } // end for i
    endTime = System.currentTimeMillis ();
    printResults ("���������� JOSEPH STEIN", endTime - startTime);
    
  }
  
  private static int countComp;   // ����������
  private static int countAddSub; // ���������������
  private static int countMulDiv; // ���������������, ����������
  private static int countMod;    // �������� �������� ���������
  private static int countIter;   // �����������
  private static DecimalFormat decForm = new DecimalFormat ("0,000");
  
  private static void initCounters () {
    countComp = countAddSub = countMulDiv = countMod = countIter = 0;
  }
  
  private static void printResults (String title, long time) {
    System.out.println (title);
    System.out.println ("   ������: " + decForm.format (time) + " millisec");
    System.out.println ("   ����������: "                   + decForm.format (countComp ));
    System.out.println ("   ���������������: "              + decForm.format (countAddSub));
    System.out.println ("   ��������������� / ����������: " + decForm.format (countMulDiv));
    System.out.println ("   �������� �������� ���������: "  + decForm.format (countMod));
    System.out.println ("   �����������: "                  + decForm.format (countIter));
  }
  
  /**
   * ���������� ������� ��� �� ���������� ����������
   * @param x � ���� ��������, >=0
   * @param y � ����� ��������, >=0
   * @return  � �������� ������ ��������� ���� >=1
   */
  public static int mkdA (int x, int y) {
    int temp; // ��������� ��������� ��� �������������� �����
    countComp++;
    if ( x < y ) {    // �� ����������,
      temp = y;     // ������������ ��� ����� ��� x, y
      y    = x;     // ���� ���� ���� y �� ��������� � ���������
      x    = temp;  // ��� ���� x � ���������� ��� ���
    }
    
    // ������������ ����� ������ ��� �� ��� �� ����� �����
    while ( y != 0 ) { // ������� �������� ��� && ���������� ���� ��� ���������
      countComp++; countIter++;
      temp = y;
      y    = Math.abs (x - y);                       countComp++; countAddSub++;
      x    = temp;
      countComp++;
      if ( x < y ) {    // �� ����������,
        temp = y;     // ������������ ��� ����� ��� x, y
        y    = x;     // ���� ���� ���� y �� ��������� � ���������
        x    = temp;  // ��� ���� x � ���������� ��� ���
      }
    } // end while
    countComp++; countIter++;
    return x; // ��� ���������� ��� ���
  }
  
  
  /**
   * ���������� ������� ��� �� ��������� �������� �������� ���������
   * @param x � ���� ��������, >=0
   * @param y � ����� ��������, >=0
   * @return  � �������� ������ ��������� ���� >=1
   */
  public static int mkdB (int x, int y) {
    int temp; // ��������� ��������� ��� �������������� �����
    countComp++;
    if ( x < y ) {    // �� ����������,
      temp = y;     // ������������ ��� ����� ��� x, y
      y    = x;     // ���� ���� ���� y �� ��������� � ���������
      x    = temp;  // ��� ���� x � ���������� ��� ���
    }
    
    // ������������ ����� ������ ��� �� ��� �� ����� �����
    while ( y != 0 ) { // ������� �������� ��� && ���������� ���� ��� ���������
      countComp++; countIter++;
      temp = y;
      y    = x % y;                                                  countMod++;
      x    = temp;
      countComp++;
      if ( x < y ) {    // �� ����������,
        temp = y;     // ������������ ��� ����� ��� x, y
        y    = x;     // ���� ���� ���� y �� ��������� � ���������
        x    = temp;  // ��� ���� x � ���������� ��� ���
      }
    } // end while
    countComp++; countIter++;
    return x; // ��� ���������� ��� ���
  }
  
  /**
   * ���������� ������� ��� ��� Joseph Stein
   * @param x � ���� ��������, >=0
   * @param y � ����� ��������, >=0
   * @return  � �������� ������ ��������� ���� >=1
   */
  public static int mkdM (int x, int y) {
    countIter++;
    countComp++;
    if (x == 0) return y;
    countComp++;
    if (y == 0) return x;
    countMod++; countComp++;
    if (x%2 == 0) { // x ������
      countMod++; countComp++;
      if (y%2 == 0) { // y ������
        countMulDiv++; countMulDiv++; countMulDiv++;
        return 2* mkdM (x/2, y/2);
      } else { // y ��������
        countMulDiv++;
        return mkdM (x/2, y);
      }
    } else { // x ��������
      countMod++; countComp++;
      if (y%2 == 0) { // y ������
        countMulDiv++;
        return mkdM (x, y/2);
      } else { // y ��������
        countAddSub++; countMulDiv++; countAddSub++; countMulDiv++; countComp++;
        return mkdM ((x + y)/2, Math.abs (x-y)/2);
      }
    }
  }
  
}