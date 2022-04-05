package MySql;

import lombok.extern.log4j.Log4j;

import java.sql.ResultSet;

@Log4j
public class UpdateHelper extends DBConnector{

    private String update;
    private String set;
    private String where;

    public static UpdateHelper getUpdate() {
        return new UpdateHelper();
    }

    public UpdateHelper update(String update) {
        this.update = update;
        return this;
    }

    public UpdateHelper set(String set) {
        this.set = set;
        return this;
    }

    public UpdateHelper where(String where) {
        this.where = where;
        return this;
    }

    public UpdateHelper execute() {
        String setWhere = this.where == null ? "" : " where " + this.where;
        int count=executeUpdate("update" + this.update + " set " + this.set + setWhere);
        if (count>0){
            log.debug("Table was updated");
        }
        else{
            log.debug("Table wasn't updated");
        }
        return this;
    }
}
