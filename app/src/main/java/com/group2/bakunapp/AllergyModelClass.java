package com.group2.bakunapp;

public class AllergyModelClass {
    String id,name,description,image,list1,list2,list3,list4;

    public AllergyModelClass(String id, String name, String description, String image, String list1, String list2, String list3, String list4) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
        this.list4 = list4;
    }

    public AllergyModelClass() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getList1() {
        return list1;
    }

    public void setList1(String list1) {
        this.list1 = list1;
    }

    public String getList2() {
        return list2;
    }

    public void setList2(String list2) {
        this.list2 = list2;
    }

    public String getList3() {
        return list3;
    }

    public void setList3(String list3) {
        this.list3 = list3;
    }

    public String getList4() {
        return list4;
    }

    public void setList4(String list4) {
        this.list4 = list4;
    }
}
