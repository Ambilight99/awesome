package com.awesome.web.domain.common;

/**
 * @author adam
 * @ClassName ResultMessage
 * @Description 返回结果信息
 * @create 2017/3/12 15:43
 */
public class ResultMessage{
    public static final int SUCCESS =1;
    public static final int FAIL =0;

    private int status;
    private String message;
    private Object data;

    public ResultMessage(){
        super();
    }

    public ResultMessage(int status){
        this.status=status;
    }

    public ResultMessage(int status, String message){
        this.status=status;
        this.message=message;
    }

    public ResultMessage(int status, String message, Object data){
        this.status=status;
        this.message=message;
        this.data=data;
    }

    public static ResultMessage fill(int status,String message){
    	return new ResultMessage(status,message);
    }
    
    public static ResultMessage fill(int status,String message,Object data){
    	return new ResultMessage(status,message,data);
    }

    /**
     * 返回成功状态
     * @return
     */
    public static ResultMessage success(){
        return ResultMessage.fill(ResultMessage.SUCCESS,"","");
    }

    /**
     * 
     * @Title: success   
     * @Description: 返回成功消息
     * @param obj
     * @return 
     * @return ResultMessage 
     * @author adam    
     * @date 2017年3月30日 上午9:31:13
     */
    public static ResultMessage success(Object obj){
        if(obj instanceof String){
            return ResultMessage.fill(ResultMessage.SUCCESS,obj.toString());
        }else{
            return ResultMessage.fill(ResultMessage.SUCCESS,"",obj);
        }
    }
    
    /**
     * 
     * @Title: success   
     * @Description: 返回成功消息
     * @param message 消息
     * @param data	数据
     * @return 
     * @return ResultMessage 
     * @author adam    
     * @date 2017年3月30日 上午9:32:15
     */
    public static ResultMessage success(String message,Object data){
    	return ResultMessage.fill(ResultMessage.SUCCESS,message,data);
    }

    /**
     * 返回失败状态
     * @return
     */
    public static ResultMessage fail(){
        return ResultMessage.fill(ResultMessage.FAIL,"","");

    }

    /**
     * 
     * @Title: fail   
     * @Description: 返回失败消息
     * @param obj
     * @return 
     * @return ResultMessage 
     * @author adam    
     * @date 2017年3月30日 上午9:31:35
     */
    public static ResultMessage fail(Object obj){
        if(obj instanceof String){
            return ResultMessage.fill(ResultMessage.FAIL,obj.toString());
        }else{
            return ResultMessage.fill(ResultMessage.FAIL,"",obj);
        }
    }
    
    /**
     * 
     * @Title: fail   
     * @Description: 返回失败消息
     * @param message
     * @param data
     * @return 
     * @return ResultMessage 
     * @author adam    
     * @date 2017年3月30日 上午9:32:05
     */
    public static ResultMessage fail(String message,Object data){
    	return ResultMessage.fill(ResultMessage.FAIL,message,data);
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
