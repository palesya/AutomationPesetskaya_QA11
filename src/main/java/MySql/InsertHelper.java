package MySql;

import lombok.extern.log4j.Log4j;

@Log4j
public class InsertHelper extends DBConnector {

    private String insert;
    private String into;
    private String values;

    public static InsertHelper getInsert() {
        return new InsertHelper();
    }

    public InsertHelper insert(String insert) {
        this.insert = insert;
        return this;
    }

    public InsertHelper into(String into) {
        this.into = into;
        return this;
    }

    public InsertHelper values(String values) {
        this.values = values;
        return this;
    }

    public InsertHelper execute() {
        int count = executeUpdate("insert into " + this.insert + " ( " + this.into + ") " + " values (" + this.values + ")");
        if (count > 0) {
            log.debug("Data was inserted");
        } else {
            log.error("Data wasn't inserted. Something went wrong");
        }
        return this;
    }
}
