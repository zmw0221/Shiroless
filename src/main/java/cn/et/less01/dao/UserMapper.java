package cn.et.less01.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Select;

import cn.et.less01.entity.Menu;
import cn.et.less01.entity.UserInfo;

public interface UserMapper {
	
	//�鿴�û�
	@Select("select user_name as userName,pass_word as password from user_info where user_name=#{0}")
	public UserInfo queryUser(String userName);

	//�鿴��ɫ
	@Select("SELECT r.role_name FROM user_info u INNER JOIN user_role_relation urr ON u.user_id =urr.user_id  "
			+ " INNER JOIN role r ON r.role_id= urr.role_id "
			+ " WHERE user_name=#{0}")
	public Set<String> queryRoleByName(String userName);
	
	//�鿴Ȩ��
	@Select("SELECT p.pems_tag FROM user_info u INNER JOIN user_role_relation urr ON u.user_id =urr.user_id "
			+ " INNER JOIN role r ON r.role_id= urr.role_id "
			+ " INNER JOIN role_pems_relation rpr ON rpr.role_id=r.role_id "
			+ " INNER JOIN pems p ON rpr.pems_id=p.pems_id "
			+ " WHERE user_name=#{0}")
	public Set<String> queryPemsByName(String userName);
	
	//�鿴���еĲ˵�
	@Select("select menu_id as menuid,menu_name as menuname,menu_url as menuurl,menu_fitter as menufitter,is_menu as ismenu from menu")
	public List<Menu> queryMenu();
	
	//ͨ��·���鿴�Ƿ���Ȩ��
	@Select("select menu_id as menuid,menu_name as menuname,menu_url as menuurl,menu_fitter as menufitter,is_menu as ismenu from menu where menu_url=#{0} ")
	public List<Menu> queryMenuByurl(String url);
	
	//ͨ���û����û������Ĳ˵�
	@Select("SELECT menu_name as menuname,menu_url as menuurl,menu_fitter as menufitter,is_menu as ismenu FROM menu m INNER JOIN user_menu_relation umr ON"
			+ " umr.menu_id=m.menu_id "
			+ "INNER JOIN user_info u ON u.user_id = umr.user_id "
			+ "WHERE u.user_name =#{0} ")
	public List<Menu> queryMenuByMenu(String userName);
	
}
