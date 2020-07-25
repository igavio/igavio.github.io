/**
 * Το πρόγραμμα μοντελοποιεί μια μηχανή έκδοσης εισιτηρίων.
 * Εκδίδει ένα τύπο εισιτηρίου του οποίου η τιμή ορίζεται στην κατασκευαστή.
 * Δεν ελέγχει ούτε την τιμή του εισιτηρίου, ούτε το ποσό που εισάγουν οι χρήστες 
 *
 * @author David J. Barnes and Michael Kolling
 * @version 2003.12.01
 */
public class TicketMachine
{
    // Η τιμή του εισιτηρίου από αυτή τη μηχανή.
    private int price;
    // Το ποσό των χρημάτων που εισήγαγε ο χρήστης μέχρι στιγμής.
    private int balance;
    // Το συνολικό ποσό που έχει συλλέξει η μηχανή από τα εισιτήρια που έχει εκδώσει.
    private int total;

    /**
     * Δημιουργεί μια μηχανή που εκδίδει εισιτήρια συγκεκριμένης τιμής.
     * Σημειώστε ότι η τιμή πρέπει να είναι μεγαλύτερη από μηδέν. Δεν ελέγχεται.
     */
    public TicketMachine(int ticketCost)
    {
        price = ticketCost;
        balance = 0;
        total = 0;
    }

    /**
     * Επιστρέφει την τιμή του εισιτηρίου.
     */
    public int getPrice()
    {
        return price;
    }

    /**
     * Επιστρέφει το ποσό που έχει εισαχθεί στον κερματοδέκτη 
     * για το επόμενο εισιτήριο.
     */
    public int getBalance()
    {
        return balance;
    }

    /**
     * Λαμβάνει στον κερματοδέκτη ένα ποσό σε λεπτά από έναν πελάτη.
     */
    public void insertMoney(int amount)
    {
        balance = balance + amount;
    }

    /**
     * Τυπώνει ένα εισιτήριο
     * Ενημερώνει το συνολικό ποσό στο ταμείο και μηδενίζει τον κερματοδέκτη.
     */
    public void printTicket()
    {
        System.out.println("##################");
        System.out.println("# " + price + " λεπτά.");
        System.out.println();

        total = total + balance; // Ενημερώνει το συνολικό ποσό.
        balance = 0; // Αδειάζει τον κερματοδέκτη.

    }
}
