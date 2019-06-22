package io.fulu.couponshop.pagination;

public class PaginationResponse {
    private PageInfo pageInfo;
    private Object data;

    public PaginationResponse(PageInfo pageInfo, Object data) {
        this.pageInfo = pageInfo;
        this.data = data;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
