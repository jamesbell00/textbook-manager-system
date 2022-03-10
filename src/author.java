//class to set all the info of one author
public class author {

    private String FName;
    private String LName;

    // Likely constructor to use
    public author(String FName, String LName) {

        this.FName = FName;
        this.LName = LName;
    }

    public String getFName() {

        return this.FName;
    }

    public void setFName(String FName) {

        this.FName = FName;
    }

    public String getLName() {

        return this.LName;
    }

    public void setLName(String LName) {

        this.LName = LName;
    }

    //Following methods are used for initializing database

    public author() {

        this("", "");
    }

    public void set(String data, int i) {

        switch(i) {
            case 0:
                setFName(data);
                break;
            case 1:
                setLName(data);
                break;
        }
    }
    public String get(int i) {

        String value = "";
        switch(i) {
            case 0:
                value = getFName();
                break;
            case 1:
                value = getLName();
                break;
        }

        return value;
    }

	public void setAuthID(String authID) {
		// TODO Auto-generated method stub
		
	}
}
