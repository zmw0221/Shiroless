package cn.et.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.et.test.dao.CookMapper;
import cn.et.test.entity.Result;
import cn.et.test.entity.TreeNode;
import cn.et.test.entity.cooking.Cooking;
import cn.et.test.service.TreeNodeService;
import cn.et.test.utils.PageTools;


@RestController
public class SbController {
	
	@Autowired
	CookMapper cm;
	@Autowired
	TreeNodeService ts;
	
	@RequestMapping("/qcook")
	public PageTools queryCook(Integer id,String fname,Integer page,Integer rows){
		if(fname!=null){
			return ts.queryCookName(fname, page, rows);
		}
		return ts.queryCook(id, page, rows);
	}
	//�鿴��������
	@RequestMapping("/Cooking")
	public List<Cooking> queryCooking(Integer id){
		
		return ts.queryCooking(id);
	}
	
	@RequestMapping("/queryCookIng")
	public List<TreeNode> queryTreeNode(Integer id){
			if(id==null){
				id=0;
			}
			return ts.queryTreeNode(id);
	}
	
	@RequestMapping(value="/Cookdelete/{fid}")
	public Result deleteFood(@PathVariable Integer fid,Integer page,Integer rows) {
		Result r= new Result();
		r.setCode(1);
		try{
			ts.deleteCook(fid);
		}catch(Exception e){
			r.setCode(0);
			r.setMessaga(e.getMessage());
		}
		return r;
		
	}
}