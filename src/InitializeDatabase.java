import java.sql.*;
import java.util.Scanner;
import java.io.File;

public class InitializeDatabase {

    private EstablishConnection conn;

    public InitializeDatabase() {

        conn = new EstablishConnection();
    }

    public void createTables() {

        createAuthors();
        createTextbooks();
        createStudents();
        createEvaluations();
        createRequiredStatus();
        createCourses();
        createRequiredRecBooks(); 
}
    public void populateTables() {

        populateAuthors();
        populateTextbooks();   
        populateStudents();
        populateEvaluations();
        populateRequiredStatus();
        populateCourses();
        populateRequiredRecBooks();
    }

    public void createAuthors() {

        DatabaseMetaData dbm;
        ResultSet tables;
        try {
            dbm = conn.getConnection().getMetaData();
            tables = dbm.getTables(null, null, "Authors", null);

            if(tables.next()) {
                
        }
             else {
                String createString = 
                "CREATE TABLE `Authors` " + "(`Auth_ID` Int NOT NULL AUTO_INCREMENT, " +
                "`FName` varchar(50), " + "`LName` varchar(50), " +
                 "PRIMARY KEY(`Auth_ID`))";

                try {
                    conn.getStatement().executeUpdate(createString);
                 } catch(Exception e) {
                    System.out.println(e);
                    System.exit(1);
            }
        }
    } catch(Exception e) {
        System.out.println(e);
        System.exit(1);
    }
}
    public void createTextbooks() {

        DatabaseMetaData dbm;
        ResultSet tables;
        try {
            dbm = conn.getConnection().getMetaData();
            tables = dbm.getTables(null, null, "Textbooks", null);

            if(tables.next()) {
                
        }
            else {
                String createString = 
                "CREATE TABLE `Textbooks` " + "(`ISBN` Int NOT NULL, " +
                "`Title` varchar(50), " + "`Auth_ID` Int, " + "`Price` Float, " +
                "PRIMARY KEY(`ISBN`), " + "FOREIGN KEY(`Auth_ID`) REFERENCES `Authors`(`Auth_ID`))";

                 try {
                    conn.getStatement().executeUpdate(createString);
                    } catch(Exception e) {
                        System.out.println(e);
                        System.exit(1);
            }
        }
    }catch(Exception e) {
        System.out.println(e);
        System.exit(1);
    }
}
    public void createStudents() {

        DatabaseMetaData dbm;
        ResultSet tables;
        try {
            dbm = conn.getConnection().getMetaData();
            tables = dbm.getTables(null, null, "Students", null);

            if(tables.next()) {
                
        }
            else {
                String createString = 
                "CREATE TABLE `Students` " + "(`Student_ID` Int, " + "`FName` varchar(50), " + 
                "`LName` varchar(50), " + "PRIMARY KEY(`Student_ID`))";
                try {
                    conn.getStatement().executeUpdate(createString);
                } catch(Exception e) {
                    System.out.println(e);
                    System.exit(1);
                }
        }
    } catch(Exception e) {
        System.out.println(e);
        System.exit(1);
    }
}
    public void createEvaluations() {

        DatabaseMetaData dbm;
        ResultSet tables;
        try {
            dbm = conn.getConnection().getMetaData();
            tables = dbm.getTables(null, null, "Evaluations", null);

            if(tables.next()) {
                
        }
            else {
                String createString = 
                "CREATE TABLE `Evaluations` " + "(`Student_ID` Int, " + "`ISBN` Int, " +
                 "`Evaluation` varchar(500), " + "PRIMARY KEY(`Student_ID`, `ISBN`), " + 
                "FOREIGN KEY(`ISBN`) REFERENCES `Textbooks`(`ISBN`), " + 
                "FOREIGN KEY(`Student_ID`) REFERENCES `Students`(`Student_ID`))";
       
                try {
                    conn.getStatement().executeUpdate(createString);
                     } catch(Exception e) {
                        System.out.println(e);
                        System.exit(1);
            }
        }
    } catch(Exception e) {
        System.out.println(e);
        System.exit(1);
    }
}
    public void createRequiredStatus() {

        DatabaseMetaData dbm;
        ResultSet tables;
        try {
            dbm = conn.getConnection().getMetaData();
            tables = dbm.getTables(null, null, "Required_Status", null);

            if(tables.next()) {
                
        }
            else {
                String createString = 
                "CREATE TABLE `Required_Status` " + "(`Status_ID` Int NOT NULL AUTO_INCREMENT, " + 
                "`Status` varchar(20), " + "PRIMARY KEY(`Status_ID`))";

                try {
                    conn.getStatement().executeUpdate(createString);
                      } catch(Exception e) {
                            System.out.println(e);
                            System.exit(1);
            }
        }
    } catch(Exception e) {
        System.out.println(e);
        System.exit(1);
    }
}
    public void createCourses() {

        DatabaseMetaData dbm;
        ResultSet tables;
        try {
            dbm = conn.getConnection().getMetaData();
            tables = dbm.getTables(null, null, "Courses", null);

            if(tables.next()) {
                
        }
            else {

                String createString = 
                "CREATE TABLE `Courses` " + "(`CRN` Int, " + "`Title` varchar(50), " +
                 "PRIMARY KEY(`CRN`))";

                try {
                    conn.getStatement().executeUpdate(createString);
                    } catch(Exception e) {
                        System.out.println(e);
                        System.exit(1);
            }
        } 
    } catch(Exception e) {
        System.out.println(e);
        System.exit(1);
    }
}
    public void createRequiredRecBooks() {

        DatabaseMetaData dbm;
        ResultSet tables;
        try {
            dbm = conn.getConnection().getMetaData();
            tables = dbm.getTables(null, null, "Required_Rec_Books", null);

            if(tables.next()) {
                
            }
            else {
                String createString = 
                "CREATE TABLE `Required_Rec_Books` " + "(`ISBN` Int, " + "`CRN` Int, " + 
                "`Status_ID` Int, " + "PRIMARY KEY(`ISBN`, `CRN`, `Status_ID`), " +
                "FOREIGN KEY(`Status_ID`) REFERENCES `Required_Status`(`Status_ID`), " +
                "FOREIGN KEY(`ISBN`) REFERENCES `Textbooks`(`ISBN`), " + 
                "FOREIGN KEY(`CRN`) REFERENCES `Courses`(`CRN`))";

                try {
                    conn.getStatement().executeUpdate(createString);
                } catch(Exception e) {
                    System.out.println(e);
                    System.exit(1);
            }
        } 
    } catch(Exception e) {
        System.out.println(e);
        System.exit(1);
    }
}

