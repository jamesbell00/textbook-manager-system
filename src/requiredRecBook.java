
public class requiredRecBook {

    private String ISBN;
    private String CRN;
    private String StatusID;

    public requiredRecBook(String ISBN, String CRN, String StatusID) {

        this.ISBN = ISBN;
        this.CRN = CRN;
        this.StatusID = StatusID;
    }

    public requiredRecBook() {

        this("", "", "");
    }

    public String getISBN() {

        return this.ISBN;
    }

    public void setISBN(String ISBN) {

        this.ISBN = ISBN;
    }

    public String getCRN() {

        return this.CRN;
    }

    public void setCRN(String CRN) {

        this.CRN = CRN;
    }

    public String getStatusID(){

        return this.StatusID;
    }

    public void setStatusID(String StatusID){

        this.StatusID = StatusID;
    } 

    public void set(String data, int i) {

        switch(i) {
            case 0:
                setISBN(data);
                break;
            case 1:
                setCRN(data);
                break;
            case 2:
                setStatusID(data);
                break;
        }
    }
}
