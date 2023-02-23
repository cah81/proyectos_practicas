package com.formacion.BS7_2.person.criteria;

import lombok.Data;

import java.util.Date;
@Data
public class SearchFilterPerson {
    private  String username;
    private  String name;
    private  String surname;
    private Date created_date;
    private  String dateCriteria;

}
