package com.formacion.BS63.model;

public class Greeting {
    private  long id;
    private  String content;
    //private  String content2;

  //  public String getContent2() {
 //       return content2;
  //  }

 //   public void setLastName(String content2) {
  //      this.content2= content2;
   // }



    public Greeting() {
    }

 /*   public Greeting(long id, String content, String content2) {
        this.id = id;
        this.content = content;
        this.content2 = content2;
    }*/

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;

    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }










}
