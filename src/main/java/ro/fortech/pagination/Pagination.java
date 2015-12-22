package ro.fortech.pagination;

/**
 * Class used to set the number of elements per page.
 *
 */
public class Pagination {

	private int pageNumber;
	private int elemetsPerPage;
	
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getElemetsPerPage() {
		return elemetsPerPage;
	}
	public void setElemetsPerPage(int elemetsPerPage) {
		this.elemetsPerPage = elemetsPerPage;
	}
	
}
