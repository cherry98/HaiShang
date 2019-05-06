package com.example.haishangzuoye.info;

public class TaskInfo {

    /**
     * taskTitle : 任务标题1
     * taskType : 任务类型1
     * taskStatus : 0（未接收）1（正在进行）
     * taskAddress : 任务地点1
     * taskDate : 任务时间1
     * taskNumber : 任务需要人数1
     */

    private String taskTitle;
    private String taskType;
    private String taskStatus;
    private String taskAddress;
    private String taskDate;
    private String taskNumber;

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskAddress() {
        return taskAddress;
    }

    public void setTaskAddress(String taskAddress) {
        this.taskAddress = taskAddress;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }
}
