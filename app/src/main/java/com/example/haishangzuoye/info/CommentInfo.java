package com.example.haishangzuoye.info;

public class CommentInfo {

    /**
     * create_time : 2019-05-10 21:05:28
     * messageId : 1
     * userId : 2
     * taskId : 2
     * content : 放技能的不行不行
     */

    private String create_time;
    private String messageId;
    private String userId;
    private String taskId;
    private String content;
    private String realName;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
