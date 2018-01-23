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
	 * 获取当前用户的授权数据
	 * 将当前用户在数据库的权限加载到AuthorizationInfo
	 * 默认 在进行授权认证时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取用户名
		String userName=principals.getPrimaryPrincipal().toString();
		System.out.println(userName);
		//查看角色
		Set<String> roleList=um.queryRoleByName(userName);
		//查看权限
		Set<String> pemsList=um.queryPemsByName(userName);
		//角色和权限集合对象
		SimpleAuthorizationInfo at=new SimpleAuthorizationInfo();
		//设置用户的角色
		at.setRoles(roleList);
		//设置用户的权限
			at.setStringPermissions(pemsList);
		return at;
	}
	
	/**
	 * 认证
	 * 将登陆输入的用户名和密码和数据库中的用户名个密码对比是否相等
	 * 返回null认证失败 非null成功 
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取页面传进来的用户和密码
		 UsernamePasswordToken upt=(UsernamePasswordToken)token;
		 //查询数据库是否有这个账号
		 System.out.println(upt.getPassword());
		 System.out.println(token.getPrincipal());
		 //查询数据的用户和密码
		 UserInfo queryUs = um.queryUser(token.getPrincipal().toString());
		 //判断账号密码是否一致
		 if(queryUs!=null && queryUs.getPassword().equals(new String(upt.getPassword()))){
			 //登录成功
			 SimpleAccount sa = new  SimpleAccount(upt.getUsername(),upt.getPassword(),"MyDbRealm");
			 return sa;
		 }
		return null;
	}

}
