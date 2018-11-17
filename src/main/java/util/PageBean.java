package util;

import java.util.List;

/**
 * @author Mr.Tong
 *	分页显示工具类
 * @param <T>	传入的泛型集合
 */
public class PageBean<T> {

	//当前页第几页，从请求那边传过来
	private int pageNum;

	//每页显示的数据条数
	private int pageSize;

	//总的记录条数
	private int totalRecord;

	//总页数
	private int totalPage;

	//limit开始索引
	private int startIndex;

	//分页显示的数据存放在list集合中

	private T object;

	private List<T> list;

	private int start;

	private int end;

	public PageBean(int pageNum,int pageSize,int totalRecord) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;

		//计算总页数
		if(totalRecord%pageSize == 0) {
			this.totalPage = totalRecord / pageSize;
		}else {
			this.totalPage = totalRecord / pageSize + 1;
		}

		//开始索引
		this.startIndex = (pageNum-1) * pageSize;
		this.start = 1;
		this.end = 5;

		//显示页码的算法
		//显示5页
		if(totalPage <= 5) {
			this.end = this.totalPage;
		}else {
			this.start = pageNum - 2;
			this.end = pageNum + 2;
			System.out.println(start);
			if(start < 0) {
				this.start = 1;
				this.end = 5;
			}
			if(end > this.totalPage) {
				this.end = totalPage;
				this.start = end-5;
			}
		}
	}


	public T getObject() {
		return object;
	}


	public void setObject(T object) {
		this.object = object;
	}


	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "PageBean{" +
				"pageNum=" + pageNum +
				", pageSize=" + pageSize +
				", totalRecord=" + totalRecord +
				", totalPage=" + totalPage +
				", startIndex=" + startIndex +
				", object=" + object +
				", list=" + list +
				", start=" + start +
				", end=" + end +
				'}';
	}
}
