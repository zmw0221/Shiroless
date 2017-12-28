package cn.et.test.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.test.dao.BibiMapper;
import cn.et.test.dao.CookMapper;
import cn.et.test.dao.CookingMapper;
import cn.et.test.entity.Bibi;
import cn.et.test.entity.BibiExample;
import cn.et.test.entity.TreeNode;
import cn.et.test.entity.cook.Cook;
import cn.et.test.entity.cook.CookExample;
import cn.et.test.entity.cooking.Cooking;
import cn.et.test.entity.cooking.CookingExample;
import cn.et.test.service.TreeNodeService;
import cn.et.test.utils.PageTools;

@Service
public class TreeNodeServiceImpl implements TreeNodeService{
	
	@Autowired
	CookingMapper cm;
	@Autowired
	CookMapper ck;
	@Autowired
	BibiMapper bm;
	
	public List<TreeNode> queryTreeNode(Integer id){
		List<TreeNode> deptlist= new ArrayList<TreeNode>();
		
		BibiExample be= new BibiExample();
		be.createCriteria().andLidEqualTo(id);
		List<Bibi> bi=bm.selectByExample(be);
		for(Bibi b:bi){
			TreeNode td= new TreeNode();
			td.setId(b.getBid());
			td.setText(b.getBname());
			if(queryTreeNode(b.getBid()).size()==0){
				td.setState("open");
			}
			deptlist.add(td);
		}
		
		//发起sql语句查询总记录数
		CookingExample ce = new CookingExample();
		ce.createCriteria().andKidEqualTo(id);
		List<Cooking> ck= cm.selectByExample(ce);
		
		for(Cooking c:ck){
			TreeNode tn= new TreeNode();
			tn.setId(c.getCid());
			tn.setText(c.getCname());
			
			//判断当前节点是否还存在子节点
			if(queryTreeNode(c.getCid()).size()==0){
				tn.setState("open");
			}
			deptlist.add(tn);
		}
		return deptlist;
	}
	//根据传入的ID查询
	public PageTools queryCook(Integer id,Integer page,Integer rows){
		CookExample ee= new CookExample();
		//如果传入ID不为空就添加条件cid=id。没有就查所有
		if(id!=null)
			ee.createCriteria().andCidEqualTo(id);
		
		int total=queryFgoCount(ee);
		//limit开始位置 总记录数
		PageTools pt= new PageTools(page, total, rows);
		RowBounds rb= new RowBounds(pt.getStartIndex()-1,rows);
		//根据id查看数据
		List<Cook> fl= ck.selectByExampleWithRowbounds(ee, rb);
		pt.setRows(fl);
		return pt;
	}
	
	public PageTools queryCookName(String fname, Integer page, Integer rows) {
			try {
				fname=new String(fname.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if(fname==null){
				fname="";
			}
			//发起sql语句查询总记录数
			CookExample ee= new CookExample();
			ee.createCriteria().andFnameLike("%"+fname+"%");
			int total=queryFgoCount(ee);
			//limit开始位置 总记录数
			PageTools pt= new PageTools(page, total, rows);
			RowBounds rb= new RowBounds(pt.getStartIndex()-1,rows);
			//根据吗名称查看数据
			List<Cook> fl= ck.selectByExampleWithRowbounds(ee, rb);
			pt.setRows(fl);
			return pt;
		
	}
	
	
	public int queryFgoCount(CookExample fe){
		int total=(int)ck.countByExample(fe);
		return total;
	}
	
	public void deleteCook(Integer fid){
		ck.deleteByPrimaryKey(fid);
	}
	
	public List<Cooking> queryCooking(Integer id){
		CookingExample ce = new CookingExample();
		if(id==null){
			id=1;
		}
		ce.createCriteria().andKidEqualTo(id);
		List<Cooking> ck= cm.selectByExample(ce);
		return  ck;
		
	}
	
	
}
