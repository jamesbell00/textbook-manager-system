public class evaluation {
    
    private String StudentID;
    private String ISBN;
    private String Evaluation;

    
    public evaluation(String StudentID, String ISBN, String Evaluation) {

        this.StudentID = StudentID;
        this.ISBN = ISBN;
        this.Evaluation = Evaluation;
    }

    public evaluation() {

        this("", "", "");
    }

    // Get and Set methods
    public String getStudentID() {

        return this.StudentID;
    }

    public void setStudentID(String StudentID) {

        this.StudentID = StudentID;
    }

    public String getISBN() {

        return this.ISBN;
    }

    public void setISBN(String ISBN) {

        this.ISBN = ISBN;
    }

    public String getEvaluation() {

        return this.Evaluation;
    }

    public void setEvaluation(String Evaluation) {

        this.Evaluation = Evaluation;
    }

    public void set(String data, int i) {
        
        switch(i) {
            case 0:
                setStudentID(data);
                break;
            case 1:
                setISBN(data);
                break;
            case 2:
                setEvaluation(data);
                break;
        }
    }
}
