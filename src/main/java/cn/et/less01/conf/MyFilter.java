package cn.et.less01.conf;  
  
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.et.less01.dao.UserMapper;
import cn.et.less01.entity.Menu;  
@Component  
public class MyFilter extends AuthorizationFilter {  
    
      
    @Autowired  
    private ShiroFilterFactoryBean sffb;  
    /**  
     * ƥ��ָ�������������url  
     * @param regex  
     * @param url  
     * @return  
     */  
    public static boolean matchUrl(String regex,String url){  
        regex=regex.replaceAll("/+", "/");  
        if(regex.equals(url)){  
            return true;  
        }  
        regex=regex.replaceAll("\\.", "\\\\.");  
        // /login.html  /l*.html  
        regex=regex.replaceAll("\\*", ".*");  
        // /**/login.html  /a/b/login.html  
        if(regex.indexOf("/.*.*/")>=0){  
            regex=regex.replaceAll("/\\.\\*\\.\\*/", "((/.*/)+|/)");  
        }  
        System.out.println(regex+"----"+url);  
        return Pattern.matches(regex, url);  
    }  
    
    @Autowired
    UserMapper um;
    /**  
     * ����  
     * @param args  
     */  
    public static void main(String[] args) {  
        System.out.println(matchUrl("/**/s*.html","/t/g/login.html"));  
    }  
    /**  
     * ��map��ģ�� ���Ҳ���Խ������������ݿ���  
     */  
   
    /**  
     * isAccessAllowed�����жϵ�ǰurl�������Ƿ�����֤ͨ��  �����֤ʧ�� ���ø����onAccessDenied������ת����¼ʧ��ҳ������Ȩʧ��ҳ��  
     */  
    @Override  
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)  
            throws Exception {  
    	
        HttpServletRequest req=(HttpServletRequest)request; 
        String contextPath=req.getContextPath();
  
        //��ȡ�û����ʵ���Դ��·��
        String url=req.getRequestURI();  
        url=url.split(contextPath)[1];
        //��ȡ���еĲ˵�
        List<Menu> qm= um.queryMenu();
      //���ݿ�û�����õ�ǰURL����Ȩ
        if(qm.size()==0){
        	return false;
        }
        String urlAuth =null;
      //��ȡ��ЩURL��Ҫ��Щ��֤
       // List<Menu> q=um.queryMenuByurl(url);
        for(Menu mu:qm){
        	 if(matchUrl(mu.getMenuurl(),url)){
             	urlAuth=mu.getMenufitter();
             }
        }
       
        
      
        //�������Ȩ�ͻ�ȡȨ������Щ
      
        if(urlAuth==null){
            return false;  
        }  
        //���õĹ�������anon ֱ�ӷŹ�  
        if(urlAuth.startsWith("anon")){
            return true;  
        }  
        //���õ���authc �жϵ�ǰ�û��Ƿ���֤ͨ��  
        Subject subject = getSubject(request, response);  
        if(urlAuth.startsWith("authc")){  
            return subject.isAuthenticated();  
        }  
        //��Ȩ��֤ Ҳ��Ҫ�ж��Ƿ��¼ û�е�¼���� ��¼�����������֤  
        boolean ifAuthc=subject.isAuthenticated();  
        if(!ifAuthc)  
            return ifAuthc;  
        //����Ƕ����roles������  ��ȡ���е�roles һ����roles[a,b]  
        if(urlAuth.startsWith("roles")){  
            String[] rolesArray=urlAuth.split("roles\\[")[1].split("\\]")[0].split(",");  
            if (rolesArray == null || rolesArray.length == 0) {  
                return true;  
            }  
            Set<String> roles = CollectionUtils.asSet(rolesArray);  
            return subject.hasAllRoles(roles);  
        }  
        if(urlAuth.startsWith("perms")){  
            String[] perms=urlAuth.split("perms\\[")[1].split("\\]")[0].split(",");  
            boolean isPermitted = true;  
            if (perms != null && perms.length > 0) {  
                if (perms.length == 1) {  
                    if (!subject.isPermitted(perms[0])) {  
                        isPermitted = false;  
                    }  
                } else {  
                    if (!subject.isPermittedAll(perms)) {  
                        isPermitted = false;  
                    }  
                }  
            }  
  
            return isPermitted;  
        }  
        return false;  
    }  
  
}  