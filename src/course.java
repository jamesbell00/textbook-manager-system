//class to set all the info of one course
public class course {

    private String CRN;
    private String Title;

    public course(String CRN, String Title) {

        this.CRN = CRN;
        this.Title = Title;
    }

    public course() {

        this("", "");
    }

    public String getCRN() {

        return this.CRN;
    }

    public void setCRN(String CRN) {

        this.CRN = CRN;
    }

    public String getTitle() {

        return this.Title;
    }

    public void setTitle(String Title) {

        this.Title = Title;
    }

    public void set(String data, int i) {

        switch(i) {
            case 0:
                setCRN(data);
                break;
            case 1:
                setTitle(data);
                break;
        }
    }
}
