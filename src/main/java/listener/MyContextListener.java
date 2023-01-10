package listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


public class MyContextListener implements ServletContextListener {

  
    public MyContextListener() {
        System.out.println("MyContextListener의 생성자");
    }
    //웹 어플리케이션이 시작되면 호출됨 즉 서블릿 컨텍스트가 생성될때..
    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("웹 어플리케이션이 시작되었습니다.");
    	System.out.println("서버 정보:"+sce.getServletContext().getServerInfo());
    
    	try {
    		Context initCtx = new InitialContext();
    		DataSource source = (DataSource)initCtx.lookup(sce.getServletContext().getInitParameter("JNDI-ROOT")+"/kosmo");
    		sce.getServletContext().setAttribute("DataSource", source);
    	}
    	catch(Exception e) {e.printStackTrace();}
    }
	
    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("웹 어플리케이션이 종료되었습니다.");
    }
	
}
