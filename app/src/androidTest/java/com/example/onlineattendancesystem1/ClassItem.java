package com.example.onlineattendancesystem1;

public class ClassItem {
    String className;

    public String getClassName(){
        return className;
    }
    public void setClassName(String className){
        this.className= className;
    }
    public String getSubjectNameClassName(){
        return subjectName;
    }
    public void setSubjectName(String subjectName){
        this.subjectName= subjectName;
    }
    String subjectName;

    public ClassItem(String className,String subjectName){
        this.className = className;
        this.subjectName = subjectName;
    }
}
