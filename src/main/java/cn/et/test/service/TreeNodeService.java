package cn.et.test.service;

import java.util.List;

import cn.et.test.entity.TreeNode;
import cn.et.test.entity.cooking.Cooking;
import cn.et.test.utils.PageTools;

public interface TreeNodeService {
	
	public List<TreeNode> queryTreeNode(Integer id);
	
	public PageTools queryCook(Integer id,Integer page,Integer rows);
	
	public PageTools queryCookName(String fname, Integer page, Integer rows);
	
	public void deleteCook(Integer fid);
	
	public List<Cooking> queryCooking(Integer id);
	
}
