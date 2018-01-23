package cn.et.less01.conf;
import java.util.Set;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.et.less01.dao.UserMapper;
import cn.et.less01.entity.UserInfo;

@Component
public class MyDbRealm extends AuthorizingRealm{
	
	@Autowired
	UserMapper um;
	
	/**
	 * ��ȡ��ǰ�û�����Ȩ����
	 * ����ǰ�û������ݿ��Ȩ�޼��ص�AuthorizationInfo
	 * Ĭ�� �ڽ�����Ȩ��֤ʱ����
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//��ȡ�û���
		String userName=principals.getPrimaryPrincipal().toString();
		System.out.println(userName);
		//�鿴��ɫ
		Set<String> roleList=um.queryRoleByName(userName);
		//�鿴Ȩ��
		Set<String> pemsList=um.queryPemsByName(userName);
		//��ɫ��Ȩ�޼��϶���
		SimpleAuthorizationInfo at=new SimpleAuthorizationInfo();
		//�����û��Ľ�ɫ
		at.setRoles(roleList);
		//�����û���Ȩ��
			at.setStringPermissions(pemsList);
		return at;
	}
	
	/**
	 * ��֤
	 * ����½������û�������������ݿ��е��û���������Ա��Ƿ����
	 * ����null��֤ʧ�� ��null�ɹ� 
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//��ȡҳ�洫�������û�������
		 UsernamePasswordToken upt=(UsernamePasswordToken)token;
		 //��ѯ���ݿ��Ƿ�������˺�
		 System.out.println(upt.getPassword());
		 System.out.println(token.getPrincipal());
		 //��ѯ���ݵ��û�������
		 UserInfo queryUs = um.queryUser(token.getPrincipal().toString());
		 //�ж��˺������Ƿ�һ��
		 if(queryUs!=null && queryUs.getPassword().equals(new String(upt.getPassword()))){
			 //��¼�ɹ�
			 SimpleAccount sa = new  SimpleAccount(upt.getUsername(),upt.getPassword(),"MyDbRealm");
			 return sa;
		 }
		return null;
	}

}
