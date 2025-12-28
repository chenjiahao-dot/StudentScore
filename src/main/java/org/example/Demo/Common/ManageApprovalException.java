package org.example.Demo.Common;

public class ManageApprovalException extends BaseException {
    public ManageApprovalException() {
        super("审批异常");
    }
    public ManageApprovalException(String msg){
        super(msg);
    }
}
