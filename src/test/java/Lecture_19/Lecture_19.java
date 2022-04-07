package Lecture_19;

import org.testng.Assert;
import org.testng.annotations.Test;

import static MySql.DeleteHelper.getDelete;
import static MySql.InsertHelper.getInsert;
import static MySql.SelectHelper.getSelect;
import static MySql.UpdateHelper.getUpdate;

public class Lecture_19 {

    @Test(priority = 1)
    public void select_test() throws Exception {
        Assert.assertTrue(getSelect().select("*").from("user").execute().getListData().size() > 1);
        Assert.assertEquals(getSelect().select("*").from("user").where("id=2").execute().getListData().get(0).get(0), "2");
        Assert.assertEquals(getSelect().select("*").from("user").where("id=2").execute().getListData().size(), 1);
    }

    @Test(priority = 2)
    public void update_test() throws Exception {
        getUpdate().update("user").set("age = 100").where("id = 1").execute();
        Assert.assertEquals(getSelect().select("*").from("user").where("id=1").execute().getListData().get(0).get(3), "100");
        getUpdate().update("user").set("age = 20").where("id = 1").execute();
        Assert.assertEquals(getSelect().select("*").from("user").where("id=1").execute().getListData().get(0).get(3), "20");
    }

    @Test(priority = 3)
    public void insert_test() throws Exception {
        getInsert().insert("pet").into("id, name, type").values("2, 'Jack', 'Street'").execute();
        Assert.assertEquals(getSelect().select("*").from("pet").where("id=2").execute().getListData().get(0).get(1), "Jack");
    }

    @Test(priority = 4)
    public void delete_test() throws Exception {
        getDelete().from("pet").where("id=2").execute();
        Assert.assertEquals(getSelect().select("*").from("pet").where("id=2").execute().getListData().size(), 0);
    }
}
