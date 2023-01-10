package listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

@WebListener
public class MySessionAttributeListener implements HttpSessionAttributeListener {

   
    public MySessionAttributeListener() {
        System.out.println("MySessionAttributeListener의 생성자");
    }

    public void attributeReplaced(HttpSessionBindingEvent se)  { 
    	
    	System.out.println("세션의 속성 변경됨:"+se.getSession().getId());
    	System.out.println(String.format("변경된 속성명:%s,속성값:%s",se.getName(),se.getValue()));
    }

    public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	System.out.println("세션의 속성이 삭제됨:"+se.getSession().getId());
    	System.out.println(String.format("삭제된 속성명:%s,속성값:%s",se.getName(),se.getValue()));
    }

    public void attributeAdded(HttpSessionBindingEvent se)  { 
    	System.out.println("세션의 속성이 추가됨:"+se.getSession().getId());
    	System.out.println(String.format("추가된 속성명:%s,속성값:%s",se.getName(),se.getValue()));
    }
	
}