    public void populateAuthors() {

        File file;
        Scanner input;

        try {
            file = new File("Authors.txt");
            input = new Scanner(file);

            author authors = new author();

            while(input.hasNextLine()) {

                String line = input.nextLine();
                String[] splitLine = line.split(";");

                for(int i=0; i<splitLine.length; i++) {
                    authors.set(splitLine[i], i);
                }
        
                String query = "INSERT IGNORE INTO Authors (FName, LName) " + 
                "values (?, ?)";
                PreparedStatement preparedStmt = conn.getConnection().prepareStatement(query);

                for(int i=0; i<splitLine.length; i++) {
                    preparedStmt.setString(i+1, authors.get(i));
                }
                preparedStmt.execute();
            }
            input.close();
        } catch(Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }
    public void populateTextbooks() {

        File file;
        Scanner input;

        try {
            file = new File("Textbooks.txt");
            input = new Scanner(file);

            book textbooks = new book();

            while(input.hasNextLine()) {

                String line = input.nextLine();
                String[] splitLine = line.split(";");

                for(int i=0; i<splitLine.length; i++) {

                    textbooks.set(splitLine[i], i);
                }

                String query = "INSERT IGNORE INTO Textbooks (ISBN, Title, Auth_ID, Price) " +
                "values (?, ?, ?, ?)";

                PreparedStatement preparedStmt = conn.getConnection().prepareStatement(query);

                preparedStmt.setInt(1, Integer.valueOf(textbooks.getISBN()));
                preparedStmt.setString(2, textbooks.getTitle());
                preparedStmt.setInt(3, Integer.valueOf(textbooks.getAuthID()));
                preparedStmt.setFloat(4, Float.valueOf(textbooks.getPrice()));

                preparedStmt.execute();
            }
            input.close();

        } catch(Exception e) {
            
            System.out.println(e);
            System.exit(1);
        }
    }
    public void populateStudents() {

        File file;
        Scanner input;

        try {
            file = new File("Students.txt");
            input = new Scanner(file);

            student students = new student();

            while(input.hasNextLine()) {

                String line = input.nextLine();
                String[] splitLine = line.split(";");

                for(int i=0; i<splitLine.length; i++) {
            
                    students.set(splitLine[i], i);
                }

                String query = "INSERT IGNORE INTO Students (Student_ID, FName, LName) " +
                "values (?, ?, ?)";

                PreparedStatement preparedStmt = conn.getConnection().prepareStatement(query);

                preparedStmt.setInt(1, Integer.valueOf(students.getStudentID()));
                preparedStmt.setString(2, students.getFName());
                preparedStmt.setString(3, students.getLName());

                preparedStmt.execute();
            }
            input.close();

        } catch(Exception e) {
            
            System.out.println(e);
            System.exit(1);
        }
    }
    public void populateEvaluations() {

        File file;
        Scanner input;

        try {
            file = new File("Evaluations.txt");
            input = new Scanner(file);

           evaluation evaluations = new evaluation();

            while(input.hasNextLine()) {

                String line = input.nextLine();
                String[] splitLine = line.split(";");

                for(int i=0; i<splitLine.length; i++) {
                    evaluations.set(splitLine[i], i);
                }

                String query = "INSERT IGNORE INTO Evaluations (Student_ID, ISBN, Evaluation) " +
                "values (?, ?, ?)";

                PreparedStatement preparedStmt = conn.getConnection().prepareStatement(query);

                preparedStmt.setInt(1, Integer.valueOf(evaluations.getStudentID()));
                preparedStmt.setInt(2, Integer.valueOf(evaluations.getISBN()));
                preparedStmt.setString(3, evaluations.getEvaluation());

                preparedStmt.execute();
            }
            input.close();

        } catch(Exception e) {
            
            System.out.println(e);
            System.exit(1);
        }
    }
    public void populateRequiredStatus() {

        File file;
        Scanner input;

        try {
            file = new File("RequiredStatus.txt");
            input = new Scanner(file);

           requiredStatus requiredStatus = new requiredStatus();

            while(input.hasNextLine()) {

                String line = input.nextLine();
                String[] splitLine = line.split(";");

                for(int i=0; i<splitLine.length; i++) {
                    requiredStatus.setStatus(splitLine[i]);
                }

                String query = "INSERT IGNORE INTO Required_Status (Status) " +
                "values (?)";

                PreparedStatement preparedStmt = conn.getConnection().prepareStatement(query);

                preparedStmt.setString(1, requiredStatus.getStatus());

                preparedStmt.execute();
            }
            input.close();

        } catch(Exception e) {
            
            System.out.println(e);
            System.exit(1);
        }
    }
    public void populateCourses() {

        File file;
        Scanner input;

        try {

            file = new File("Courses.txt");
            input = new Scanner(file);

           course courses = new course();

            while(input.hasNextLine()) {

                String line = input.nextLine();
                String[] splitLine = line.split(";");

                for(int i=0; i<splitLine.length; i++) {
                    courses.set(splitLine[i], i);
                }

                String query = "INSERT INTO Courses (CRN, Title) " +
                "values (?, ?)";

                PreparedStatement preparedStmt = conn.getConnection().prepareStatement(query);

                preparedStmt.setInt(1, Integer.valueOf(courses.getCRN()));
                preparedStmt.setString(2, courses.getTitle());

                preparedStmt.execute();
            }

            input.close();

        } catch(Exception e) {
            
            System.out.println(e);
            System.exit(1);
        }
    }
    public void populateRequiredRecBooks() {

        File file;
        Scanner input;

        try {
            file = new File("RequiredRecBooks.txt");
            input = new Scanner(file);

           requiredRecBook requiredRecBooks = new requiredRecBook();

            while(input.hasNextLine()) {

                String line = input.nextLine();
                String[] splitLine = line.split(";");

                for(int i=0; i<splitLine.length; i++) {
                    requiredRecBooks.set(splitLine[i], i);
                }

                String query = "INSERT IGNORE INTO Required_Rec_Books  (ISBN, CRN, Status_ID) " +
                "values (?, ?, ?)";

                PreparedStatement preparedStmt = conn.getConnection().prepareStatement(query);

                preparedStmt.setInt(1, Integer.valueOf(requiredRecBooks.getISBN()));
                preparedStmt.setInt(2, Integer.valueOf(requiredRecBooks.getCRN()));
                preparedStmt.setInt(3, Integer.valueOf(requiredRecBooks.getStatusID()));

                preparedStmt.execute();
            }
            input.close();

        } catch(Exception e) {
            
            System.out.println(e);
            System.exit(1);
        }
    }
}



