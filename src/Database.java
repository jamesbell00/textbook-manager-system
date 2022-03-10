import java.sql.*;

public class Database {

   
    private EstablishConnection connection;
    private ResultSet rs;

    public Database() {
        
        connection = new EstablishConnection();
    }

    public ResultSet getTables() {

        try {
            this.rs = connection.getStatement().executeQuery("Show tables");
            return rs;
        } catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }
        return rs;
    }

    public ResultSet getAuthors() {

        try {
            this.rs = connection.getStatement().executeQuery("SELECT * FROM Authors");
            return rs;
        } catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }

        return rs;
    }

    public ResultSet getCourses() {

        try {
            this.rs = connection.getStatement().executeQuery("SELECT * FROM Courses");
            return rs;
            } catch(Exception e) {
    
                System.out.println(e);
                System.exit(1);
            }
    
            return rs;
    }


    
    public ResultSet getStudent(String Student_ID) {

  	  try {

          PreparedStatement pstmt;

          String query = "SELECT * FROM Students WHERE Student_ID=?";
          pstmt = connection.getConnection().prepareStatement(query);

          pstmt.setString(1, Student_ID);

          rs = pstmt.executeQuery();

      }catch(Exception e) {

          System.out.println(e);
          System.exit(1);
      }
      return rs;
        }

    public ResultSet getTextbooks() {

        try {
            this.rs = connection.getStatement().executeQuery("SELECT * FROM Textbooks");
            return rs;
            } catch(Exception e) {
    
                System.out.println(e);
                System.exit(1);
            }
    
            return rs;
        }
    
    public ResultSet getEvaluations() {

        try {
            this.rs = connection.getStatement().executeQuery("SELECT * FROM Evaluations");
            return rs;
            } catch(Exception e) {
    
                System.out.println(e);
                System.exit(1);
            }
            return rs;
    }
    
    public ResultSet getEvaluation(String string) {

    	  try {

              PreparedStatement pstmt;

              String query = "SELECT * FROM Evaluations WHERE ISBN=?";
              pstmt = connection.getConnection().prepareStatement(query);

              pstmt.setString(1, string);

              rs = pstmt.executeQuery();

          }catch(Exception e) {

              System.out.println(e);
              System.exit(1);
          }
          return rs;
    }
    
    public ResultSet getRequiredRecBooks() {

        try {
            this.rs = connection.getStatement().executeQuery("SELECT * FROM Required_Rec_Books");
            return rs;
            } catch(Exception e) {
    
                System.out.println(e);
                System.exit(1);
            }
    
            return rs;
    }

    public ResultSet getRequiredStatus() {

        try {
            this.rs = connection.getStatement().executeQuery("SELECT * FROM Required_Status");
            return rs;
            } catch(Exception e) {
    
                System.out.println(e);
                System.exit(1);
            }
    
            return rs;
    }

    public ResultSet getCourseBookStatus() {

        try {
            String query = "SELECT c.Title as 'Course', " + 
            "t.Title as 'Book', " + "s.Status " + "FROM Required_Rec_Books r " +
            "JOIN Required_Status s ON s.Status_ID = r.Status_ID " +
            "JOIN Textbooks t ON t.ISBN = r.ISBN " + "JOIN Courses c " + 
            "ON c.CRN = r.CRN";

            this.rs = connection.getStatement().executeQuery(query);
            return rs;
        } catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }
        return rs;

    }

    public ResultSet getISBN(String title) {

        try {

            PreparedStatement pstmt;

            String query = "SELECT ISBN FROM Textbooks WHERE Title=?";
            pstmt = connection.getConnection().prepareStatement(query);

            pstmt.setString(1, title);

            rs = pstmt.executeQuery();

        } catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }
        return rs;

    }
  

    public ResultSet getTextbookInfo(int ISBN) {

        try {

            PreparedStatement pstmt;

            String query = "SELECT * FROM Textbooks WHERE ISBN=?";
            pstmt = connection.getConnection().prepareStatement(query);

            pstmt.setInt(1, ISBN);

            rs = pstmt.executeQuery();

        }catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }
        return rs;
    }
    public ResultSet getReqRec(int ISBN) {

        try {

            PreparedStatement pstmt;

            String query = "SELECT * FROM Required_Rec_Books WHERE ISBN=?";
            pstmt = connection.getConnection().prepareStatement(query);

            pstmt.setInt(1, ISBN);

            rs = pstmt.executeQuery();

        }catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }
        return rs;
    }

    public ResultSet getDetailsAboutBook(int ISBN) {

        try {

            String query = "SELECT t.Title as 'Book', t.ISBN, t.Price, " + 
            "c.Title as 'Course', s.Status, concat(stu.FName, ' ', stu.LName) as 'Student', " +
            "e.Evaluation " + " FROM Textbooks t " + "JOIN Required_Rec_Books rr " + 
            "ON rr.ISBN = t.ISBN " + "JOIN Courses c ON c.CRN = rr.CRN " + 
            "JOIN Required_Status s ON s.Status_ID = rr.Status_ID " + 
            "JOIN Evaluations e ON e.ISBN = t.ISBN " + "JOIN Students stu " + 
            "ON stu.Student_ID = e.Student_ID " + "JOIN Authors a " + 
            "ON a.Auth_ID = t.Auth_ID " + "WHERE t.ISBN LIKE " + String.valueOf(ISBN);

            rs = connection.getStatement().executeQuery(query);
        } catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }
        return rs;
    }

    public ResultSet getAuthorName(String string) {

        try {

            PreparedStatement pstmt;

            String query = "SELECT FNAME,LNAME FROM Authors WHERE Auth_ID=?";
            pstmt = connection.getConnection().prepareStatement(query);

            pstmt.setString(1, string);

            rs = pstmt.executeQuery();

        } catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }
        return rs;

    }
    
    public ResultSet getCourse(String Title) {

        try {

            PreparedStatement pstmt;

            String query = "SELECT * FROM Courses WHERE Title=?";
            pstmt = connection.getConnection().prepareStatement(query);

            pstmt.setString(1, Title);

            rs = pstmt.executeQuery();

        }catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }
        return rs;
    }
    
    public ResultSet getCourseBooks(int CRN) {

        try {

            PreparedStatement pstmt;

            String query = "SELECT ISBN FROM Required_Rec_Books WHERE CRN=?";
            pstmt = connection.getConnection().prepareStatement(query);

            pstmt.setInt(1, CRN);

            rs = pstmt.executeQuery();

        }catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }
        return rs;
    }
    
    
    public ResultSet getCoursewithCRN(int CRN) {

        try {

            PreparedStatement pstmt;

            String query = "SELECT * FROM Courses WHERE CRN=?";
            pstmt = connection.getConnection().prepareStatement(query);

            pstmt.setInt(1, CRN);

            rs = pstmt.executeQuery();

        }catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }
        return rs;
    }
    
    public ResultSet getCourseBook(int CRN) {

        try {

            String query = "SELECT c.Title as 'Course', t.Title as 'Book', t.Price as Price,t.ISBN as ISBN, " + 
            "s.Status " + "FROM Courses c " + "JOIN Required_Rec_Books rr " + 
            "ON rr.CRN = c.CRN " + "JOIN Textbooks t ON t.ISBN = rr.ISBN " + 
            "JOIN Required_Status s ON s.Status_ID = rr.Status_ID " + 
            "JOIN Evaluations e ON e.ISBN = t.ISBN " + "JOIN Students stu " + 
            "ON stu.Student_ID = e.Student_ID " + "JOIN Authors a " + 
            "ON a.Auth_ID = t.Auth_ID " + "WHERE c.CRN LIKE " + String.valueOf(CRN);

            rs = connection.getStatement().executeQuery(query);
        } catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }
        return rs;
    }

    public void insertAuthor(String fName, String lName) {

        author author = new author(fName, lName);
        String query = "INSERT IGNORE INTO Authors (FName, LName) " + 
                "values (?, ?)";

        try {

            PreparedStatement pstmt;

            pstmt = connection.getConnection().prepareStatement(query);

            pstmt.setString(1, author.getFName());
            pstmt.setString(2, author.getLName());

            pstmt.execute();
        
        } catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }
    }

    public void insertTextbook(int ISBN, String Title, int Auth_ID, Float Price) {

        String query = "INSERT IGNORE INTO Textbooks (ISBN, Title, Auth_ID, Price) " + 
                "values (?, ?, ?, ?)";

        try {

            PreparedStatement pstmt;

            pstmt = connection.getConnection().prepareStatement(query);
            pstmt.setInt(1, ISBN);
            pstmt.setString(2, Title);
            pstmt.setInt(3, Auth_ID);
            pstmt.setFloat(4, Price);

            pstmt.execute();
        
        } catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }
    }

    public void insertEvaluation(int Student_ID, int ISBN, String Evaluation) {

        String query = "INSERT INTO Evaluations (Student_ID, ISBN, Evaluation) " + 
                "values (?, ?, ?)";

        try {

            PreparedStatement pstmt;

            pstmt = connection.getConnection().prepareStatement(query);
            pstmt.setInt(1, Student_ID);
            pstmt.setInt(2, ISBN);
            pstmt.setString(3, Evaluation);
            pstmt.execute();
        
        } catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }
    }

    public void insertStudent(int Student_ID, String fName, String lName) {

        String query = "INSERT INTO Students (Student_ID, FName, LName) " + 
                "values (?, ?, ?)";

        try {

            PreparedStatement pstmt;

            pstmt = connection.getConnection().prepareStatement(query);
            pstmt.setInt(1, Student_ID);
            pstmt.setString(2, fName);
            pstmt.setString(3, lName);
            pstmt.execute();
        
        } catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }

        
    }

    public void insertCourse(int CRN, String title) {

        String query = "INSERT INTO Courses (CRN, Title) " + 
                "values (?, ?)";

        try {

            PreparedStatement pstmt;

            pstmt = connection.getConnection().prepareStatement(query);
            pstmt.setInt(1, CRN);
            pstmt.setString(2, title);
            pstmt.execute();
        
        } catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }
    }

    public void insertRequiredRecBook(int ISBN, int CRN, int Status_ID) {

        String query = "INSERT INTO Required_Rec_Books (ISBN, CRN, Status_ID) " + 
                "values (?, ?, ?)";

        try {

            PreparedStatement pstmt;

            pstmt = connection.getConnection().prepareStatement(query);
            pstmt.setInt(1, ISBN);
            pstmt.setInt(2, CRN);
            pstmt.setInt(3, Status_ID);
            pstmt.execute();
        
        } catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }
    }

    public void insertRequiredRecBook(int ISBN, int CRN, String Status) {

        String query = "INSERT INTO Required_Rec_Books (ISBN, CRN, Status_ID) " + 
                "values (?, ?, ?)";

        try {

            PreparedStatement pstmt;

            pstmt = connection.getConnection().prepareStatement(query);
            pstmt.setInt(1, ISBN);
            pstmt.setInt(2, CRN);
            if(Status == "Required") {
                pstmt.setInt(3, 1);
            }
            else {
                pstmt.setInt(3, 2);
            }
            pstmt.execute();
        
        } catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }
    }

    public void deleteAuthor(String fName, String lName) {

        try {

            String query = "DELETE FROM Authors WHERE FName = ? AND LName = ?";

            PreparedStatement pstmt = connection.getConnection().prepareStatement(query);
            pstmt.setString(1, fName);
            pstmt.setString(2, lName);

            pstmt.executeUpdate();

        } catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }
    }

    public void deleteAuthor(int Auth_ID) {

        try {

            String query = "DELETE FROM Authors WHERE Auth_ID = ? ";

            PreparedStatement pstmt = connection.getConnection().prepareStatement(query);
            pstmt.setInt(1, Auth_ID);
            pstmt.executeUpdate();

        } catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }
    }

    public void deleteTextbook(int ISBN) {

        try {

            String query = "DELETE FROM Textbooks WHERE ISBN = ?";

            PreparedStatement pstmt = connection.getConnection().prepareStatement(query);
            pstmt.setInt(1, ISBN);
            pstmt.executeUpdate();

        } catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }
    }

    public void deleteEvaluation(int Student_ID, int ISBN) {

        try {

            String query = "DELETE FROM Evaluations WHERE Student_ID = ? AND ISBN = ?";

            PreparedStatement pstmt = connection.getConnection().prepareStatement(query);
            pstmt.setInt(1, Student_ID);
            pstmt.setInt(2, ISBN);

            pstmt.executeUpdate();

        } catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }
    }

    public void deleteStudent(int Student_ID) {

        try {

            String query = "DELETE FROM Students WHERE Student_ID = ?";

            PreparedStatement pstmt = connection.getConnection().prepareStatement(query);
            pstmt.setInt(1, Student_ID);
            pstmt.executeUpdate();

        } catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }
    }

    public void deleteCourse(int CRN) {

        try {

            String query = "DELETE FROM Courses WHERE CRN = ?";

            PreparedStatement pstmt = connection.getConnection().prepareStatement(query);
            pstmt.setInt(1, CRN);
            pstmt.executeUpdate();

        } catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }
    }

    
}



    
