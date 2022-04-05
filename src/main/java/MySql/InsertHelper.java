package MySql;

import lombok.extern.log4j.Log4j;

@Log4j
public class InsertHelper extends DBConnector {

    private String insert;
    private String into;
    private String values;

    public static InsertHelper getUpdate() {
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
        executeUpdate("insert into" + this.insert + " ( " + this.into + ") " + "values (" + this.values + ")");
        log.debug("Data was inserted");
        return this;
    }
}
