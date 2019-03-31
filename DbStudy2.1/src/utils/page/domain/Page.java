package utils.page.domain;

import java.util.List;

public class Page {
    private int totalPage;
    private int currentPage;
    private int pageSize = 10;
    private int dataSize;
    private List<?> list;

    public int getTotalPage() {
        totalPage = dataSize/pageSize;
        return dataSize%pageSize!=0?++totalPage : totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getDataSize() {
        return dataSize;
    }

    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
