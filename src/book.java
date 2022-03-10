public class book {

    private String ISBN;
    private String Title;
    private String AuthID;
    private String Price;

    // Likely constructor to use
    public book(String ISBN, String Title,String AuthID, String Price) {

        this.ISBN = ISBN;
        this.Title = Title;
        this.AuthID = AuthID;
        this.Price = Price;
    }

    // Other possible constructor
    public book(String ISBN) {

        this(ISBN, "", "", "");
    }

    public book() {

        this("", "", "", "");
    }

    // Get and Set methods
    public String getISBN() {

        return this.ISBN;
    }

    public void setISBN(String ISBN) {

        this.ISBN = ISBN;
    }

    public String getTitle() {

        return this.Title;
    }

    public void setTitle(String Title) {

        this.Title = Title;
    }

    public String getAuthID() {

        return this.AuthID;
    }

    public void setAuthID(String AuthID) {

        this.AuthID = AuthID;
    }

    public String getPrice() {

        return this.Price;
    }

    public void setPrice(String Price) {

        this.Price = Price;
    }

    public void set(String data, int i) {
        
        switch(i) {
            case 0:
                setISBN(data);
                break;
            case 1:
                setTitle(data);
                break;
            case 2:
                setAuthID(data);
                break;
            case 3:
                setPrice(data);
                break;
        }
    }
}



//function 1:a search title is given, the function looks for all the titles with the given title and returns the ISBNs
//function2:A function that gets all the data a book(title,ISBN, price...) given its ISBN
