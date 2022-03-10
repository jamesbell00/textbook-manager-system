//class to set all the info of one student
public class student {

        private String StudentID;
        private String FName;
        private String LName;
    
        public student(String StudentID, String FName, String LName) {
    
            this.StudentID = StudentID;
            this.FName = FName;
            this.LName = LName;
        }

        public student() {

            this("", "", "");
        }
    
        // Get and Set methods
        public int getStudentID() {
        	int ID=Integer.parseInt(StudentID);
            return ID;
        }
    
        public void setStudentID(String StudentID) {
    
            this.StudentID = StudentID;
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

        public void set(String data, int i) {
        
            switch(i) {
                case 0:
                    setStudentID(data);
                    break;
                case 1:
                    setFName(data);
                    break;
                case 2:
                    setLName(data);
                    break;
            }
        }
    }