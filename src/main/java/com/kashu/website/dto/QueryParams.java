package com.kashu.website.dto;

import java.util.ArrayList;
import java.util.List;

//用來儲存用戶傳入的查詢參數
public class QueryParams {
     /*
      * 用戶傳入這樣的查詢參數 (JSON格式)，對應到此QueryParams類別
      * 
       {
           "filters" : [
              { "column" : "name" , "operator" : "like" , "value" : "日向"}
           ],
           "sorts" : [
              { "column" : "name" , "type" : "desc" }
           ],
           "pageNumber" : 1,
           "pageSize" : 10
       }
      */
	
     List<Filter> filters;
     List<Sort> sorts;
     int pageNumber = 1;  //頁碼
     int pageSize = 10;   //每頁顯示記錄筆數
     
     public QueryParams() {
    	 filters =  new ArrayList<>();
    	 sorts =  new ArrayList<>();
     }
     
     public QueryParams(List<Filter> filters , List<Sort> sorts) {
    	 this.filters = filters;
    	 this.sorts = sorts;
     }

	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	public List<Sort> getSorts() {
		return sorts;
	}

	public void setSorts(List<Sort> sorts) {
		this.sorts = sorts;
	}
    
	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/*
	 * note : it will return only last one Filter
	 *        if multiple filters with the same column name existed
	 *        probably a bug ... try to change return type to List<Filter> in someday future
	 */
	public Filter getFilterByColumn(String column) {
		Filter filter = new Filter("","","");
		for(Filter f : this.filters) {
			if(f.getColumn().equals(column)) {
				filter = f;
			}
		}
		return filter;
	}
	
	/*
	 * note : the same as above ...
	 */
	
	public Sort getSortByColumn(String column) {
		Sort sort = new Sort("","");
		for(Sort s : this.sorts) {
			if(s.getColumn().equals(column)) {
				sort = s;
			}
		}
		return sort;
	}
}
