import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class mainPage extends JFrame implements ActionListener{
	welcomePage p3;
	searchPage p;
	loginPage p1;
	bookPage p2;
	
	JButton searchButton=new JButton("Search");
	JButton searchButton2=new JButton("Search");
	JButton loginButton=new JButton("Log In/Register");
	JTextField searchInput1=new JTextField("Book Title...",20);
	JTextField searchInput2=new JTextField("Course...",20);
	Font font=new Font("Arial",Font.BOLD,15);
	Font font1=new Font("Arial",Font.BOLD,20);
	Font font2=new Font("Arial",Font.PLAIN,12);
	Font font3=new Font("Arial",Font.BOLD,30);
	Font font4=new Font("Arial",Font.PLAIN,20);
	Font font5=new Font("Arial",Font.BOLD,20);
	Font font6=new Font("Arial",Font.PLAIN,15);
	Font font7=new Font("Arial",Font.BOLD,20);
	
	JButton viewButton1=new JButton("View");
	JButton viewButton2=new JButton("View");
	JButton viewButton3=new JButton("View");
	JButton viewButton4=new JButton("View");
	JButton logIn=new JButton("Log In");
	JButton registrate=new JButton("Register");
	JButton sumbitReview=new JButton("Submit Review");
	JTextField fnameInput=new JTextField(20);
	JTextField lnameInput=new JTextField(20);
	JTextField stuIdInput=new JTextField(20);
	JLabel Icon=new JLabel("SLU");
	JLabel Icon2=new JLabel("Library");
	JTextArea reviewInput;
	Database database = new Database();
	int[] isbns=new int[] {0};
	evaluation evalu=new evaluation();
	book b=new book();
	author aut=new author();
	evaluation eva=new evaluation();
	evaluation eva2=new evaluation();
	evaluation eva3=new evaluation();
	student loggedStu=new student();
	student stu=new student();
	student stu1=new student();
	student stu2=new student();
	course cour=new course();
	course reqCour=new course();
	course recCour=new course();
	requiredRecBook req=new requiredRecBook();
	boolean logged=false;
	int numReviews=0;


	mainPage(){
		
		EstablishConnection connection;
        DatabaseMetaData dbm;
        ResultSet tables;

        connection = new EstablishConnection();


        try {

            dbm = connection.getConnection().getMetaData();
            tables = dbm.getTables(null, null, "Authors", null);
            if(tables.next()) {

            }
            else {

                InitializeDatabase in = new InitializeDatabase();
                in.createTables();
                in.populateTables();

            }

        } catch(Exception e) {

            System.out.println(e);
            System.exit(1);
        }

        
        /*
        DBTablePrinter.printResultSet(database.getAuthors());
        DBTablePrinter.printResultSet(database.getCourses());
        DBTablePrinter.printResultSet(database.getEvaluations());
        DBTablePrinter.printResultSet(database.getStudents());
        DBTablePrinter.printResultSet(database.getTextbooks());
        DBTablePrinter.printResultSet(database.getRequiredRecBooks());
        DBTablePrinter.printResultSet(database.getRequiredStatus());

        DBTablePrinter.printResultSet(database.getCourseBookStatus());
        */

        DBTablePrinter.printResultSet(database.getDetailsAboutBook(7379));
        DBTablePrinter.printResultSet(database.getCourseBook(67890));
        DBTablePrinter.printResultSet(database.getTextbookInfo(7379));

		
		p=new searchPage();
		p1=new loginPage();
		p2=new bookPage();
		p3=new welcomePage();
		searchInput1.setText("Book Title...");
		searchInput2.setText("Course...");
		header();
		welcome();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(p3);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object click=e.getSource();
		if(click==searchButton) {
			numReviews=0;
			this.getContentPane().removeAll();
			clearCourse(reqCour,recCour);
			header();
			p.setNumBooks(0);
			searchPage(0);
			this.repaint();
			this.setVisible(true);
			int pos=0;
			ResultSet ISBNs=database.getISBN(searchInput1.getText());
				try {
					while(ISBNs.next())
						{pos++;	
							searchPage(pos);
							int tisbn=ISBNs.getInt("ISBN");
							ResultSet Book=database.getTextbookInfo(tisbn);
							ResultSet s0=database.getReqRec(tisbn);
							setRequiredRecBook(s0,req);
							ResultSet s=database.getReqRec(tisbn);
							while(s.next()) {
								
								if(s.getInt("Status_ID")==1) {
									ResultSet c=database.getCoursewithCRN(Integer.parseInt(s.getString("CRN")));
									setClassCourse(c,reqCour);
								}
								else if (s.getInt("Status_ID")==2) {
									ResultSet c=database.getCoursewithCRN(s.getInt("CRN"));
									setClassCourse(c,recCour);
								}
							}
							setClassBook(Book,b);
							
							searchedBooks();
							
							
							}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.print("error search");
				}
				p.setNumBooks(pos);
			
		}
		else if (click==searchButton2) {
			this.getContentPane().removeAll();
			header();
			p.setNumBooks(0);
			searchPage(0);
			this.repaint();
			this.setVisible(true);
			int pos=0;
			ResultSet Courses=database.getCourse(searchInput2.getText());
			try {
				setClassCourse(Courses,cour);
				//System.out.print(cour.getCRN());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//System.out.print(cour.getCRN());
			ResultSet Books=database.getCourseBooks(Integer.parseInt(cour.getCRN()));
			
			try {
				while(Books.next())
					{pos++;
						
						//System.out.print(Books.getInt("ISBN"));
						ResultSet Book=database.getTextbookInfo(Books.getInt("ISBN"));
						setClassBook(Book,b);
						ResultSet s=database.getReqRec(Integer.parseInt(b.getISBN()));
						while(s.next()) {
							searchPage(pos);
							if(s.getInt("Status_ID")==1) {
								ResultSet c=database.getCoursewithCRN(Integer.parseInt(s.getString("CRN")));
								setClassCourse(c,reqCour);
							}
							else if (s.getInt("Status_ID")==2) {
								ResultSet c=database.getCoursewithCRN(s.getInt("CRN"));
								setClassCourse(c,recCour);
							}
							
						}
						
						searchedBooks();
						
							}
			} catch (SQLException e1) {
				 //TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.print("error search");
			}
			p.setNumBooks(pos);
			
			
		}
		else if (click==loginButton) {
			this.getContentPane().removeAll();
			header();
			loginPage();
			
			this.repaint();
			this.setVisible(true);
		}
		else if(click==viewButton1) {
			numReviews=0;
			clearBookReview(eva,eva2,eva3);
			clearStudents(stu,stu1,stu2);
			this.getContentPane().removeAll();
			header();
			
			try {
				ResultSet eval=database.getEvaluation(b.getISBN());
				
				setClassEvaluations(eval,eva,eva2,eva3);
				ResultSet eval2=database.getEvaluation(b.getISBN());
				while(eval2.next()) {
					if(numReviews==0) {
					ResultSet stud=database.getStudent(eval2.getString("Student_ID"));
					setClassStudent(stud,stu);
					}else if(numReviews==1){
					ResultSet stud=database.getStudent(eval2.getString("Student_ID"));
					setClassStudent(stud,stu1);
					}else if(numReviews==2){
					ResultSet stud=database.getStudent(eval2.getString("Student_ID"));
					setClassStudent(stud,stu2);
					}
					numReviews++;
				}
				bookPage();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.repaint();
			this.setVisible(true);
			
			
		}
		else if(click==viewButton2) {
			numReviews=0;
			clearBookReview(eva,eva2,eva3);
			clearStudents(stu,stu1,stu2);
			this.getContentPane().removeAll();
			header();
			
			try {
				ResultSet eval=database.getEvaluation(b.getISBN());
				
				setClassEvaluations(eval,eva,eva2,eva3);
				ResultSet eval2=database.getEvaluation(b.getISBN());
				while(eval2.next()) {
					if(numReviews==0) {
					ResultSet stud=database.getStudent(eval2.getString("Student_ID"));
					setClassStudent(stud,stu);
					}else if(numReviews==1){
					ResultSet stud=database.getStudent(eval2.getString("Student_ID"));
					setClassStudent(stud,stu1);
					}else if(numReviews==2){
					ResultSet stud=database.getStudent(eval2.getString("Student_ID"));
					setClassStudent(stud,stu2);
					}
					numReviews++;
				}
				bookPage();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.repaint();
			this.setVisible(true);
			
		}
		else if(click==viewButton3) {
			numReviews=0;
			clearBookReview(eva,eva2,eva3);
			clearStudents(stu,stu1,stu2);
			this.getContentPane().removeAll();
			header();
			
			try {
				ResultSet eval=database.getEvaluation(b.getISBN());
				
				setClassEvaluations(eval,eva,eva2,eva3);
				ResultSet eval2=database.getEvaluation(b.getISBN());
				while(eval2.next()) {
					if(numReviews==0) {
					ResultSet stud=database.getStudent(eval2.getString("Student_ID"));
					setClassStudent(stud,stu);
					}else if(numReviews==1){
					ResultSet stud=database.getStudent(eval2.getString("Student_ID"));
					setClassStudent(stud,stu1);
					}else if(numReviews==2){
					ResultSet stud=database.getStudent(eval2.getString("Student_ID"));
					setClassStudent(stud,stu2);
					}
					numReviews++;
				}
				bookPage();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.repaint();
			this.setVisible(true);
			
		}
		else if(click==viewButton4) {
			numReviews=0;
			clearBookReview(eva,eva2,eva3);
			clearStudents(stu,stu1,stu2);
			this.getContentPane().removeAll();
			header();
			
			try {
				ResultSet eval=database.getEvaluation(b.getISBN());
				
				setClassEvaluations(eval,eva,eva2,eva3);
				ResultSet eval2=database.getEvaluation(b.getISBN());
				while(eval2.next()) {
					if(numReviews==0) {
					ResultSet stud=database.getStudent(eval2.getString("Student_ID"));
					setClassStudent(stud,stu);
					}else if(numReviews==1){
					ResultSet stud=database.getStudent(eval2.getString("Student_ID"));
					setClassStudent(stud,stu1);
					}else if(numReviews==2){
					ResultSet stud=database.getStudent(eval2.getString("Student_ID"));
					setClassStudent(stud,stu2);
					}
					numReviews++;
				}
				bookPage();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.repaint();
			this.setVisible(true);
			
			
		}
		else if(click==logIn) {
			
			ResultSet Studente=database.getStudent(stuIdInput.getText());
			try {
				
				setClassStudent(Studente,loggedStu);
				if(loggedStu.getStudentID()>0) {
					System.out.print(true);
					logged=true;
					//this.getContentPane().removeAll();
					//header();
				}
				else {
					System.out.print(false);
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}	
		}
		else if(click==registrate) {
			stu.set(stuIdInput.getText(), 0);
			stu.set(fnameInput.getText(), 1);
			stu.set(lnameInput.getText(), 2);
			database.insertStudent(stu.getStudentID(), fnameInput.getText(), lnameInput.getText());
			
		}
		else if(click==sumbitReview) {
		
			database.insertEvaluation(loggedStu.getStudentID(), Integer.parseInt(b.getISBN()),reviewInput.getText() ) ;
			
		}
		
	}
	
	
	
	public void header() {
		//Setting Header
		//Setting Search Button
		
		Icon.setFont(font3);
		Icon.setBounds(25, 5, 250, 50);
		Icon.setForeground(Color.white);
		add(Icon);
		Icon2.setFont(font);
		Icon2.setBounds(30, 25, 250, 50);
		Icon2.setForeground(Color.white);
		add(Icon2);
		
		add(searchButton);
		searchButton.setBounds(290,17,80,40);
		searchButton.addActionListener(this);
		add(searchButton2);
		searchButton2.setBounds(520,17,80,40);
		searchButton2.addActionListener(this);
		//Setting Log In Button
		if(logged==true) {
			JLabel t0=new JLabel(loggedStu.getFName()+" "+loggedStu.getLName());
			t0.setFont(font7);
			t0.setForeground(Color.white);
			t0.setBounds(630, 27, 140, 20);
			add(t0);
		}else {
		add(loginButton);
		loginButton.setBounds(630,17,140,40);
		loginButton.addActionListener(this);}
		//Setting Search Box 4
		searchInput1.setBounds(120,17,160,40);
		add(searchInput1);
		//Search Box 2
		searchInput2.setBounds(380,17,130,40);
		add(searchInput2);
	}
	public void searchedBooks() throws SQLException {
		
			//Set Book Title
			JLabel jltitle=new JLabel(b.getTitle());
			jltitle.setFont(font1);
			jltitle.setBounds(150, 105, 250, 20);
			add(jltitle);
			//Set Book Authors
			ResultSet Authors=database.getAuthorName(b.getAuthID());
			setClassAuthor(Authors,aut,b);
			JLabel jlauthors=new JLabel(aut.getFName()+aut.getLName());
			jlauthors.setFont(font6);
			jlauthors.setBounds(150, 125, 250, 20);
			add(jlauthors);
			//Set Book Courses
			JLabel jlbookCourses=new JLabel(recCour.getTitle()+" "+reqCour.getTitle());
			jlbookCourses.setFont(font6);
			jlbookCourses.setBounds(150, 145, 250, 20);
			add(jlbookCourses);
			//Set Book Price
			JLabel jltbookPrice=new JLabel("Price:");
			jltbookPrice.setFont(font6);
			jltbookPrice.setBounds(450, 110, 250, 20);
			add(jltbookPrice);
		
			JLabel jlbookPrice=new JLabel(b.getPrice()+" $");
			jlbookPrice.setFont(font6);
			jlbookPrice.setBounds(450, 140, 250, 20);
			add(jlbookPrice);
			//Set Number of reviews
			JLabel jltnumReviews=new JLabel("Number of");
			jltnumReviews.setFont(font6);
			jltnumReviews.setBounds(360, 110, 250, 20);
			add(jltnumReviews);
			JLabel jltnumReviews2=new JLabel("reviews:");
			jltnumReviews2.setFont(font6);
			jltnumReviews2.setBounds(360, 120, 250, 20);
			add(jltnumReviews2);
		
			JLabel jlnumReviews=new JLabel(String.valueOf(numReviews));
			jlnumReviews.setFont(font6);
			jlnumReviews.setBounds(360, 140, 250, 20);
			add(jlnumReviews);
			
			add(p);
		}
		
	public void welcome() {
		JLabel pag=new JLabel("Welcome to the Text Book Database Manager");
		pag.setFont(font5);
		pag.setBounds(180, 120, 450, 30);
		add(pag);
		add(p);
		JLabel tex=new JLabel("Welcome to the Text Book Database Manager");
		tex.setFont(font5);
		pag.setBounds(180, 120, 450, 30);
		add(pag);
		
		JTextArea textArea = new JTextArea(
			    "This is an Application for all SLU students to look " +
			    "up books. The application will disntiguish between  " +
			    "books needed and books suggested for a course " +
			    "It will also give important information such as the "+
			    "the price and the author(s). Students will be able to "+
			    "review the books and see the reviews of other students."+
			    "This is an Extension of the SLU library."
			    
			);
			textArea.setFont(font6);
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			textArea.setBounds(180, 160, 400, 200);
			//textArea.setBackground(Color.);
			add(textArea);
		
		add(p);
	}
	public void searchPage(int pos) {
		//Setting page content
				
	
					//Set View Button
					if(pos==0) {}
					else if (pos==1) {
						
						viewButton1.setBounds(530,115,120,40);
						viewButton1.addActionListener(this);
						add(viewButton1);}
					else if(pos==2) {
						viewButton2.setBounds(530,215,120,40);
						viewButton2.addActionListener(this);
						add(viewButton2);}
					else if (pos==3) {
						viewButton3.setBounds(530,315,120,40);
						viewButton3.addActionListener(this);
						add(viewButton3);}
					else if(pos==4) {
						viewButton4.setBounds(530,415,120,40);
						viewButton4.addActionListener(this);
						add(viewButton4);}
					add(p);
				
	}
	public void bookPage() throws SQLException {
		
		//Setting page content
		//Setting book info
		JLabel t0=new JLabel("Title: ");
		t0.setFont(font);
		t0.setBounds(150, 110, 250, 20);
		add(t0);
		JLabel bookTitle=new JLabel(b.getTitle());
		bookTitle.setFont(font6);
		bookTitle.setBounds(190, 110, 250, 20);
		add(bookTitle);
		//Set Authors
		JLabel t1=new JLabel("Author: ");
		t1.setFont(font);
		t1.setBounds(150, 140, 250, 20);
		add(t1);
		JLabel bookAuthors=new JLabel(aut.getFName()+aut.getLName());
		bookAuthors.setFont(font6);
		bookAuthors.setBounds(210, 140, 250, 20);
		add(bookAuthors);
		//Set Price
		JLabel t2=new JLabel("Required for: ");
		t2.setFont(font);
		t2.setBounds(150, 170, 250, 20);
		add(t2);
		JLabel bookCoursed=new JLabel(reqCour.getTitle());
		bookCoursed.setFont(font6);
		bookCoursed.setBounds(250, 170, 250, 20);
		add(bookCoursed);
		//Set Required Course
		JLabel t6=new JLabel("Recomended for: ");
		t6.setFont(font);
		t6.setBounds(150, 200, 250, 20);
		add(t6);
		JLabel requi=new JLabel(recCour.getTitle());
		requi.setFont(font6);
		requi.setBounds(280, 200, 250, 20);
		add(requi);
		//Set Recomended Course
		JLabel t3=new JLabel("Price of Book: ");
		t3.setFont(font);
		t3.setBounds(400, 110, 250, 20);
		add(t3);
		JLabel recomend=new JLabel(b.getPrice()+" $");
		recomend.setFont(font6);
		recomend.setBounds(510, 110, 250, 20);
		add(recomend);
		//Set Recomended Course
		JLabel t4=new JLabel("Number of Reviews: ");
		t4.setFont(font);
		t4.setBounds(400, 140, 250, 20);
		add(t4);
		JLabel recomend1=new JLabel(String.valueOf(numReviews));
		recomend1.setFont(font6);
		recomend1.setBounds(550, 140, 250, 20);
		add(recomend1);
		add(p2);
		
		
		//Setting Students Review
		JLabel R= new JLabel("Reviews of the book");
		R.setFont(font);
		R.setBounds(150, 255, 200, 30);
		//Show Reviews
		
		
		JLabel student=new JLabel(stu.getFName()+" "+stu.getLName());
		student.setFont(font6);
		student.setBounds(160, 300,90,30);
		add(student);
		JLabel student2=new JLabel(stu1.getFName()+" "+stu1.getLName());
		student2.setFont(font6);
		student2.setBounds(160, 350,90,30);
		add(student2);
		JLabel student3=new JLabel(stu2.getFName()+" "+stu2.getLName());
		student3.setFont(font6);
		student3.setBounds(160, 400,90,30);
		add(student3);
		
		JLabel evaluat=new JLabel(eva.getEvaluation());
		evaluat.setFont(font6);
		evaluat.setBounds(270, 300,200,30);
		add(evaluat);
		JLabel evaluat2=new JLabel(eva2.getEvaluation());
		evaluat2.setFont(font6);
		evaluat2.setBounds(270, 350,200,30);
		add(evaluat2);
		JLabel evaluat3=new JLabel(eva3.getEvaluation());
		evaluat3.setFont(font6);
		evaluat3.setBounds(270, 400,200,30);
		add(evaluat3);
		
		//Input Review
		JLabel makeR=new JLabel("Make a Review");
		makeR.setFont(font);
		makeR.setBounds(150, 445,200,30);
		add(makeR);
		reviewInput=new JTextArea();
		reviewInput.setBounds(150,470,500,50);;
		add(reviewInput);
		sumbitReview.setBounds(500,530,150,40);
		sumbitReview.addActionListener(this);
		
		//reviewInput.setBounds(360,17,160,40);
		
		//add(reviewInput);
		add(sumbitReview);
		add(R);
		add(p2);
		
				
	}
	public void loginPage() {
		
		JLabel loginTitle=new JLabel("Library Log In");
		loginTitle.setFont(font1);
		loginTitle.setBounds(350, 170, 200, 30);
		add(loginTitle);
		JLabel fName=new JLabel("First Name: ");
		fName.setFont(font1);
		fName.setBounds(180, 225, 200, 30);
		add(fName);
		JLabel lName=new JLabel("Last Name: ");
		lName.setFont(font1);
		lName.setBounds(180, 275, 200, 30);
		add(lName);
		JLabel stuID=new JLabel("Student ID: ");
		stuID.setFont(font1);
		stuID.setBounds(180, 325, 200, 30);
		add(stuID);
		
		logIn.setBounds(530,275,120,40);
		logIn.addActionListener(this);
		add(logIn);
		
		registrate.setBounds(530,325,120,40);
		registrate.addActionListener(this);
		//Box to get the email from user
		fnameInput.setBounds(300,225,210,40);
		add(fnameInput);
		//Box to get the email from user
		lnameInput.setBounds(300,275,210,40);
		add(lnameInput);
		//Box to get the password from user
		stuIdInput.setBounds(300,325,210,40);
		add(stuIdInput);
		
		add(registrate);
		
		add(p1);
	}
	

	public void setClassStudent(ResultSet rs,student st) throws SQLException {
		
		while(rs.next()) {
			
			st.set(rs.getString("Student_ID"), 0);
			st.set(rs.getString("FName"), 1);
			st.set(rs.getString("LName"), 2);
		}	
		
	}
	public void setClassEvaluation(ResultSet rs,evaluation e0) throws SQLException {
		while(rs.next()) {
			e0.set(rs.getString("Student_ID"), 0);
			e0.set(rs.getString("ISBN"), 1);
			e0.set(rs.getString("Evaluation"), 2);
		}
	}
	public void setClassEvaluations(ResultSet rs,evaluation e1,evaluation e2,evaluation e3) throws SQLException {
		int i=0;
		while(rs.next()) {
			if(i==0) {
				e1.set(rs.getString("Student_ID"), 0);
				e1.set(rs.getString("ISBN"), 1);
				e1.set(rs.getString("Evaluation"), 2);
			}else if(i==1) {
				e2.set(rs.getString("Student_ID"), 0);
				e2.set(rs.getString("ISBN"), 1);
				e2.set(rs.getString("Evaluation"), 2);
			}else if(i==2) {
				e3.set(rs.getString("Student_ID"), 0);
				e3.set(rs.getString("ISBN"), 1);
				e3.set(rs.getString("Evaluation"), 2);
			}
			i++;
		}
	}
	public void setClassBook(ResultSet rs,book b0) throws SQLException {
		while(rs.next())
		{
			//System.out.print(rs.getString("ISBN"));
			b0.set(rs.getString("ISBN"),0);
			b0.set(rs.getString("Title"),1);
			b0.set(rs.getString("Auth_ID"),2);
			b0.set(rs.getString("Price"),3);
		}
	}
	
	public void setClassCourse(ResultSet rs,course b0) throws SQLException {
		while(rs.next())
		{
			
			b0.set(rs.getString("CRN"),0);
			b0.set(rs.getString("Title"),1);
			
		}
	}
	public void setClassAuthor(ResultSet rs,author a0,book b0) throws SQLException {
		while(rs.next())
		{
			a0.set(rs.getString("FName"),0);
			a0.set(rs.getString("LName"),1);
			a0.setAuthID(b0.getAuthID());
		}
	}

	public void setRequiredRecBook(ResultSet rs,requiredRecBook a0) throws SQLException {
		while(rs.next())
		{
			a0.set(rs.getString("ISBN"),0);
			a0.set(rs.getString("CRN"),1);
			a0.set(rs.getString("Status_ID"),2);
		}
	}
	public void clearCourse(course b1,course b2) {
		
		b1.set("",0);
		b1.set("",1);
		b2.set("",0);
		b2.set("",1);
		
	}

	public void clearBookReview(evaluation e0,evaluation e1,evaluation e2) {
		
		e0.set(null, 0);
		e0.set("", 1);
		e0.set("", 2);
		e1.set(null, 0);
		e1.set("", 1);
		e1.set("", 2);
		e2.set(null, 0);
		e2.set("", 1);
		e2.set("", 2);
		
	}
	public void clearStudents(student e0,student e1,student e2) {
		
		e0.set(null, 0);
		e0.set("", 1);
		e0.set("", 2);
		e1.set(null, 0);
		e1.set("", 1);
		e1.set("", 2);
		e2.set(null, 0);
		e2.set("", 1);
		e2.set("", 2);
		
	}}
	

