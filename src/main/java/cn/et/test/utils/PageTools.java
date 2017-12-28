package cn.et.test.utils;

import java.util.List;

public class PageTools {
	/*
	 * @param curPage页面传入的当前页
	 *  @param totalCount  数据库查询的总记录数
	 *   @param pageCount	每页显示的条数
	 */
	public PageTools(Integer curPage,Integer total,Integer pageCount){
		this.curPage=curPage;
		this.total=total;
		this.pageCount=pageCount==null?this.pageCount:pageCount;
		this.prePage=(curPage==1?1:curPage-1);
		this.totalPage=(total%this.pageCount==0)?total/this.pageCount:total/this.pageCount+1;
		this.nextPage=(curPage==totalPage)?totalPage:(curPage+1);
		this.startIndex=(curPage-1)*this.pageCount+1;
		this.endIndex=curPage*this.pageCount;
	
	}
	
	private Integer curPage;
	/*
	 * 当前页(动态由页面传递)
	 */
	
	private Integer prePage;
	/*
	 * 上一页prePage=(curPage==1?1:curPage-1)
	 */
	private Integer nextPage;
	/*
	 * 下一页nextPage=(curPage==totalPage)?totalPage:(curPage+1)
	 */
	private Integer pageCount=10;
	/*
	 * 每页显示的条数
	 */
	 
	private Integer totalPage;
	/*
	 * 总页数totalPage=(totalCount%pageCount==0)?totalCount/pageCount:totalCount/pageCount+1
	 */
	private Integer total;
	/*
	 * 总数据
	 */
	private List rows;
	/*
	 * 存储最终查询的数据
	 */
	private Integer startIndex;
	/*
	 * 开始索引(curPage-1)*pageCount+1
	 */
	private Integer endIndex;
	/*结束条数
	 * curPage*pageCount
	 */
	

	public static void main(String[] args) {
		int curpage=2;
		int totalCount=26;
		int pageCount=5;
		PageTools pt= new PageTools(curpage,totalCount,pageCount);
		System.out.println(pt.getNextPage());
		System.out.println(pt.getTotalPage());
		System.out.println(pt.getPrePage());
		System.out.println(pt.getStartIndex());
		System.out.println(pt.getEndIndex());
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getPrePage() {
		return prePage;
	}

	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
}
